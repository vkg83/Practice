package com.vkg.pactice.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecodeCounter {
    public int numDecodings(String a) {
        if(a == null || a.isEmpty()) {
            return 0;
        }
        return numDecodings(a, 0, new ArrayList<Integer>(Collections.nCopies(a.length(), -1)));
    }
    public int numDecodings(String a, int offset, final List<Integer> dp) {
        if(offset < dp.size() && dp.get(offset) >= 0) {
            return dp.get(offset);
        }
        if(a.length() - offset > 0 && a.charAt(offset) == '0') {
            return 0;
        } else if(a.length() - offset < 2) {
            return 1;
        }
        char x = a.charAt(offset);
        char y = a.charAt(offset + 1);
        int c2 = 0;

        if((x == '1' && y >= '0' && y <= '9') || (x == '2' && y >= '0' && y <= '6')){
            c2 = numDecodings(a, offset + 2, dp);
        }

        dp.set(offset, numDecodings(a, offset + 1, dp) + c2);

        return dp.get(offset);
    }
}
