package com.vkg.pactice.amazon;

import java.util.Scanner;

public class MaxSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int arr[] = new int[count];
        int max = 0;
        int start = -1;
        int end = -1;
        for (int i = 0; i < count; i++) {
            arr[i] = sc.nextInt();
            if(arr[i] == 0) {
                if(max < end - start) {
                    max = end - start;
                }
                start = i;
                end = i;
            } else {
                end++;
            }
        }

        System.out.println(max);
    }
}
