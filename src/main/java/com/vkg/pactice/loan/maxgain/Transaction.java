package com.vkg.pactice.loan.maxgain;

import com.vkg.pactice.loan.maxgain.trans.General;
import com.vkg.pactice.loan.maxgain.trans.builders.Builder;

import java.time.Month;
import java.time.Period;
import java.time.YearMonth;

public abstract class Transaction implements Comparable<Transaction> {
    public abstract void transact(AccountState state);

    private YearMonth yearMonth;
    private int day;

    public int getDay() {
        return day;
    }

    protected YearMonth getYearMonth() {
        return yearMonth;
    }

    int getDays(Transaction prevTr) {
        return Period.between(prevTr.yearMonth.atDay(prevTr.day), yearMonth.atDay(day)).getDays();
    }

    boolean isWithin(YearMonth ym) {
        return yearMonth.equals(ym);
    }

    @Override
    public int compareTo(Transaction transaction) {
        int res = this.yearMonth.compareTo(transaction.yearMonth);
        return res == 0 ? this.day - transaction.day : res;
    }

    protected static abstract class AbstractBuilder<E extends AbstractBuilder<E>> implements Builder {
        private int day;
        private YearMonth yearMonth;

        public E on(int day, Month month, int year) {
            return on(day, YearMonth.of(year, month));
        }

        public E on(int day, YearMonth yearMonth) {
            this.day = day;
            this.yearMonth = yearMonth;
            return (E)this;
        }

        @Override
        public final Transaction build() {
            Transaction tr = createTransaction();
            tr.day = day;
            tr.yearMonth = yearMonth;
            return tr;
        }

        protected abstract Transaction createTransaction();
    }

}
