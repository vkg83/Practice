package com.vkg.pactice.string;

public class PalindromicSubstring {

    /**
     P[ i, j ] ← ( P[ i+1, j-1 ] and Si = Sj )
     The base cases are:

     P[ i, i ] ← true
     P[ i, i+1 ] ← ( Si = Si+1 )
     * @param str
     * @return
     */
    public String find(String str) {
        int start = 0;
        int s = 0;
        int e = 1;
        for (int i = 1; i < str.length(); i++) {
            if(start - 1 >= 0 && str.charAt(start - 1) == str.charAt(i)) {
                start = start - 1;
                if(e - s < i - start + 1) {
                    s = start;
                    e = i + 1;
                }
            } else if (i - 2 >= 0 && str.charAt(i - 2) == str.charAt(i)) {
                start = i - 2;
                if(e - s < i - start + 1) {
                    s = start;
                    e = i + 1;
                }
            } else if (i - 1 >= 0 && str.charAt(i - 1) == str.charAt(i)) {
                start = i - 1;
                if(e - s < i - start + 1) {
                    s = start;
                    e = i + 1;
                }
            } else {
                start++;
                i = start;
            }
        }
        return str.substring(s, e);
    }

    public String longestPalSubstr(String str) {
        int maxLength = 1;  // The result (length of LPS)

        int start = 0;
        int len = str.length();

        int low, high;

        // One by one consider every character as center point of
        // even and length palindromes
        for (int i = 1; i < len; ++i) {
            // Find the longest even length palindrome with center points
            // as i-1 and i.
            low = i - 1;
            high = i;
            while (low >= 0 && high < len && str.charAt(low) == str.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }

            // Find the longest odd length palindrome with center
            // point as i
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < len && str.charAt(low) == str.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }
        }

        return str.substring(start, start + maxLength);
    }
}
