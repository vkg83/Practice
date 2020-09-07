package com.vkg.pactice.loan.maxgain;

import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class LoanConfig {
    private double amount;
    private double interestRate;
    private int durationInMonth;
    private int emiDay;
    private int startDay;
    private YearMonth startYearMonth;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setDurationInMonth(int durationInMonth) {
        this.durationInMonth = durationInMonth;
    }

    public int getDurationInMonth() {
        return durationInMonth;
    }

    public double getEmi() {
        double rate = getInterestFactor(12);
        return (amount * rate * Math.pow(1 + rate, durationInMonth)) / (Math.pow(1 + rate, durationInMonth) - 1);
    }

    public void setEmiDay(int emiDay) {
        this.emiDay = emiDay;
    }

    public int getEmiDay() {
        return emiDay;
    }

    public double getDailyInterestFactor() {
        return getInterestFactor(365);
    }

    private double getInterestFactor(int factorCount) {
        return interestRate / 100 / factorCount;
    }

    public void setStartDate(int day, Month month, int year) {
        startDay = day;
        startYearMonth = YearMonth.of(year, month);
    }

    public YearMonth getYearMonth() {
        return startYearMonth;
    }

    public double getPrinciple(YearMonth yearMonth) {
        double rate = getInterestFactor(12);
        int period = (int)startYearMonth.until(yearMonth, ChronoUnit.MONTHS);
        return (period>0)? Finance.ppmt(rate, period, this.durationInMonth, -amount):0;
    }

    @Override
    public String toString() {
        return "LoanConfig{" +
                "amount=" + amount +
                ", interestRate=" + interestRate +
                ", durationInMonth=" + durationInMonth +
                ", emiDay=" + emiDay +
                ", startDate=" + startYearMonth.atDay(startDay) +
                '}';
    }
}
