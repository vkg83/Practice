package com.vkg.pactice.math;

import org.junit.Test;

public class BudgetDistributionTest {
    @Test
    public void shouldGiveRemainder() throws Exception {
        final BudgetDistribution budgetDistribution = new BudgetDistribution(99.0, .5, .6);
        final BudgetDistribution budgetDistribution2 = new BudgetDistribution(4.9, 10.5, 5.6, 5.7, 20.8, 3.6, 7.5, 25.5, 15.9);
        int count = 0;
        for(int i = 0; i < 100000; i++) {
            final BudgetDistribution randomBudgetDistribution = createRandomBudget();
            double diff = randomBudgetDistribution.display();
            if(Math.abs(diff) == 0) {
                count++;
            }
        }

        System.out.println("No Diff : " + count);
    }

    private BudgetDistribution createRandomBudget() {
        int count = (int)(10 + Math.random() * 10);

        double values[] = new double[count];
        double total = 0;
        for (int i = 0; i < count - 1; i++){
            values[i] = 10 + Math.random() * 10;
            total += values[i];
        }

        values[count - 1] = 10 - (total - Math.floor(total));

        return new BudgetDistribution(values);
    }

}