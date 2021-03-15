package com.vkg.pactice.loan.maxgain.print;

import java.time.LocalDate;
import java.util.List;

public class AggregatedMapEntry<T> implements AggregatedEntry<T> {
    private LocalDate date;
    private T groupValue;
    private double interest;
    private LocalDate createdOn;
    private double balance;
    private List<String> descriptions;

    public AggregatedMapEntry() {

    }

    @Override
    public String getDescription() {
        return String.join(" | ", descriptions);
    }

    @Override
    public LocalDate getCreatedOn() {
        return date;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterest() {
        return interest;
    }

    @Override
    public void setAggregatedOn(T value) {
        this.groupValue = value;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public T getAggregatedOn() {
        return groupValue;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
}
