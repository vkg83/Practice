package com.vkg.pactice.loan.maxgain.trans;

import com.vkg.pactice.loan.maxgain.trans.builders.Builder;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;

public abstract class AbstractTransaction implements Transaction {
    private LocalDate date;

    public AbstractTransaction(LocalDate date) {
        this.date = date;
    }

    @Override
    public int compareTo(Transaction transaction) {
        return this.date.compareTo(transaction.getDate());
    }

    @Override
    public int getDays(Transaction prevTr) {
        return Period.between(((AbstractTransaction)prevTr).fetchDate(), fetchDate()).getDays();
    }

    private LocalDate fetchDate() {
        LocalDate d = getDate();
        if(includeTransactionDay()) {
            return d.plusDays(1);
        }

        return d;
    }
    public LocalDate getDate() {
        return date;
    }

    public boolean isWithin(YearMonth ym) {
        return YearMonth.from(date).equals(ym);
    }


    protected static abstract class DatedBuilder<E extends DatedBuilder<E>> implements Builder {
        private LocalDate date;

        public E on(int day, Month month, int year) {
            return on(day, YearMonth.of(year, month));
        }

        public E on(int day, YearMonth yearMonth) {
            return on(yearMonth.atDay(day));
        }

        public E on(LocalDate date) {
            this.date = date;
            return (E)this;
        }

        @Override
        public final AbstractTransaction build() {
            return createTransaction(date);
        }

        protected abstract AbstractTransaction createTransaction(LocalDate date);
    }
}
