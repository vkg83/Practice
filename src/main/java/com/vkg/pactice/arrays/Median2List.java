package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Median of Array
 There are two sorted arrays A and B of size m and n respectively.

 Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).

 The overall run time complexity should be O(log (m+n)).

 Sample Input

 A : [1 4 5]
 B : [2 3]

 Sample Output

 3

 NOTE: IF the number of elements in the merged array is even, then the median is the average of n / 2 th and n/2 + 1th element.
 For example, if the array is [1 2 3 4], the median is (2 + 3) / 2.0 = 2.5
 */

public class Median2List {
    public static void main(String[] args) {
        final List<Integer> arrA = new ArrayList<Integer>(Arrays.<Integer>asList(10));
        final List<Integer> arrB = new ArrayList<Integer>(Arrays.asList(20));
        executeFinder(new PartitionMedianFinder(), arrA, arrB);
        //executeFinder(new DisposalMedianFinder(), arrA, arrB);
        //executeFinder(new KthMedianFinder(), arrA, arrB);
    }

    private static void executeFinder(final MedianFinder finder, final List<Integer> arrA, final List<Integer> arrB) {
        final double val = finder.findMedian(arrA, arrB);
        System.out.println(val);
    }

}

interface MedianFinder {
    double findMedian(List<Integer> a, List<Integer> b);
}

/**
Hint
Given a sorted array A of length m, we can split it into two parts:

{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
All the elements in right part are greater than the elements in left part.

The left part has “i” elements, and right part has “m - i” elements.

There are “m + 1” kinds of splits. (i = 0 ~ m)

When i = 0, the left part has “0” elements, right part has “m” elements.

When i = m, the left part has “m” elements, right part has “0” elements.

For array B, we can split it with the same way:

{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
The left part has “j” elements, and right part has “n - j” elements.

Put A’s left part and B’s left part into one set. (Let us name this set “LeftPart”)

Put A’s right part and B’s right part into one set. (Let us name this set”RightPart”)

        LeftPart              |            RightPart
{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
If we can ensure the following:

1) LeftPart’s length == RightPart’s length (or RightPart’s length + 1)

2) All elements in RightPart is greater than elements in LeftPart,

then we split all elements in {A, B} into two parts with equal length, and one part is

always greater than the other part.

Then the median can be easily found.

The expected time complexity gives away binary search in this case.
We are going to do binary search for the answer in this case.

Given a sorted array A of length m, we can split it into two parts:

{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
All elements in right part are greater than elements in the left part.

The left part has i elements, and right part has m - i elements.
There are m + 1 kinds of splits.

(i = 0 ~ m)

When i = 0, the left part has “0” elements, the right part has “m” elements.
When i = m, the left part has “m” elements, right part has “0” elements.

For the array B, we can split it in the same way:

{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
The left part has “j” elements, and right part has “n - j” elements.

Put A’s left part and B’s left part into one set. (Let’s name this set “LeftPart”)

Put A’s right part and B’s right part into one set. (Let’s name this set”RightPart”)

        LeftPart              |            RightPart
{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
If we can ensure the following:

LeftPart’s length == RightPart’s length (or RightPart’s length + 1)
All elements in RightPart is greater than elements in LeftPart,
then we can split all elements in {A, B} into two parts with equal length, and one part is always greater than the other part.

Then the median can thus be easily found.

To ensure these two condition, we just need to ensure:

Condition 1 :
 i + j == (m - i) + (n - j) OR i + j == (m - i) + (n - j) + 1
Which means if n >= m,

i = 0 to m
j = (m + n + 1) / 2 - i
Condition 2
 B[j - 1] <= A[i] and A[i - 1] <= B[j]
Considering edge values, we need to ensure:

   (j == 0 or i == m or B[j - 1] <= A[i]) and

   (i == 0 or j == n or A[i - 1] <= B[j])
So, all we need to do is:

Search i from 0 to m, to find an object i to meet condition (1) and (2) above.
And we can do this search by binary search.

How?

If B[j0 - 1] > A[i0], than the object ix can’t be in [0, i0].

Why?

Because if

  ix < i0,
  => jx = (m + n + 1) / 2 - ix > j0
  => B[jx - 1] >= B[j0 - 1] > A[i0] >= A[ix].
This violates the condition (2). So ix can’t be less than i0.

And if A[i0 - 1] > B[j0], than the object ix can’t be in [i0, m].
So we can do the binary search following the steps described below:

set iMin, iMax = 0, m, then start searching in [iMin, iMax]
Search in [iMin, iMax]:
    i = (iMin + iMax) / 2
    j = ((m + n + 1) / 2) - i
    if B[j - 1] > A[i]:
        search in [i + 1, iMax]
    else if A[i - 1] > B[j]:
        search in [iMin, i - 1]
    else:
        if m + n is odd:
            answer is max(A[i - 1], B[j - 1])
        else:
            answer is (max(A[i - 1], B[j - 1]) + min(A[i], B[j])) / 2
 */

