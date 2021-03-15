package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.Account;
import com.vkg.pactice.loan.maxgain.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;

public interface Transaction extends Comparable<Transaction> {
    String getMessage();
    boolean isWithin(YearMonth yearMonth);
    void transact(Account account);
    LocalDate getDate();
    int getDays(Transaction transaction);
    default boolean includeTransactionDay() { return false;}
}
