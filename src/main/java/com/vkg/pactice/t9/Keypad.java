package com.vkg.pactice.t9;

import java.util.HashMap;
import java.util.Map;

public class Keypad {
    private Map<Integer, String> keymap = new HashMap<Integer, String>();
    private Integer toggleChar;
    private int lower;

    public void assign(final int i, final String chars) {
        keymap.put(i, chars);
    }

    public String convert(final int ... keys) {
        String text = "";
        int start = 0;
        while(start < keys.length) {
            int charIndex = getCharIndex(keys, start);
            if (charIndex == -1) {
                start += 1;
                continue;
            }
            text += keymap.get(keys[start]).charAt(charIndex);
            start += charIndex + 1;
        }
        return text;
    }

    private int getCharIndex(final int[] keys, final int start) {
        int key = keys[start];
        int index = 0;
        int charLength = getCharLength(key);
        while (index < charLength && start + index < keys.length && key == keys[start + index]) index++;

        return index - 1;
    }

    public String convert2(final int ... keys) {
        return getText(keys, 0);
    }

    private String getText(final int[] keys, final int start) {
        if (start >= keys.length) {
            return "";
        }

        int key = keys[start];

        int index = 0;
        if(key == toggleChar) {
            toggleMode();
        } else {
            int charLength = getCharLength(key);
            while (index < charLength && start + index < keys.length && key == keys[start + index]) index++;
        }

        String ch = "";
        if(index > 0) {
            ch += getaChar(key, index);
        } else {
            index++;
        }

        return  ch + getText(keys, start + index);
    }

    private char getaChar(final int key, final int index) {
        return (char)(keymap.get(key).charAt(index - 1) + lower);
    }

    private void toggleMode() {
        lower = lower == 0 ? 'a' - 'A' : 0;
    }

    private int getCharLength(final int key) {
        return keymap.get(key).length();
    }

    public void assignToggle(final int i) {
        toggleChar = i;
    }
}