class PartitionMedianFinder implements MedianFinder {
    public double findMedian(List<Integer> a, List<Integer> b) {
        int m = a.size();
        int n = b.size();

        if(m < n) {
            return search(a, b, 0 , m);
        } else {
            return search(b, a, 0, n);
        }
    }

    private double search(final List<Integer> a, final List<Integer> b, final int iMin, final int iMax) {
        int i = (iMin + iMax) / 2;
        int j = ((a.size() + b.size() + 1) / 2) - i;
        int ai_1 = getVal(i - 1, a, Integer.MIN_VALUE);
        int ai = getVal(i, a, Integer.MAX_VALUE);
        int bj_1 = getVal(j - 1, b, Integer.MIN_VALUE);
        int bj = getVal(j, b, Integer.MAX_VALUE);
        if(bj_1 > ai) {
            return search(a, b, i + 1, iMax);
        } else if (ai_1 > bj){
            return search(a, b, iMin, i - 1);
        }

        double ans = Math.max(ai_1, bj_1);
        if ((a.size() + b.size()) % 2 == 0) {
            ans = (ans + Math.min(ai, bj)) / 2;
        }

        return ans;
    }

    private int getVal(final int i, final List<Integer> list, final int defaultValue) {
        return i >= 0 && i < list.size() ? list.get(i) : defaultValue;
    }

}

class DisposalMedianFinder implements MedianFinder {
    public double findMedian(List<Integer> a, List<Integer> b) {
        double median;
        if(a.size() == 0) {
            median = getMedian(b);
        } else if (b.size() == 0) {
            median = getMedian(a);
        } else {
            median = findMedian(a, b, 0, a.size() - 1, 0, b.size() - 1);
        }

        return median;
    }

    private double getMedian(List<Integer> a) {
        if(a.size() == 0) return 0;
        final int i = a.size() / 2;
        double median = a.get(i);
        if (a.size() % 2 == 0) {
            median = (median + a.get(i - 1)) / 2;
        }

        return median;
    }

    private double findMedian(List<Integer> a, List<Integer> b, int aStart, int aEnd, int bStart, int bEnd) {
        System.out.print(a.subList(aStart, aEnd + 1));
        System.out.print(", ");
        System.out.println(b.subList(bStart, bEnd + 1));
        if(aEnd - aStart <= 1 && bEnd - bStart <= 1) {
            return (Math.max(a.get(aStart), b.get(bStart)) + Math.min(a.get(aEnd), b.get(bEnd))) / 2;
        }

        int m1Index = (aStart + aEnd) / 2;
        int m2Index = (bStart + bEnd) / 2;

        int m1 = a.get(m1Index);
        int m2 = b.get(m2Index);

        if (m1 == m2) {
            return m1;
        } else if (m1 < m2) {
            aStart = m1Index + (aEnd - aStart) % 2;
            bEnd = m2Index;
        } else {
            bStart = m2Index + (bEnd - bStart) % 2;
            aEnd = m1Index;
        }

        return findMedian(a, b, aStart, aEnd, bStart, bEnd);
    }

}

class KthMedianFinder implements MedianFinder {
    public double findMedian(List<Integer> a, List<Integer> b) {
        int m = a.size();
        int n = b.size();
        int mid = (m + n) / 2;

        double ans = findKthItem(a, b, 0, m - 1, 0, n - 1, mid);
        if((m + n) % 2 == 0) {
            ans = (ans + findKthItem(a, b, 0, m - 1, 0, n - 1, mid - 1)) / 2;
        }

        return ans;
    }

    private int findKthItem(final List<Integer> a, final List<Integer> b, int aStart, int aEnd, int bStart, int bEnd, int k) {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if(aLen == 0) {
            return b.get(bStart + k);
        }

        if(bLen == 0) {
            return a.get(aStart + k);
        }

        if(k == 0) {
            return a.get(aStart) < b.get(bStart) ? a.get(aStart) : b.get(bStart);
        }

        int aMid = k * aLen / (aLen + bLen);
        int bMid = k - aMid - 1;
        aMid += aStart;
        bMid += bStart;

        if(a.get(aMid) > b.get(bMid)) {
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