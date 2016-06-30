package com.vkg.pactice.string;

import java.util.HashMap;
import java.util.Map;

public class Isomorphic {
    public static void main(String[] args) {
        Isomorphic isomorphic = new Isomorphic();
        //boolean flag = isomorphic.areIsomorphic("foo", "egg");
        boolean flag = isomorphic.areIsomorphic("foh", "egg");
        System.out.println(flag?"YES":"NO");
    }

    private boolean areIsomorphic(final String str1, final String str2) {
        if(str1 == null && str2 == null) {
            return true;
        }
        if(str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        if(str1.length() == 0) {
            return true;
        }

        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < str1.length(); i++) {
             char ch1 = str1.charAt(i);
             char ch2 = str2.charAt(i);
            Character ch = getKey(map, ch2);
            if(ch != null && ch != ch1) {
                return false;
            } else if(map.containsKey(ch1)) {
                if(map.get(ch1) != ch2) {
                    return false;
                }
            } else {
                map.put(ch1, ch2);
            }
        }
        return true;
    }

    private Character getKey(final HashMap<Character, Character> map, final char ch2) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if(entry.getValue() == ch2) {
                return entry.getKey();
            }
        }
        return null;
    }
}
