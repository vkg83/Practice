package com.vkg.pactice.loan.maxgain;

import java.time.LocalDate;

class BookEntry {
    LocalDate date;
    double balance;
    double interest;
    int days;
    double limit;
    @Override
    public String toString() {
        return String.format("| %s | %3d Days | %12.2f | %12.2f | %12.2f |", date, days, balance, interest, limit);
    }
}
