package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.Account;
import com.vkg.pactice.loan.maxgain.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class General extends AbstractTransaction {
    private double amount;

    public General(LocalDate date) {
        super(date);
    }

    @Override
    public String getMessage() {
        String date = getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String type = amount < 0 ? "Withdrawn" : "Deposited";
        return String.format("%s %.2f on %s", type, amount, date);
    }

    @Override
    public void transact(Account state) {
        if(amount < 0) {
            state.withdraw(-amount);
        } else {
            state.deposit(amount);
        }
    }

    public static class Builder extends DatedBuilder<Builder> {
        private double amount;
        @Override
        public General createTransaction(LocalDate date) {
            General tr = new General(date);
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
