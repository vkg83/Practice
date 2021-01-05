package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.AccountState;
import com.vkg.pactice.loan.maxgain.LoanConfig;
import com.vkg.pactice.loan.maxgain.Transaction;

import java.time.format.DateTimeFormatter;

public class InterestChange extends Transaction {
    private LoanConfig config;
    private double percentage;

    public InterestChange(LoanConfig config) {
        this.config = config;
    }

    @Override
    public void transact(AccountState state) {
        config.setInterestRate(percentage);
        String date = getYearMonth().atDay(getDay()).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        System.out.println(String.format("Interest rate changed to %.2f on %s", config.getInterestRate(), date));
    }

    public static class Builder extends AbstractBuilder<Builder> {
        private LoanConfig config;
        private double percentage;

        public Builder(LoanConfig config) {
            this.config = config;
        }

        public Builder percentage(double percentage) {
            this.percentage = percentage;
            return this;
        }

        @Override
        public Transaction createTransaction() {
            InterestChange tr = new InterestChange(config);
            tr.percentage = percentage;
            return tr;
        }
    }
}
