package com.vkg.pactice.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DigitWordSorter implements Comparator<Integer> {
    //Eight, Five, Four, Nine, One, Seven, Six, Three, Two, Zero

    private int[] order = {9, 4, 8, 7, 2, 1, 6, 5, 0, 3};

    public static void main(String[] args) {
        DigitWordSorter sorter  = new DigitWordSorter();
        final List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9);
        Collections.sort(list, sorter);
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Override
    public int compare(final Integer o1, final Integer o2) {
        return order[o1]-order[o2];
    }
}
