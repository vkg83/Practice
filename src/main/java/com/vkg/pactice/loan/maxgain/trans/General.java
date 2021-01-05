package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.AccountState;
import com.vkg.pactice.loan.maxgain.Transaction;

public class General extends Transaction {
    private double amount;
    public void transact(AccountState state) {
        state.transact(amount);
    }

    public static class Builder extends AbstractBuilder<Builder> {
        private double amount;
        @Override
        public Transaction createTransaction() {
            General tr = new General();
            tr.amount = amount;
            return tr;
        }

        public Builder withdraw(double amount) {
            this.amount = -amount;
            return this;
        }

        public Builder deposit(double amount) {
            this.amount = amount;
            return this;
        }

    }
}
