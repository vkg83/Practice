package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.LoanConfig;
import com.vkg.pactice.loan.maxgain.trans.tpl.TransactionTemplate;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionManager {
    private final LoanConfig config;
    private List<Transaction> transactions;
    private List<TransactionTemplate> transactionTemplates;

    public TransactionManager(LoanConfig config) {
        this.config = config;
        this.transactions = new ArrayList<>();
        this.transactionTemplates = new ArrayList<>();
    }

    public List<Transaction> findTransactionsFor(YearMonth yearMonth) {
        List<Transaction> list = getTransactions(transactions.stream(), yearMonth);
        list.addAll(getTransactionFromTemplates(yearMonth));
        Collections.sort(list);
        return list;
    }

    private List<Transaction> getTransactionFromTemplates(YearMonth yearMonth) {
        return getTransactions(transactionTemplates.stream()
                .map(tpl -> tpl.getTransaction(yearMonth)), yearMonth);
    }

    private List<Transaction> getTransactions(Stream<Transaction> transactions, YearMonth yearMonth) {
        return transactions
                    .filter(isWithin(yearMonth))
                    .collect(Collectors.toList());
    }

    private Predicate<Transaction> isWithin(YearMonth yearMonth) {
        int day = config.getStartDay();
        return t -> t.isWithin(yearMonth) && !config.getYearMonth().atDay(day).isAfter(t.getDate());
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public void addTransaction(TransactionTemplate transactionTemplate) {
        transactionTemplates.add(transactionTemplate);
    }
}