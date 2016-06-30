package com.vkg.pactice.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubStringConcat {
    public ArrayList<Integer> findSubstring(String a, final List<String> b) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(a == null || a.length() == 0 || b == null || b.isEmpty()) {
            return result;
        }

        Map<String, Integer> expectedFrequencyMap = new HashMap<String, Integer>();

        for (String word : b) {
            changeFrequency(expectedFrequencyMap, word, 1);
        }

        int wordLen = b.get(0).length();

        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> actualFrequencyMap = new HashMap<String, Integer>();
            int count = 0;
            int start = i;
            for (int j = i; j <= a.length() - wordLen; j+=wordLen) {
                String part = a.substring(j, j + wordLen);
                if(expectedFrequencyMap.containsKey(part)) {
                    changeFrequency(actualFrequencyMap, part, 1);
                    count++;
                    while(actualFrequencyMap.get(part) > expectedFrequencyMap.get(part)) {
                        start = removeLeftWord(a, wordLen, actualFrequencyMap, start);
                        count--;
                    }

                    if(count == b.size()) {
                        result.add(start);
                        start = removeLeftWord(a, wordLen, actualFrequencyMap, start);
                        count--;
                    }
                } else {
                    actualFrequencyMap.clear();
                    count = 0;
                    start = j + wordLen;
                }
            }
        }

        return result;
    }

    private int removeLeftWord(final String a, final int wordLen, final Map<String, Integer> tempFrequencyMap, int start) {
        String pre = a.substring(start, start + wordLen);
        changeFrequency(tempFrequencyMap, pre, -1);
        return start + wordLen;
    }

    private void changeFrequency(final Map<String, Integer> frequencyMap, final String word, int delta) {
        Integer frequency = delta;
        if (frequencyMap.containsKey(word)) {
            frequency += frequencyMap.get(word);
        }

        frequencyMap.put(word, frequency);
    }
}
