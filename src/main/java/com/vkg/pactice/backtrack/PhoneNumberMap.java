package com.vkg.pactice.backtrack;

import com.vkg.pactice.stack.BalancedParanthesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberMap {
    private final BalancedParanthesis balancedParanthesis;

    public PhoneNumberMap(BalancedParanthesis balancedParanthesis) {
        this.balancedParanthesis = balancedParanthesis;
    }

    public ArrayList<String> letterCombinations(String a) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "0");
        map.put(1, "1");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        return build(a, map);
    }



    private ArrayList<String> build(final String a, final Map<Integer, String> map) {

        if(a.length() <= 0) {
            final ArrayList<String> list = new ArrayList<String>();
            list.add("");
            return list;
        }

        int d = a.charAt(0) - '0';
        ArrayList<String> result = new ArrayList<String>();
        for (char ch : map.get(d).toCharArray()) {
            ArrayList<String> list = build(a.substring(1), map);
            for (String s : list) {
                result.add(ch + s);
            }
        }
        return result;
    }

}