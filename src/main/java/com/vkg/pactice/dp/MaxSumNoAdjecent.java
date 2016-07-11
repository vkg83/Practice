package com.vkg.pactice.dp;

import java.util.ArrayList;
import java.util.Collections;

public class MaxSumNoAdjecent {
    public int adjacent(ArrayList<ArrayList<Integer>> a) {
        int size = a.get(0).size();
        ArrayList<Integer> dp1 = new ArrayList<Integer>(Collections.nCopies(size, (Integer)null));
        ArrayList<Integer> dp2 = new ArrayList<Integer>(dp1);
        ArrayList<ArrayList<Integer>> dp = new ArrayList<ArrayList<Integer>>();
        dp.add(dp1); dp.add(dp2);
        return Math.max(
                Math.max(findMaxSum(a, dp, size - 1, 0),
                        findMaxSum(a, dp, size - 1, 1)),
                Math.max(findMaxSum(a, dp, size - 2, 0),
                        findMaxSum(a, dp, size - 2, 1)));
    }

    public int adjacentBetter(ArrayList<ArrayList<Integer>> a) {
        ArrayList<Integer> input = new ArrayList<>();
        int size = a.get(0).size();
        for(int i = 0; i < size; i++) {
            input.add(Math.max(a.get(0).get(i), a.get(1).get(i)));
        }

        ArrayList<Integer> dp = new ArrayList<Integer>(Collections.nCopies(size, (Integer)null));
        dp.set(0, input.get(0));
        dp.set(1, input.get(1));

        return findMaxSumBetter(input, dp, size, true);
    }

    private int findMaxSumBetter(final ArrayList<Integer> input, final ArrayList<Integer> dp, int index, final boolean excludeCurrent) {
        if(index < 0) {
            return 0;
        }

        int val;
        if (excludeCurrent) {
            val = Math.max(findMaxSumBetter(input, dp, index - 1, false), findMaxSumBetter(input, dp, index - 1, true));

        } else {
            val = findMaxSumBetter(input, dp, index - 1, true) + input.get(index);
        }

        System.out.println(excludeCurrent + ":" + dp);
        return val;
    }

    private int findMaxSum(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> dp, int index, int row) {
        if(index < 0) {
            return 0;
        }

        Integer v = dp.get(row).get(index);
        if(v != null) {
            return v;
        }
        int max = 0;
        int i = index - 2;
        while(i >= 0) {
            int val1 = findMaxSum(a, dp, i, 0);
            int val2 = findMaxSum(a, dp, i, 1);
            v = (val1 > val2 ? val1 : val2);
            if(max < v) {
                max = v;
            }
            i--;
        }

        dp.get(row).set(index, max + a.get(row).get(index));

        printStat(dp);

        return dp.get(row).get(index);
    }

    private void printStat(final ArrayList<ArrayList<Integer>> dp) {
        for (ArrayList<Integer> list : dp) {
            System.out.println(list);
        }
        System.out.println();
    }

    public int adjacentBest(ArrayList<ArrayList<Integer>> a) {
        ArrayList<Integer> input = new ArrayList<>();
        int size = a.get(0).size();
        for(int i = 0; i < size; i++) {
            input.add(Math.max(a.get(0).get(i), a.get(1).get(i)));
        }

        ArrayList<Integer> dp = new ArrayList<Integer>(Collections.nCopies(size, (Integer)null));

        int i;

        for(i=0;i<size;i++) {
            if(i==0) {
                dp.set(i, input.get(i));
            }else if(i==1) {
                dp.set(i, Math.max(dp.get(0),input.get(i)));
            }else{
                dp.set(i, Math.max(dp.get(i - 2)+input.get(i),dp.get(i - 1)));
            }
        }
        return dp.get(size-1);
    }

}
