package com.vkg.pactice.backtrack;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutation {
    public String kthPermutation(int n, int k) {
        int off = n - 12;
        StringBuilder sb = new StringBuilder();
        if(off < 0) {
            off = 0;
        } else {
            for (int i = 0; i < off; i++) {
                sb.append(i + 1);
            }
        }
        int nf = 1;
        int[] res = new int[n - off];
        for (int i = 0; i < n - off; i++) {
            res[i] = i + 1 + off;
            nf *= i + 1;
        }
        k--;
        n = n - off;
        for (int x = 0;x < n; x++) {
            nf = nf / (n - x);
            int choosed =  k / nf;
            sb.append(res[choosed]);
            for (int j = choosed + 1; j < n - x; ++j)
                res[j - 1] = res[j];

            k = k % nf;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Permutation ps = new Permutation();
        String s = ps.kthPermutation(5, 500);
        System.out.println(s);
        final ArrayList<ArrayList<Integer>> list = ps.permute(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)));

        for (ArrayList<Integer> p :
                list) {
            System.out.println(p);
        }

        System.out.println(list.size());

    }

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
        final ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        getPermutation(a, 0, result);
        return result;
    }

    private void getPermutation(final ArrayList<Integer> list, final int start, final ArrayList<ArrayList<Integer>> result) {
        if(start == list.size()) {
            result.add(new ArrayList<Integer>(list));
        }
        for(int i = start; i < list.size(); i++) {
            swap(i , start, list);
            getPermutation(list, start + 1, result);
            swap(i , start, list);
        }
    }

    private void swap(final int i, final int j, final ArrayList<Integer> list) {
        if(i != j) {
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }
}
