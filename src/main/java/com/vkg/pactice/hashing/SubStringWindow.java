package com.vkg.pactice.hashing;

import java.util.HashMap;
import java.util.Map;

public class SubStringWindow {
    /**
     * Essentially you have a start and end pointer starting from the beginning of the string.
     * You keep moving the end pointer and including more characters till you have all the characters of T included.
     * At this point, you start moving start pointer and popping out characters till the point
     * that you still have all the characters of T included. Update your answer and keep repeating the process.
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int start = 0;
        int end = 0;
        int from = 0;
        int to = 0;
        Map<Character, Pair> store = initStore(t);
        while(start < s.length() && end < s.length()) {
            while (end < s.length() && !hasAllChars(store)) {
                char ch = s.charAt(end);
                if (store.containsKey(ch)) {
                    store.get(ch).actual++;
                }
                end++;
            }
            boolean found = false;
            while (start < s.length() && hasAllChars(store)) {
                found = true;
                char ch = s.charAt(start);
                if (store.containsKey(ch)) {
                    store.get(ch).actual--;
                }
                start++;
            }

            if (found && (to == 0 || to - from > end - start + 1)) {
                from = start - 1;
                to = end;
            }
        }


        return s.substring(from, to);
    }

    private HashMap<Character, Pair> initStore(final String t) {
        final HashMap<Character, Pair> store = new HashMap<Character, Pair>();
        for (char ch : t.toCharArray()) {
            Pair pair = store.get(ch);
            if (pair == null) {
                pair = new Pair();
                store.put(ch, pair);
            }
            pair.expected++;
        }
        return store;
    }

    private boolean hasAllChars(final Map<Character, Pair> store) {
        for (Pair p : store.values()) {
            if(p.expected > p.actual) {
                return false;
            }
        }
        return true;
    }

    class Pair {
        int expected;
        int actual;
    }
}

