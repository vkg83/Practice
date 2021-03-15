package com.vkg.pactice.loan.maxgain;

public interface Account {
    double getBalance();
    void withdraw(double amount);
    void deposit(double amount);
    void apply(BookEntry entry);
}
