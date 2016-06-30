package com.vkg.pactice.arrays;

import java.util.ArrayList;

public class MaxOnes {
    public ArrayList<Integer> maxone(ArrayList<Integer> a, int b) {
        int left = 0;
        int right = 0;
        int maxLeft = 0;
        int maxRight = 0;
        int zeros = 0;
        while(right < a.size()) {
            if(zeros <= b) {
                if(a.get(right) == 0) zeros++;
                right++;
            }

            if(zeros > b) {
                if(a.get(left) == 0) zeros--;
                left++;
            }

            if(right - left > maxRight - maxLeft) {
                maxLeft = left;
                maxRight = right;
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = maxLeft; i < maxRight; i++) {
            result.add(i);
        }

        return result;
    }
}
