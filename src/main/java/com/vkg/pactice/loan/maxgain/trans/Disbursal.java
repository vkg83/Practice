package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.Account;
import com.vkg.pactice.loan.maxgain.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Disbursal extends AbstractTransaction {
    private double amount;

    public Disbursal(LocalDate date) {
        super(date);
    }

    @Override
    public String getMessage() {
        String date = getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("Disbursed %.2f on %s", amount, date);
    }

    @Override
    public void transact(Account state) {
        state.withdraw(amount);
    }

    public static class Builder extends DatedBuilder<Builder> {
        private double amount;
        @Override
        public Disbursal createTransaction(LocalDate date) {
            Disbursal tr = new Disbursal(date);
            tr.amount = amount;
            return tr;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }
    }

}
