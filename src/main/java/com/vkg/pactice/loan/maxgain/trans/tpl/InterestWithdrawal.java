package com.vkg.pactice.loan.maxgain.trans.tpl;

import com.vkg.pactice.loan.maxgain.Account;
import com.vkg.pactice.loan.maxgain.AmountAccumulator;
import com.vkg.pactice.loan.maxgain.Transactional;
import com.vkg.pactice.loan.maxgain.trans.AbstractTransaction;
import com.vkg.pactice.loan.maxgain.trans.Transaction;

import java.time.YearMonth;

public class InterestWithdrawal extends AbstractTransactionTemplate {
    private AmountAccumulator accumulator;

    @Override
    public Transaction getTransaction(YearMonth yearMonth) {
        return new AbstractTransaction(getDate(yearMonth)) {

            @Override
            public boolean includeTransactionDay() {
                return true;
            }

            @Override
            public String getMessage() {
                return InterestWithdrawal.this.getMessage();
            }

            @Override
            public void transact(Account state) {
                double amount = accumulator.getAmount();
                state.withdraw(amount);
                accumulator.reset();
            }

        };
    }

    public static class Builder extends TemplateBuilder<Builder> {
        private AmountAccumulator accumulator;
        @Override
        public AbstractTransactionTemplate createTransaction() {
            InterestWithdrawal interestWithdrawal = new InterestWithdrawal();
            interestWithdrawal.accumulator = accumulator;
            return interestWithdrawal;
        }

        public Builder accumulator(AmountAccumulator accumulator) {
            this.accumulator = accumulator;
            return this;
        }
    }
}
