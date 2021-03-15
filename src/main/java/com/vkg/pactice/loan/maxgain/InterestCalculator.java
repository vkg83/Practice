package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.trans.Transaction;
import com.vkg.pactice.loan.maxgain.trans.TransactionManager;
import com.vkg.pactice.loan.maxgain.trans.builders.TransactionBuilders;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterestCalculator {
    private final LoanConfig config;
    private final TransactionManager transactionManager;
    private final LoanAccount account;
    private Transaction lastTr;
    private AmountAccumulator accumulator;

    public InterestCalculator(LoanConfig config, TransactionManager transactionManager) {
        this.config = config;
        this.account = new LoanAccount();
        this.transactionManager = transactionManager;
        this.lastTr = TransactionBuilders.withdraw(0).on(config.getStartDay(), config.getYearMonth()).build();
    }

    public void setAccumulator(AmountAccumulator accumulator) {
        this.accumulator = accumulator;
    }

    public List<BookEntry> calculate() {
        YearMonth yearMonth = config.getYearMonth();
        List<BookEntry> entryList = new ArrayList<>();
        for (int i = 0; i < config.getDurationInMonth(); i++) {
            List<BookEntry> entries = calculateEntry(yearMonth);
            yearMonth = yearMonth.plusMonths(1);
            if(entries.size() > 0) {
                entryList.addAll(entries);
            }
        }
        return entryList;
    }

    private List<BookEntry> calculateEntry(YearMonth yearMonth) {
        return transactionManager.findTransactionsFor(yearMonth).stream()
                .map(this::toEntry)
                .filter(e -> e.balance >= 0|| !YearMonth.now().isBefore(yearMonth))
                .collect(Collectors.toList());
    }

    private BookEntry toEntry(Transaction transaction) {
        BookEntry e = new BookEntry();
        e.message = transaction.getMessage();
        e.date = transaction.getDate();
        e.days = transaction.getDays(this.lastTr);
        account.apply(e);
        e.interest = calculateInterest(transaction);
        return e;
    }

    private double calculateInterest(Transaction transaction) {
        int days = transaction.getDays(this.lastTr);
        double interest = calculateInterest(days);
        accumulator.accumulate(interest);
        transaction.transact(account);
        this.lastTr = transaction;
        return interest;
    }

    private double calculateInterest(int days) {
        double balance = -account.getBalance();
        double rate = config.getDailyInterestFactor();
        double interest = rate * days * balance;
        return interest < 0 ? 0 : interest;
    }
}
