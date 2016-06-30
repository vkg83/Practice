package com.vkg.pactice.arrays;

public class Median2Arrays {
    public static void main(String[] args) {
        Median2Arrays median = new Median2Arrays();
        final int[] arrA = {-50, -47, -36, -35, 0, 13, 14, 16};
        final int[] arrB = {-31, 1, 9, 23, 30, 39};
        final double val = median.findMedianSortedArrays(arrA, arrB);
        System.out.println(val);
    }
    private double findMedianSortedArrays(int a[], int b[]) {
        int m = a.length;
        int n = b.length;
        int mid = (m + n) / 2;

        double ans = findKthItem(a, b, 0, m - 1, 0, n - 1, mid);
        if((m + n) % 2 == 0) {
            ans = (ans + findKthItem(a, b, 0, m - 1, 0, n - 1, mid - 1)) / 2;
        }

        return ans;
    }

    private int findKthItem(final int[] a, final int[] b, int aStart, int aEnd, int bStart, int bEnd, int k) {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if(aLen == 0) {
            return b[bStart + k];
        }

        if(bLen == 0) {
            return a[aStart + k];
        }

        if(k == 0) {
            return a[aStart] < b[bStart] ? a[aStart] : b[bStart];
        }

        int aMid = k * aLen / (aLen + bLen);
        int bMid = k - aMid - 1;
        aMid += aStart;
        bMid += bStart;

        if(a[aMid] > b[bMid]) {
            k -= bMid - bStart + 1;
            bStart = bMid + 1;
            aEnd = aMid;
        } else {
            k -= aMid - aStart + 1 ;
            aStart = aMid + 1;
            bEnd = bMid;
        }
        return findKthItem(a, b, aStart, aEnd, bStart, bEnd, k);
    }
}
