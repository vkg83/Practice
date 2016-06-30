package com.vkg.pactice.math;

public class ExcelColumn {
    public int titleToNumber(String a) {
        int index = 0;
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            int d = ch - 'A' + 1;
            index = index * 26 + d;
        }

        return index;
    }

}
