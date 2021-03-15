package com.vkg.pactice.loan.maxgain.trans.tpl;

import com.vkg.pactice.loan.maxgain.trans.builders.TplBuilder;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public abstract class AbstractTransactionTemplate implements TransactionTemplate {
    private int day;
    private String message;

    public String getMessage() {
        return message;
    }
    protected LocalDate getDate(YearMonth yearMonth) {
        return day > 0 ? yearMonth.atDay(day) : yearMonth.atEndOfMonth();
    }

    protected static abstract class TemplateBuilder<E extends TemplateBuilder<E>> implements TplBuilder {
        private String message;
        private int day;

        @Override
        public E onEach(int day) {
            this.day = day;
            return (E)this;
        }

        public E message(String message) {
            this.message = message;
            return (E)this;
        }

        @Override
        public final AbstractTransactionTemplate build() {
            AbstractTransactionTemplate tr = createTransaction();
            tr.message = message;
            tr.day = day;
            return tr;
        }

        protected abstract AbstractTransactionTemplate createTransaction();
    }

}
