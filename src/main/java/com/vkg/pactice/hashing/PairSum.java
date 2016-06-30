package com.vkg.pactice.hashing;

import java.util.ArrayList;
import java.util.HashMap;

public class PairSum {
    public ArrayList<Integer> equal(ArrayList<Integer> list) {
        HashMap<Integer, Pair> map = new HashMap<Integer, Pair>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            for(int j = i + 1; j < list.size(); j++) {
                int b = list.get(j);
                int sum = a + b;
                if(!map.containsKey(sum)) {
                    Pair p = new Pair(i, j);
                    map.put(a + b, p);
                } else {
                    Pair p = map.get(sum);

                    if(p.first < i && p.second != i && p.second != j) {
                        if(result.isEmpty() || (result.get(0) > p.first ||
                                (result.get(0) == p.first && result.get(1) > p.second) ||
                                (result.get(1) == p.second && result.get(2) > i) ||
                                (result.get(2) == i && result.get(3) > j))) {
                            result.clear();
                            result.add(p.first);
                            result.add(p.second);
                            result.add(i);
                            result.add(j);
                        }
                    }
                }
            }
        }

        return result;
    }
}

class Pair {
    int first;
    int second;
    public Pair(int f, int s) {
        first = f;
        second = s;
    }
}
