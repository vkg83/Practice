package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositiveSubArray {
    public ArrayList<Integer> maxset(ArrayList<Integer> a) {
        int start = 0, end = 0;
        Long sum = Long.MIN_VALUE;

        Long curSum = 0l;
        int curStart = 0;
        int curEnd;
        for (int i = 0; i < a.size(); i++) {
            int value = a.get(i);
            if (value >= 0) {
                curEnd = i + 1;
                curSum += value;
                if(curSum > sum || (curSum == sum && end-start < curEnd - curStart)) {
                    sum = curSum;
                    start = curStart;
                    end = curEnd;
                }
            } else {
                curStart = i + 1;
                curEnd = i + 1;
                curSum = 0l;
            }
        }

        return new ArrayList<Integer>(a.subList(start, end));
    }

    public static void main(String[] args) {
        PositiveSubArray array = new PositiveSubArray();
        List<Integer> list = Arrays.asList(0 , 0, -1, 0);
        System.out.println(array.maxset(new ArrayList<Integer>(list)));
    }
}
