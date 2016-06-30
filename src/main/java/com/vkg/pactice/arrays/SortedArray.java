package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.List;

public class SortedArray {
    public ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        int i = 0, j = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(i < a.size() && j < b.size()) {
            final Integer aVal = a.get(i);
            final Integer bVal = b.get(j);
            if(aVal > bVal) {
                j++;
            } else if (bVal > aVal) {
                i++;
            } else {
                result.add(aVal);
                i++; j++;
            }
        }

        return result;
    }

    public ArrayList<Integer> union(final List<Integer> a, final List<Integer> b) {
        int i = 0, j = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(i < a.size() && j < b.size()) {
            final Integer aVal = a.get(i);
            final Integer bVal = b.get(j);
            if(aVal > bVal) {
                result.add(bVal);
                j++;
            } else if (bVal > aVal) {
                result.add(aVal);
                i++;
            } else {
                result.add(aVal);
                i++; j++;
            }
        }

        while(i < a.size()) {
            result.add(a.get(i));
            i++;
        }

        while(j < b.size()) {
            result.add(b.get(j));
            j++;
        }

        return result;
    }

    public int removeDuplicates(ArrayList<Integer> a) {
        if(a.size() <= 1) {
            return a.size();
        }

        int prev = 0;
        int curr = 1;

        while(curr < a. size()) {
            if(a.get(prev).equals(a.get(curr))) {
                curr++;
            } else {
                prev++;
                a.set(prev, a.get(curr));
                curr++;
            }
        }

        a.subList(prev + 1, a.size()).clear();

        return a.size();
    }
}
