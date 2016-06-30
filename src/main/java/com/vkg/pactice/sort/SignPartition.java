package com.vkg.pactice.sort;

import java.util.Arrays;

public class SignPartition {

    public static void main(String[] args) {
        final SignPartition signPartition = new SignPartition();
        int arr[] = {10, -3, -4,0 ,0,67,89,0,0,-78,9};
        signPartition.partition(arr);
        System.out.println(Arrays.toString(arr));
    }

    private void partition(final int[] arr) {
        int nIndex = 0;
        int lastIndex = arr.length - 1;
        int pIndex = arr.length - 1;
        for (int i = lastIndex; i >= 0; i--) {

            if (arr[i] > 0) {
                if (i != pIndex) {
                    int tmp = arr[i];
                    arr[i] = arr[pIndex];
                    arr[pIndex] = tmp;
                    System.out.println(Arrays.toString(arr));
                }
                pIndex--;

            }
        }
        for (int i = 0; i <= lastIndex; i++) {
            if(arr[i] < 0) {
                if(i != nIndex) {
                    int tmp = arr[i];
                    arr[i] = arr[nIndex];
                    arr[nIndex] = tmp;
                    System.out.println(Arrays.toString(arr));
                }
                nIndex++;

            }
        }
    }
}
