package com.vkg.pactice.loan.maxgain.trans.builders;

import com.vkg.pactice.loan.maxgain.trans.*;
import com.vkg.pactice.loan.maxgain.trans.tpl.Emi;
import com.vkg.pactice.loan.maxgain.trans.tpl.InterestWithdrawal;

public final class TransactionBuilders {
    private TransactionBuilders() {
        // Private constructor
    }

    public static Disbursal.Builder disburse(double amount) {
        return new Disbursal.Builder().amount(amount);
    }

    public static InterestRateChange.Builder changeInterest() {
        return new InterestRateChange.Builder();
    }

    public static Emi.Builder emi() {
        return new Emi.Builder().message("EMI deposited");
    }

    public static General.Builder withdraw(double amount) {
        return new General.Builder().withdraw(amount);
    }

    public static General.Builder deposit(double amount) {
        return new General.Builder().deposit(amount);
    }

    public static InterestWithdrawal.Builder withdrawInterest() {
        return new InterestWithdrawal.Builder().message("Withdrawn Interest");
    }
}
