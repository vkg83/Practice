package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.trans.Transaction;

public interface Transactional {
    BookEntry apply(Transaction transaction);
}
