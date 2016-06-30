package com.vkg.pactice.arrays;

import java.util.Arrays;
import java.util.List;

public class SingleNumber {
    public int find(List<Integer> arr) {
        int result = 0;
        for (int value : arr) {
            result ^= value;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 1, 3, 3);
        SingleNumber sn = new SingleNumber();
        final int number = sn.find(arr);
        System.out.println(number);
    }
}