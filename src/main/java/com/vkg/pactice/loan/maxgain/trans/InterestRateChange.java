package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.Account;
import com.vkg.pactice.loan.maxgain.LoanConfig;
import com.vkg.pactice.loan.maxgain.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InterestRateChange extends AbstractTransaction {
    private LoanConfig config;
    private double percentage;

    public InterestRateChange(LoanConfig config, LocalDate date) {
        super(date);
        this.config = config;
    }

    @Override
    public String getMessage() {
        String date = getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("Interest rate changed to %.2f on %s", percentage, date);
    }

    @Override
    public void transact(Account state) {
        config.setInterestRate(percentage);
    }

    public static class Builder extends DatedBuilder<Builder> {
        private LoanConfig config;
        private double percentage;

        public Builder config(LoanConfig config) {
            this.config = config;
            return this;
        }

        public Builder percentage(double percentage) {
            this.percentage = percentage;
            return this;
        }

        @Override
        public AbstractTransaction createTransaction(LocalDate date) {
            InterestRateChange tr = new InterestRateChange(config, date);
            tr.percentage = percentage;
            return tr;
        }
    }
}
