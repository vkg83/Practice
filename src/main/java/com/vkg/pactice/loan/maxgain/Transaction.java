package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.trans.Disbursal;
import com.vkg.pactice.loan.maxgain.trans.Emi;
import com.vkg.pactice.loan.maxgain.trans.General;
import com.vkg.pactice.loan.maxgain.trans.InterestChange;

import java.time.Month;
import java.time.Period;
import java.time.YearMonth;

public abstract class Transaction implements Comparable<Transaction> {
    public abstract void transact(AccountState state);

    private double amount;
    private YearMonth yearMonth;
    private int day;

    boolean isWithin(YearMonth ym) {
        return yearMonth.equals(ym);
    }

    public double getAmount() {
        return amount;
    }

    int getDays(Transaction prevTr) {
        return Period.between(prevTr.yearMonth.atDay(prevTr.day), yearMonth.atDay(day)).getDays();
    }

    public int getDay() {
        return day;
    }

    protected YearMonth getYearMonth() {
        return yearMonth;
    }

    public static class Builder {
        private Transaction tr = new General();
        public Builder withdraw(double amount) {
            tr.amount = -amount;
            return this;
        }
        public Builder deposit(double amount) {
            tr.amount = amount;
            return this;
        }
        public Builder on(int day, Month month, int year) {
            return on(day, YearMonth.of(year, month));
        }
        public Builder on(int day, YearMonth yearMonth) {
            tr.day = day;
            tr.yearMonth = yearMonth;
            return this;
        }

        public Transaction build() {
            return tr;
        }

        public Builder disburse(double amount) {
            tr = new Disbursal();
            tr.amount = amount;
            return this;
        }

        public Builder interest(LoanConfig config, double amount) {
            tr = new InterestChange(config);
            tr.amount = amount;
            return this;
        }

        Builder emi(double amount) {
            tr = new Emi();
            tr.amount = amount;
            return this;
        }
    }

    @Override
    public int compareTo(Transaction transaction) {
        int res = this.yearMonth.compareTo(transaction.yearMonth);
        return res == 0 ? this.day - transaction.day : res;
    }
}
