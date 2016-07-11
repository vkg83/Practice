package com.vkg.pactice.math;

import java.math.BigInteger;

public class MathUtil {
    public int sqrt(int a) {
        if(a < 2) return a;
        int x = a/2;
        while (!(x <= a / x && (x + 1) > a / (x + 1))) {
            x = ((a / x) + x) / 2;
        }

        return x;
    }

    //x * x % d
    /*
    n even -> (x * x) ^ (n / 2)
    n odd  -> (x * x) ^ (n / 2) * x
    */

    public int pow(int x, int n, int d) {
        if(d == 1) return 0;
        long t = x % d;
        long c = 1;
        while (n > 0) {
            if(n % 2 == 1) {
               c = (c * t) % d;
            }

            n /= 2;
            t = (t * t) % d;
        }
        c = (d + c) % d;
        return (int) c;
    }

    public boolean isPower(int x) {
        int i = 2;

        int previousFactorCount = 0;

        while(x > 1) {
            int factorCount = 0;
            while (x % i == 0) {
                x /= i;
                factorCount++;
            }

            if(factorCount > 0 && previousFactorCount == 0) {
                previousFactorCount = factorCount;
            }

            if(previousFactorCount != factorCount) {
                return false;
            }

            i = nextPrime(i);
        }

        return true;
    }

    private int nextPrime(final int i) {
        BigInteger b = BigInteger.valueOf(i);
        return b.nextProbablePrime().intValue();
    }
}


/*
4

 */