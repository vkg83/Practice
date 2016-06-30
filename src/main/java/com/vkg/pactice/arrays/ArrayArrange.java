package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayArrange {
    // value ->index and index -> value
    public void arrange(ArrayList<Integer> a) {
        final int length = a.size();
        for (int i = 0; i < length; i++) {
            int x = a.get(i);
            a.set(i, x+(a.get(x)%length)*length);
        }
        for (int i = 0; i < length; i++) {
            a.set(i, a.get(i)/length);
        }
    }

    public static void main(String[] args) {
        ArrayArrange arrayArrange = new ArrayArrange();
        final ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(4, 0, 2, 1, 3));
        arrayArrange.arrange(list);
        System.out.println(list);
    }
}
