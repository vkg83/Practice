package com.vkg.pactice.math;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeSum {

    public ArrayList<Integer> primesum(int a) {
        ArrayList result = getPair(2, a - 2);
        int i = 3;
        while (result == null) {
            result = getPair(i, a - i);
            i+=2;
        }

        return result;
    }

    private ArrayList<Integer> getPair(final int a, final int b) {
        return isPrimes(a) && isPrimes(b)? new ArrayList<Integer>(Arrays.asList(a, b)):null;
    }

    public boolean isPrimes(int num) {
        if(num<2) {
            return false;
        }
        int upperLimit = (int)(Math.sqrt(num));
        for (int i = 2; i <= upperLimit; i++) {
            if (i < num && num % i == 0) return false;
        }
        return true;
    }
}
