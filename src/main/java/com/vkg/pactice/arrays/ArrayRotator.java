package com.vkg.pactice.arrays;

import java.util.Arrays;

public class ArrayRotator {
    public static void main(String[] args) {
        ArrayRotator rotator = new ArrayRotator();
        final int[] arr = {1, 2, 3, 4, 5, 6, 7};
        rotator.rotate(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    private void rotate(final int[] arr, final int k) {
        int c = k % arr.length;
        c = arr.length - c;
        System.out.println(c);
        reverse(arr, 0, c - 1);
        reverse(arr, c, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    private void reverse(final int[] arr, int start, int end) {
        while (start < end) {
            int tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }
}