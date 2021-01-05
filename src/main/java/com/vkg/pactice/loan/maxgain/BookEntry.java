package com.vkg.pactice.loan.maxgain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class BookEntry {
    LocalDate date;
    double balance;
    double interest;
    int days;
    double limit;
    @Override
    public String toString() {
        return String.format("| %s | %3d Days | %12.2f | %12.2f | %12.2f |", date.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd")), days, balance, interest, limit);
    }
}
