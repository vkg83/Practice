package com.vkg.pactice.loan.maxgain.trans.tpl;

import com.vkg.pactice.loan.maxgain.Account;
import com.vkg.pactice.loan.maxgain.LoanConfig;
import com.vkg.pactice.loan.maxgain.Transactional;
import com.vkg.pactice.loan.maxgain.trans.AbstractTransaction;
import com.vkg.pactice.loan.maxgain.trans.Transaction;

import java.time.YearMonth;

public class Emi extends AbstractTransactionTemplate {
    private LoanConfig config;

    @Override
    public Transaction getTransaction(YearMonth yearMonth) {
        return new AbstractTransaction(getDate(yearMonth)) {
            @Override
            public String getMessage() {
                return Emi.this.getMessage();
            }

            @Override
            public void transact(Account state) {
                double emi = config.getEmi();
                state.deposit(emi);
            }

            @Override
            public int compareTo(Transaction transaction) {
                int v = super.compareTo(transaction);
                return v == 0 && !(transaction.getClass().isAssignableFrom(this.getClass())) ? -1 : v;
            }
        };
    }

    public static class Builder extends TemplateBuilder<Builder> {
        LoanConfig config;
        @Override
        protected AbstractTransactionTemplate createTransaction() {
            Emi emi = new Emi();
            emi.config = config;
            return emi;
        }

        public Builder config(LoanConfig config) {
            this.config = config;
            return this;
        }
    }
}
