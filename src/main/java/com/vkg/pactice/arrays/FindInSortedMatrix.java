package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class FindInSortedMatrix {
    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        int row = a.size(), col = a.get(0).size();
        int low = 0, high = row * col - 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            int j = mid % col;
            int i = mid / col;
            int value = a.get(i).get(j);
            if(value == b) return 1;
            else if (value > b) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        FindInSortedMatrix findInSortedMatrix = new FindInSortedMatrix();
        final ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(12, 9, 12, 13, 16, 18, 18, 19, 20, 22)),
                new ArrayList<Integer>(Arrays.asList(29, 59, 62, 66, 71, 75, 77, 79, 97, 99))
        ));
        final int b = 45;
        final int i = findInSortedMatrix.searchMatrix(arrayLists, b);
        System.out.println(i);
    }
}

