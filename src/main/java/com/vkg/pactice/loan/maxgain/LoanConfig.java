package com.vkg.pactice.loan.maxgain;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class LoanConfig {
    private double amount;
    private double interestRate;
    private int durationInMonth;
    private int emiDay;
    private LocalDate startDate;
    private double emi;

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
        if(emi - 0 < 0.00001) {
            double rate = getInterestFactor(12);
            emi = (amount * rate * Math.pow(1 + rate, durationInMonth)) / (Math.pow(1 + rate, durationInMonth) - 1);
        }

        return emi;
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
        startDate = LocalDate.of(year, month, day);
    }

    public YearMonth getYearMonth() {
        return YearMonth.from(startDate);
    }

    public int getStartDay() {
        return startDate.getDayOfMonth();
    }

    public double getPrinciple(YearMonth yearMonth) {
        double rate = getInterestFactor(12);
        int period = (int)getYearMonth().until(yearMonth, ChronoUnit.MONTHS);
        return (period>0)? Finance.ppmt(rate, period, this.durationInMonth, -amount):0;
    }

    @Override
    public String toString() {
        return "LoanConfig{" +
                "amount=" + amount +
                ", interestRate=" + interestRate +
                ", durationInMonth=" + durationInMonth +
                ", emiDay=" + emiDay +
                ", startDate=" + startDate +
                '}';
    }
}
