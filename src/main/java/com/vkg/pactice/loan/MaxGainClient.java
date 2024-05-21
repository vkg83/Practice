package com.vkg.pactice.loan;

import com.vkg.pactice.loan.maxgain.*;
import com.vkg.pactice.loan.maxgain.print.*;
import com.vkg.pactice.loan.maxgain.trans.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static com.vkg.pactice.loan.maxgain.trans.builders.TransactionBuilders.*;
import static java.time.Month.*;

public class MaxGainClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaxGainClient.class);
    public static void main(String[] args) {
        LoanConfig config = new LoanConfig();
        config.setAmount(3000000);
        config.setInterestRate(8.55);
        config.setDurationInMonth(120);
        config.setStartDate(19, JUNE, 2019);
        config.setEmiDay(5);
        TransactionManager tm = new TransactionManager(config);
        tm.addTransaction(disburse(660000).on(19, JUNE, 2019).build());
        tm.addTransaction(deposit(100).on(24, JUNE, 2019).build());
        tm.addTransaction(deposit(50000).on(5, JULY, 2019).build());
        tm.addTransaction(deposit(50000).on(26, JULY, 2019).build());
        tm.addTransaction(deposit(50000).on(3, AUGUST, 2019).build());
        tm.addTransaction(deposit(50000).on(2, SEPTEMBER, 2019).build());
        tm.addTransaction(disburse(118).on(14, OCTOBER, 2019).build());
        tm.addTransaction(changeInterest().config(config).percentage(7.1).on(19, JUNE, 2020).build());
        tm.addTransaction(disburse(663116).on(7, OCTOBER, 2020).build());
        tm.addTransaction(deposit(100000).on(24, OCTOBER, 2020).build());
        tm.addTransaction(deposit(143.14).on(3, NOVEMBER, 2020).build());
        tm.addTransaction(deposit(50000).on(27, JANUARY, 2021).build());
        tm.addTransaction(deposit(50000).on(4, FEBRUARY, 2021).build());
        tm.addTransaction(deposit(50000).on(28, FEBRUARY, 2021).build());
        tm.addTransaction(disburse(994674).on(12, MARCH, 2021).build());
        tm.addTransaction(deposit(360000).on(15, MARCH, 2021).build());
        tm.addTransaction(deposit(50000).on(10, MAY, 2021).build());
        tm.addTransaction(deposit(50000).on(20, MAY, 2021).build());
        tm.addTransaction(deposit(50000).on(27, MAY, 2021).build());
        tm.addTransaction(deposit(50000).on(26, JUNE, 2021).build());
        tm.addTransaction(deposit(50000).on(23, JULY, 2021).build());
        tm.addTransaction(deposit(50000).on(16, AUGUST, 2021).build());
        tm.addTransaction(emi().config(config).onEach(config.getEmiDay()).build());
        AmountAccumulator acc = new AmountAccumulator();
        tm.addTransaction(withdrawInterest().accumulator(acc).build());

        YearMonth ym = YearMonth.of(2021, SEPTEMBER);
        while(ym.isBefore(YearMonth.of(2025, JULY))) {
            tm.addTransaction(deposit(50000).on(25, ym.getMonth(), ym.getYear()).build());
            ym = ym.plusMonths(1);
        }
        LOGGER.info("\n {}", config);
        LOGGER.info(String.format("EMI = %8.0f.00%n", config.getEmi()));

        InterestCalculator calculator = new InterestCalculator(config, tm);
        calculator.setAccumulator(acc);
        List<BookEntry> entries = calculator.calculate();
        entries.sort(Comparator.comparing(BookEntry::getCreatedOn));
        entries.forEach(e -> LOGGER.info("{}", e));

        GroupAggregator<YearMonth> aggregator = new GroupAggregator<>(entries);
        Function<Entry, LocalDate> groupField = Entry::getCreatedOn;
        aggregator.groupBy(groupField.andThen(YearMonth::from), AggregatedMapEntry::new);

        aggregator.sum(Entry::getInterest, (e, v) ->((AggregatedMapEntry)e).setInterest(v));
        aggregator.last(Entry::getBalance, (e, v) ->((AggregatedMapEntry)e).setBalance(v.doubleValue()));

        GroupAggregator.AggregatorRule<String, ArrayList<String>> descRule = new GroupAggregator.AggregatorRule<>(ArrayList::new);
        descRule.extractor(Entry::getDescription);
        descRule.operate((x, y) -> {x.add(y); return x;});
        descRule.applier((e, v) ->((AggregatedMapEntry)e).setDescriptions(v));
        aggregator.addRule(descRule);

        aggregator.sum(e -> ((BookEntry)e).getDays(), (e, v) -> ((AggregatedMapEntry<?>)e).getDescriptions().add("Days: " + v.intValue()));

        MonthlyInterestPrinter printer = new MonthlyInterestPrinter(aggregator);
        printer.add("#");
        printer.add("Month   ");
        printer.add(" Interest");
        printer.add("         Balance");
        printer.print();

        GroupAggregator<Year> yearAggregator = new GroupAggregator<>(entries);
        yearAggregator.groupBy(groupField.andThen(d -> d.minusMonths(3)).andThen(Year::from), AggregatedMapEntry::new);

        GroupAggregator.AggregatorRule<Double, Double> yrIntRule = new GroupAggregator.AggregatorRule<>(0.0);
        yrIntRule.extractor(Entry::getInterest);
        yrIntRule.operate(Double::sum);
        yrIntRule.applier((e, v) ->((AggregatedMapEntry)e).setInterest(v));
        yearAggregator.addRule(yrIntRule);
        YearlyInterestPrinter yearPrinter = new YearlyInterestPrinter(yearAggregator);
        yearPrinter.add("#");
        yearPrinter.add("Year");
        yearPrinter.add(" Interest");
        yearPrinter.print();
    }
}
