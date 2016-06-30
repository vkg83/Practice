package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Collections;

public class ThreeSumClosest {
    public int threeSumClosest(ArrayList<Integer> a, int b) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        Collections.sort(a);
        final int size = a.size();
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            int k = size - 1;
            while (j < k) {
                int sum = a.get(i) + a.get(j) + a.get(k);
                int diff = Math.abs(sum - b);
                if (diff == 0) return sum;
                if (diff < min) {
                    min = diff;
                    result = sum;
                }
                if (sum <= b) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

}
