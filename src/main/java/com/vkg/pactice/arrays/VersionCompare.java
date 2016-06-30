package com.vkg.pactice.arrays;

public class VersionCompare {
    public int compareVersion(String a, String b) {
        String[] x = a.split("\\.");
        String[] y = b.split("\\.");
        int p = x.length;
        int q = y.length;
        int i = 0;
        for(; i < p && i < q; i++) {
            int diff = compare(x[i], y[i]);
            if(diff > 0) {
                return 1;
            } else if (diff < 0) {
                return -1;
            }
        }

        if(p > q) {
            while(i < p && compare(x[i], "") == 0) i++;
            return i < p ? 1 : 0;
        } else if(q > p){
            while(i < q && compare("", y[i]) == 0) i++;
            return i < q ? -1 : 0;
        } else {
            return 0;
        }
    }

    private int compare(String a, String b) {
        int x = a.length();
        int y = b.length();

        if(x > y) {
            b = getZeros(x-y) + b;
        } else if(y > x){
            a = getZeros(y-x) + a;
        }


        for(int i = 0; i < a.length(); i++) {
            int diff = a.charAt(i) - b.charAt(i);
            if(diff > 0) {
                return 1;
            } else if (diff < 0) {
                return -1;
            }
        }

        return 0;
    }

    private String getZeros(int n) {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < n; i++) {
            b.append('0');
        }

        return b.toString();
    }
}

