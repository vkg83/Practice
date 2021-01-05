package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.trans.TransactionBuilders;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {
    private final LoanConfig config;
    private List<Transaction> transactions;

    public TransactionManager(LoanConfig config) {
        this.config = config;
        this.transactions = new ArrayList<>();
    }

    List<Transaction> findTransactionsFor(YearMonth yearMonth) {
        List<Transaction> list = getTransactions(yearMonth);
        addEmiTransaction(yearMonth, list);
        list.add(getMonthEndTransaction(yearMonth));
        Collections.sort(list);
        return list;
    }

    private Transaction getMonthEndTransaction(YearMonth yearMonth) {
        return TransactionBuilders.general()
                .on(1, yearMonth.plusMonths(1))
                .build();
    }

    private void addEmiTransaction(YearMonth yearMonth, List<Transaction> list) {
        if (yearMonth.isAfter(config.getYearMonth())) {
            list.add(getEmiTransaction(yearMonth));
        }
    }

    private Transaction getEmiTransaction(YearMonth yearMonth) {
        return TransactionBuilders.emi().on(config.getEmiDay(), yearMonth)
                .build();
    }

    private List<Transaction> getTransactions(YearMonth yearMonth) {
        return transactions.stream()
                    .filter(t -> t.isWithin(yearMonth))
                    .collect(Collectors.toList());
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}