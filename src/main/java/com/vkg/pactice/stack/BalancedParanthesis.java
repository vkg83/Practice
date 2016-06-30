package com.vkg.pactice.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BalancedParanthesis {
    public int isValid(String a) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack<Character>();

        for(char ch : a.toCharArray()) {
            if(map.containsKey(ch)) {
                stack.push(ch);
            } else if(stack.isEmpty() || ch != map.get(stack.pop())) {
                return 0;
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
