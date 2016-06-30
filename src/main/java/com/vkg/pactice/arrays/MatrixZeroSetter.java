package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixZeroSetter {
    public void setZeroes(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        int rowFlag = 1;
        int colFlag = 1;

        for (int i = 0; i < m; i++) {
            if(a.get(i).get(0) == 0) {
                colFlag = 0;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if(a.get(0).get(i) == 0) {
                rowFlag = 0;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(a.get(i).get(j) == 0) {
                    a.get(0).set(j, 0);
                    a.get(i).set(0, 0);
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(a.get(i).get(0) == 0 || a.get(0).get(j) == 0) {
                    a.get(i).set(j, 0);
                }
            }
        }

        if(colFlag == 0) {
            for (int i = 0; i < m; i++) {
                a.get(i).set(0, 0);
            }
        }
        if(rowFlag == 0) {
            for (int i = 0; i < n; i++) {
                a.get(0).set(i, 0);
            }
        }

    }

    public static void main(String[] args) {
        MatrixZeroSetter setter = new MatrixZeroSetter();
        final ArrayList<ArrayList<Integer>> mat = new ArrayList(Arrays.asList(
                new ArrayList(Arrays.asList(1, 0 , 1)),
                new ArrayList(Arrays.asList(1, 1 , 1)),
                new ArrayList(Arrays.asList(1, 1 , 1))
        ));
        //print(mat);
        setter.setZeroes(mat);
        print(mat);
    }

    private static void print(final ArrayList<ArrayList<Integer>> mat) {
        for (ArrayList<Integer> integers : mat) {
            for (Integer integer : integers) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}