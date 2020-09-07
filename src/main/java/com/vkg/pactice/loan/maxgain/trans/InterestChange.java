package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.AccountState;
import com.vkg.pactice.loan.maxgain.LoanConfig;
import com.vkg.pactice.loan.maxgain.Transaction;

/**
 * Created by Vishnu on 6/21/2020.
 */
public class InterestChange extends Transaction {
    private LoanConfig config;

    public InterestChange(LoanConfig config) {
        this.config = config;
    }

    @Override
    public void transact(AccountState state) {
        config.setInterestRate(getAmount());
    }
}
