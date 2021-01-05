package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.LoanConfig;

public final class TransactionBuilders {
    private TransactionBuilders() {
        // Private constructor
    }

    public static Disbursal.Builder disburse() {
        return new Disbursal.Builder();
    }

    public static InterestChange.Builder changeInterest(LoanConfig config) {
        return new InterestChange.Builder(config);
    }

    public static Emi.Builder emi() {
        return new Emi.Builder();
    }

    public static General.Builder general() {
        return new General.Builder();
    }
}
