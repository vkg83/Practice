package com.vkg.pactice.string;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Vishnu on 5/14/2018.
 */
public class StringChecker {
    public static boolean areAnagrams(String a, String b) {
        if(a.length() != b.length()) {
            return false;
        }
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        return Arrays.equals(aArr, bArr);
    }
}
