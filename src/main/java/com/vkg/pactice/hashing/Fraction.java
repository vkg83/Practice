package com.vkg.pactice.hashing;

import java.util.HashMap;

public class Fraction {
    public String fractionToDecimal(int n, int d) {
        long numerator = n, denominator = d;

        if(numerator == 0) return "0";

        StringBuilder result = new StringBuilder();
        if(numerator < 0 != denominator < 0) result.append('-');

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        result.append(numerator / denominator);
        numerator %= denominator;

        if (numerator > 0) {
            result.append('.');
        }

        HashMap<Long, Integer> map = new HashMap<Long, Integer>();

        while(numerator > 0 && !map.containsKey(numerator)) {
            map.put(numerator, result.length());
            numerator *= 10;
            result.append(numerator / denominator);
            numerator %= denominator;
        }

        if(numerator > 0) {
            int i = map.get(numerator);
            result.insert(i, '(');
            result.append(')');
        }
        return result.toString();
    }
}