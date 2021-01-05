package com.vkg.pactice.loan.maxgain.trans.builders;

import com.vkg.pactice.loan.maxgain.Transaction;

import java.time.Month;
import java.time.YearMonth;

public interface Builder {
    Builder on(int day, Month month, int year);
    Builder on(int day, YearMonth yearMonth);
    Transaction build();
}
