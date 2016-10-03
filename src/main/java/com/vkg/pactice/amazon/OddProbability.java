package com.vkg.pactice.amazon;

import java.util.Scanner;

public class OddProbability {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            printProbability(m, n);
        }
    }

    private static void printProbability(final int m, final int n) {
        int mEven = m / 2;
        int nEven = n / 2;

        int oddCount =  mEven * (n - nEven) + nEven * (m - mEven);
        int gcd = getGCD(oddCount, m * n);
        System.out.println(oddCount / gcd + "/" + (m * n) / gcd);
    }

    private static int getGCD(final int a, final int b) {
        if(a == 0) {
            return b;
        }
        return getGCD(b % a, a);
    }

}
