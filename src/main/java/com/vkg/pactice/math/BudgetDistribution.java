package com.vkg.pactice.math;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetDistribution {
    final List<MediaTactic> tactics;

    public BudgetDistribution(final double... values) {
        tactics = Arrays.stream(values)
                .mapToObj(value -> new MediaTactic(value))
                .collect(Collectors.toList());
    }

    public double display() {
        printDetails();
        printDiff();
        applyDiff(getDistributedDiff());
        printDetails();
        return Math.round(getTotal()) - getRoundedTotal();
    }

    private void applyDiff(final double distributedDiff) {
        tactics.forEach(tactic -> tactic.addDiff(distributedDiff));
    }

    private void printDiff() {
        System.out.println();
        System.out.println("Total Diff:" + getTotalDiff() + ", Distributed diff: " + getDistributedDiff());
        System.out.println();
    }

    private double getDistributedDiff() {
        return getTotalDiff() / this.tactics.size();
    }

    private double getTotalDiff() {
        return getRoundedTotal() - getTotal();
    }

    private void printDetails() {
        System.out.println("Total         : " + getTotal());
        System.out.println("Rounded Total : " + getRoundedTotal());
        tactics.forEach(System.out::println);
    }

    private long getRoundedTotal() {
        return tactics.stream().mapToLong(MediaTactic::getRoundedBudget).sum();
    }

    private double getTotal() {
        return tactics.stream().mapToDouble(MediaTactic::getBudget).sum();
    }
}

class MediaTactic {
    private double budget;

    public MediaTactic(final double budget) {
        this.budget = budget;
    }

    public long getRoundedBudget() {
        return Math.round(this.budget);
    }

    public double getBudget() {
        return this.budget;
    }

    @Override
    public String toString() {
        final long roundedBudget = getRoundedBudget();
        return String.format("BudgetDistribution: %5.2f \tRounded BudgetDistribution: %5d \tDiff: %5.2f", budget, roundedBudget, budget - roundedBudget);
    }

    public void addDiff(final double diff) {
        double rDiff = this.budget - getRoundedBudget();
        this.budget += diff + rDiff;
    }
}
