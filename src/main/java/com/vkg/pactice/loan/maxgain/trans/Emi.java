package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.AccountState;
import com.vkg.pactice.loan.maxgain.Transaction;

public class Emi extends Transaction {
    @Override
    public void transact(AccountState state) {
        state.depositEmi(getYearMonth());
    }

}
