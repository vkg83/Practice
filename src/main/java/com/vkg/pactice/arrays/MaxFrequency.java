package com.vkg.pactice.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MaxFrequency {
    public static void main(String[] args) {
        MaxFrequency mf = new MaxFrequency();
        System.out.println(mf.findMaxFrequency(Arrays.asList(1, 7, 2, 9, 2)));
        System.out.println(mf.findMaxFrequency(Arrays.asList(1, 7, 2, 7, 2)));
    }
    public int findMaxFrequency(List<Integer> arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            int freq = 0;
            if(map.containsKey(num)) {
                freq = map.get(num);
            }

            map.put(num, freq + 1);
        }

        int max = -1;
        boolean duplicate = false;
        for (int freq : map.values()) {
            if(max < freq) {
                max = freq;
                duplicate = false;
            } else if(max == freq) {
                duplicate = true;
            }
        }

        return duplicate ? -1 : max;
    }
}
