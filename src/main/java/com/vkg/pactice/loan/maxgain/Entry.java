package com.vkg.pactice.loan.maxgain;

import java.time.LocalDate;

public interface Entry {
    String getDescription();
    LocalDate getCreatedOn();
    double getBalance();
    double getInterest();
}
