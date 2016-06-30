package com.vkg.pactice.backtrack;

import java.util.ArrayList;
import java.util.Collections;

public class SubSetFinder {
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
        Collections.sort(a);
        ArrayList<ArrayList<Integer>> result = find(a, 0);
        return result;
    }

    private ArrayList<ArrayList<Integer>> find(final ArrayList<Integer> a, int start) {
        if(a.size() <= start) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
            list.add(new ArrayList<Integer>());
            return list;
        }

        final ArrayList<ArrayList<Integer>> sublists = find(a, start + 1);

        int val = a.get(start);
        final ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for(ArrayList<Integer> subList: sublists) {
            ArrayList<Integer> newlist = new ArrayList<Integer>(subList);
            newlist.add(0, val);
            list.add(newlist);
        }
        list.add(0, sublists.remove(0));
        list.addAll(sublists);

        return list;
    }
}
