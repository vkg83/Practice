package com.vkg.pactice.string;

import java.util.Arrays;

public class RomanConverter {
    /**
     I = 1
     V = 5      I
     X = 10     I
     L = 50     X
     C = 100    X
     D = 500    C
     M = 1000   C

     * @param a
     * @return
     */
    public String intToRoman(int a) {
        return ROMAN.convert(a);
    }

    public int romanToInt(String a) {
        return ROMAN.convert(a);
    }
}

enum ROMAN {
    I(1, null), V(5, I), X(10, I), L(50, X), C(100, X), D(500, C), M(1000, C);

    ROMAN(final int value, final ROMAN negate) {
        this.value = value;
        this.negate = negate;
    }

    private String getRoman(final int n) {
        char[] arr = new char[n];
        Arrays.fill(arr, getChar());
        return new String(arr);
    }

    private char getChar() {
        return this.name().charAt(0);
    }

    private String getNegativeRoman() {
        return this.negate.name() + this.name();
    }

    public static String convert(int num) {
        ROMAN vals[] = ROMAN.values();
        StringBuilder result = new StringBuilder();
        for (int i = vals.length - 1; i >= 0; i--) {
            final ROMAN val = vals[i];
            result.append(val.getRoman(num / val.value));
            num = num % val.value;
            if(val.negate != null && num >= val.value - val.negate.value) {
                result.append(val.getNegativeRoman());
                num -= val.value - val.negate.value;
            }
        }

        return result.toString();
    }

    public static int convert(String romanStr) {
        int result = 0;
        ROMAN prev = null;
        for (char ch : romanStr.toCharArray()) {
            ROMAN roman = parse(ch);
            if(prev != null && prev == roman.negate) {
                result -= 2 * roman.negate.value;
            }
            result += roman.value;
            prev = roman;
        }

        return result;
    }

    private static ROMAN parse(final char ch) {
        for (ROMAN roman :
                values()) {
            if (ch == roman.getChar()) {
                return roman;
            }
        }
        return null;
    }

    private int value;
    private ROMAN negate;
}
