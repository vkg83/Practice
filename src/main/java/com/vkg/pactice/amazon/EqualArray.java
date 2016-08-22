package com.vkg.pactice.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EqualArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int arr[] = new int[sc.nextInt()];
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            for (int j = 0; j < arr.length; j++) {
                arr[j] = sc.nextInt();
            }

            boolean flag = isPossible(arr, x, y, z);
            System.out.println(flag ? "She can" : "She can't");
        }
    }

    private static boolean isPossible(final int[] arr, final int x, final int y, final int z) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : new int[]{2, 3, 5, 7}) {
            if (x != i && y != i && z != i) {
                map.put(i, 0);
            }
        }
        for(int val : arr) {
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                if(val % e.getKey() == 0) {
                    map.put(e.getKey(), e.getValue() + 1);
                }
            }
        }

        for (int val : map.values()) {
            if(val > 0 && val < arr.length) {
                return false;
            }
        }
        return true;
    }
}
