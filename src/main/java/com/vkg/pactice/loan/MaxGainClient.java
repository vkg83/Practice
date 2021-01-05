package com.vkg.pactice.loan;

import com.vkg.pactice.loan.maxgain.TransactionManager;
import com.vkg.pactice.loan.maxgain.Transaction;
import com.vkg.pactice.loan.maxgain.LoanConfig;
import com.vkg.pactice.loan.maxgain.InterestCalculator;
import com.vkg.pactice.loan.maxgain.trans.TransactionBuilders;

import java.time.YearMonth;

import static java.time.Month.*;

public class MaxGainClient {
    public static void main(String[] args) {
        LoanConfig config = new LoanConfig();
        config.setAmount(3000000);
        config.setInterestRate(8.55);
        config.setDurationInMonth(120);
        config.setStartDate(19, JUNE, 2019);
        config.setEmiDay(5);
        TransactionManager tm = new TransactionManager(config);
        tm.addTransaction(TransactionBuilders.disburse().amount(660000).on(19, JUNE, 2019).build());
        tm.addTransaction(TransactionBuilders.general().deposit(100).on(24, JUNE, 2019).build());
        tm.addTransaction(TransactionBuilders.general().deposit(50000).on(5, JULY, 2019).build());
        tm.addTransaction(TransactionBuilders.general().deposit(50000).on(26, JULY, 2019).build());
        tm.addTransaction(TransactionBuilders.general().deposit(50000).on(3, AUGUST, 2019).build());
        tm.addTransaction(TransactionBuilders.general().deposit(50000).on(2, SEPTEMBER, 2019).build());
        tm.addTransaction(TransactionBuilders.disburse().amount(118).on(14, OCTOBER, 2019).build());
        tm.addTransaction(TransactionBuilders.changeInterest(config).percentage(7.1).on(19, JUNE, 2020).build());
        tm.addTransaction(TransactionBuilders.disburse().amount(663116).on(7, OCTOBER, 2020).build());
        tm.addTransaction(TransactionBuilders.general().deposit(100000).on(24, OCTOBER, 2020).build());
        tm.addTransaction(TransactionBuilders.general().deposit(143.14).on(3, NOVEMBER, 2020).build());
        tm.addTransaction(TransactionBuilders.disburse().amount(331558).on(10, JANUARY, 2021).build());
        tm.addTransaction(TransactionBuilders.disburse().amount(331558).on(14, FEBRUARY, 2021).build());
        YearMonth ym = YearMonth.of(2021, JANUARY);
        while(ym.isBefore(YearMonth.of(2025, APRIL))) {
            tm.addTransaction(TransactionBuilders.general().deposit(50000).on(25, ym.getMonth(), ym.getYear()).build());
            ym = ym.plusMonths(1);
        }
        System.out.println(config);
        InterestCalculator calculator = new InterestCalculator(config, tm);
        calculator.printSheet();
    }
}
