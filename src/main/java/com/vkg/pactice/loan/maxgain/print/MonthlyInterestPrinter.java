package com.vkg.pactice.loan.maxgain.print;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MonthlyInterestPrinter extends SheetPrinter<YearMonth> {

    private final EntryAggregator<YearMonth> aggregator;

    public MonthlyInterestPrinter(EntryAggregator<YearMonth> aggregator) {
        this.aggregator = aggregator;
    }

    @Override
    public void printContent() {
        Map<YearMonth, AggregatedEntry<YearMonth>> entries = aggregator.aggregateGroup();
        int idx = 0;
        for (YearMonth e : entries.keySet()) {
            AggregatedEntry<YearMonth> aggregatedEntry = entries.get(e);
            YearMonth yearMonth = aggregatedEntry.getAggregatedOn();
            String current = YearMonth.now().equals(yearMonth)? " Current" :"";
            String dt = yearMonth.format(DateTimeFormatter.ofPattern("yyyy MMM"));
            print("| %2d| %s | %6.0f.00 | %16.2f |%s",++idx, dt, aggregatedEntry.getInterest(), aggregatedEntry.getBalance(), current);
            print("|---------------------------------------------|");
            for (String desc : ((AggregatedMapEntry<?>)aggregatedEntry).getDescriptions()) {
                print("|%-45s|", desc);
            }
            print("|_____________________________________________|");
        }

        print("|           ---- **** END **** ----           |");
    }
}
