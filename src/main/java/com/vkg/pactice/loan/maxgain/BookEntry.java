package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.print.AggregatedEntry;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class BookEntry implements Entry {
    String message;
    LocalDate date;
    double balance;
    double interest;
    int days;
    double limit;
    @Override
    public String toString() {
        String dt = date.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd"));
        return String.format("| %s | %3d Days | %12.2f | %12.2f | %12.2f |", dt, days, balance, interest, limit);
    }

    @Override
    public String getDescription() {
        return message;
    }

    @Override
    public LocalDate getCreatedOn() {
        return date;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterest() {
        return interest;
    }

    public int getDays() {
        return days;
    }
}
