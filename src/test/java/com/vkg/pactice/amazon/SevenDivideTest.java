package com.vkg.pactice.amazon;

import org.junit.Assert;
import org.junit.Test;

public class SevenDivideTest {
    @Test
    public void shouldName() throws Exception {
        char[] arr = new char[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char)('0' + (int)(Math.random() * 10));
        }
        int[] map = {0, 3, 6, 2, 5, 1, 4};
        long start = System.currentTimeMillis();
        final boolean b = SevenDivide.isDivisible2(arr, 0, 99999, map);
        System.out.println("" + b + "," + (System.currentTimeMillis() - start));
    }
    @Test
    public void shouldBeDivisible() throws Exception {
        char[] arr = new char[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = '7';
        }
        int[] map = {0, 3, 6, 2, 5, 1, 4};
        long start = System.currentTimeMillis();
        int x = 0;
        boolean b = false;
        while(++x < 100000)
            b = SevenDivide.isDivisible2(arr, 0, 99999, map);
        Assert.assertTrue(b);
        System.out.println("" + b + "," + (System.currentTimeMillis() - start));
    }
}