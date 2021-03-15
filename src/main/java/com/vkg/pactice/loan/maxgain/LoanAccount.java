package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.trans.Transaction;
import com.vkg.pactice.loan.maxgain.trans.builders.TransactionBuilders;

import java.time.YearMonth;

public class LoanAccount implements Account {
    private double balance;

    public LoanAccount() {
        balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }


    @Override
    public String toString() {
        return String.format("%11.2f | %11.2f | %11.2f | %11.2f", balance, balance);
    }

    @Override
    public void apply(BookEntry e) {
        e.balance = -balance;
    }
}
