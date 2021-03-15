package com.vkg.pactice.loan.maxgain;

public class AmountAccumulator {
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void accumulate(double value) {
        amount += value;
    }

    public void reset() {
        amount = 0;
    }
}
