package com.vkg.pactice.amazon;

import java.util.Scanner;

public class SevenDivide {
    public static void main(String[] args) {
        int[] map = {0, 3, 6, 2, 5, 1, 4};
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        int count = sc.nextInt();
        for (int i = 0; i < count; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            boolean flag = isDivisible2(arr, l - 1, r - 1, map);
            System.out.println(flag ? "YES" : "NO");
        }
    }

    public static boolean isDivisible2(final char[] arr, final int left, final int right, int[] map) {
        int pos = 0;
        for (int i = left; i <= right; i++) {
            int hop = (pos + arr[i] - '0') % 7;
            pos = map[hop];
        }

        return pos == 0;
    }

    private static boolean isDivisible(final char[] arr, final int left, final int right) {
        int val = 0;
        int[] digits = {1, 3, 2, 6, 4, 5};
        int q = ((right - left) / 6) * 6 + left;
        int r = (right - left) % 6;
        for (int i = right; i >= q; i-=6) {
            val += (arr[i] - '0') * digits[(right - i) % 6];
            val = val % 7;
        }


        return val % 7 == 0;
    }
}
