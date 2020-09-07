package com.vkg.pactice.loan.maxgain;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Month.APRIL;

public class InterestCalculator {
    private final LoanConfig config;
    private final TransactionManager transactionManager;

    public InterestCalculator(LoanConfig config, TransactionManager transactionManager) {
        this.config = config;
        this.transactionManager = transactionManager;
    }

    public List<BookEntry> printSheet() {
        AccountState state = new AccountState(config);
        YearMonth yearMonth = config.getYearMonth();
        double fyInterest = 0;
        double fyPrinciple = 0;
        List<BookEntry> entryList = new ArrayList<>();

        double emi = config.getEmi();
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|  #| Month    | Interest  | Limit       | Available   | Book Balance|");
        System.out.println("|--------------+-----------+-------------+-------------+-------------|");
        for (int i = 0; i < config.getDurationInMonth(); i++) {
            List<BookEntry> entries = calculateEntry(state, yearMonth);
            entryList.addAll(entries);
            double interest = entries.stream().map(e->e.interest).reduce(0.0, (a, b) ->  a + b);
            print("|%3d| %s | %6.0f.00 | %s |", i+1, yearMonth.format(DateTimeFormatter.ofPattern("MMM yyyy")), interest, state);
            state.withdrawInterest(interest);
            fyInterest+= interest;
            if(yearMonth.isAfter(config.getYearMonth())) {
                fyPrinciple+= emi;
            }
            fyPrinciple-=interest;
            yearMonth = yearMonth.plusMonths(1);
            if(yearMonth.getMonth() == APRIL || interest < 0) {
                System.out.println("+------------------------------------------------------+-------------+");
                print("FY %d : Interest = %.2f, Principle = %.2f, Total = %.2f", yearMonth.getYear(), fyInterest, fyPrinciple, fyInterest+fyPrinciple);
                System.out.println("+------------------------------------------------------+-------------+");
                fyInterest = 0;
            }
            if(interest < 0) break;
        }

        System.out.println("+--------------------------------------------------------------------+");
        print("Balance:        %12.2f", state.getBalance());
        print("Interest Saved: %12.2f", state.getEmiBalance());

        entryList.forEach(System.out::println);
        return entryList;
    }

    private void print(String format, Object... values) {
        System.out.println(String.format(format, values));
    }

    private List<BookEntry> calculateEntry(AccountState state, YearMonth yearMonth) {
        return transactionManager.findTransactionsFor(yearMonth).stream()
                .map(state::getSnapshot)
                .collect(Collectors.toList());
    }
}
