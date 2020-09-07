package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.AccountState;
import com.vkg.pactice.loan.maxgain.Transaction;

/**
 * Created by Vishnu on 5/14/2020.
 */
public class Disbursal extends Transaction {
    @Override
    public void transact(AccountState state) {
        state.disburse(getAmount());
    }
}