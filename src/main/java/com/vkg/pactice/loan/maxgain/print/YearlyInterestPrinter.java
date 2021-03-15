package com.vkg.pactice.loan.maxgain.print;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class YearlyInterestPrinter extends SheetPrinter<Year> {

    private final EntryAggregator<Year> aggregator;

    public YearlyInterestPrinter(EntryAggregator<Year> aggregator) {
        this.aggregator = aggregator;
    }

    @Override
    public void printContent() {
        Map<Year, AggregatedEntry<Year>> entries = aggregator.aggregateGroup();
        int idx = 0;
        double interest =0;
        for (Year e : entries.keySet()) {
            AggregatedEntry<Year> aggregatedEntry = entries.get(e);
            String dt = aggregatedEntry.getAggregatedOn().format(DateTimeFormatter.ofPattern("yyyy"));
            print("| %2d) %s : %9.2f |",++idx, dt, aggregatedEntry.getInterest());
            interest += aggregatedEntry.getInterest();
        }
        print("|%-20s|", "______________________");

        print("|Total %15.2f |", interest);
    }
}
