package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.AccountState;
import com.vkg.pactice.loan.maxgain.Transaction;

import java.time.format.DateTimeFormatter;

public class Disbursal extends Transaction {
    private double amount;

    @Override
    public void transact(AccountState state) {
        state.disburse(amount);
        String date = getYearMonth().atDay(getDay()).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        System.out.println(String.format("Disbursed %.2f on %s", amount, date));
    }

    public static class Builder extends AbstractBuilder<Builder> {
        private double amount;
        @Override
        public Disbursal createTransaction() {
            Disbursal tr = new Disbursal();
            tr.amount = amount;
            return tr;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }
    }

}
