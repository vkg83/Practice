package com.vkg.pactice.loan.maxgain.trans.tpl;

import com.vkg.pactice.loan.maxgain.trans.Transaction;

import java.time.YearMonth;

public interface TransactionTemplate {
    Transaction getTransaction(YearMonth yearMonth);
}
