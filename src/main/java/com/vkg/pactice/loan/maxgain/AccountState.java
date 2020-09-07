package com.vkg.pactice.loan.maxgain;

import java.time.YearMonth;

public class AccountState {
    private double drawingPower;
    private double limit;
    private double balance;
    private double emiBalance;
    private LoanConfig config;
    private Transaction transaction;

    public AccountState(LoanConfig config) {
        this.config = config;
        //rate = config.getDailyInterestFactor();
        drawingPower = 0;
        limit = config.getAmount();
        transaction = new Transaction.Builder().on(1, config.getYearMonth()).build();
    }

    public void disburse(double amount) {
        drawingPower += amount;
        limit -= amount;
    }

    public void transact(double amount) {
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

    public double getEmiBalance() {
        return emiBalance;
    }

    @Override
    public String toString() {
        return String.format("%11.2f | %11.2f | %11.2f | %11.2f", limit+balance, getAvailableBalance(), getBookBalance(), drawingPower);
    }

    public void withdrawInterest(double amount) {
        emiBalance -= amount;
    }

    public void depositEmi(YearMonth yearMonth) {
        double emi = config.getEmi();
        double p = config.getPrinciple(yearMonth);

        emiBalance += emi - p;
        limit += p;
        drawingPower -= p;
    }

    public double getLimit() {
        return limit;
    }

    public BookEntry getSnapshot(Transaction transaction) {
        BookEntry e = new BookEntry();
        e.date= transaction.getYearMonth().atDay(transaction.getDay());
        e.balance = getBookBalance();
        e.days = transaction.getDays(this.transaction);
        double rate = config.getDailyInterestFactor();
        e.interest = rate * e.days * getBookBalance();
        e.limit = drawingPower;
        transaction.transact(this);
        this.transaction = transaction;
        return e;
    }
}