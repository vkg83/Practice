package com.vkg.pactice.arrays;

import java.util.Arrays;
import java.util.List;

public class SortedItemCount {
    public static void main(String[] args) {
        SortedItemCount count = new SortedItemCount();
        int c = count.findCount(Arrays.asList(5, 7, 7, 8, 8, 10), 10);
        System.out.println(c);
    }

    public int findCount(final List<Integer> a, int b) {
        int x = findIndex(a, b, true);
        if(x >= 0) {
            int y = findIndex(a, b, false);
            return y - x + 1;
        } else {
            return 0;
        }
    }

    private int findIndex(final List<Integer> a, final int b, boolean findFirst) {
        int low = 0, high = a.size() - 1, result = -1;
        while(low<=high) {
            int mid = (low + high) / 2;

            final Integer item = a.get(mid);
            if(item == b) {
                result = mid;
                if(findFirst)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            else if (item > b) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
