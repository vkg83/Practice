package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.trans.Transaction;
import com.vkg.pactice.loan.maxgain.trans.builders.TransactionBuilders;

import java.time.YearMonth;

public class LedgerBook extends LoanAccount {
    private double drawingPower;
    private double limit;
    private double balance;
    private double emiBalance;
    private LoanConfig config;

    public LedgerBook(LoanConfig config) {
        this.config = config;
        drawingPower = 0;
        limit = config.getAmount();
    }

    @Override
    public void withdraw(double amount) {
        drawingPower += amount;
        limit -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    public double getAvailableBalance() {
        return balance + emiBalance;
    }

    public double getBookBalance() {
        return drawingPower - getAvailableBalance();
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("%11.2f | %11.2f | %11.2f | %11.2f", limit+balance, getAvailableBalance(), getBookBalance(), balance);
    }

    public double getLimit() {
        return limit;
    }

    @Override
    public void apply(BookEntry e) {
        super.apply(e);
        e.limit = drawingPower;
    }
}
