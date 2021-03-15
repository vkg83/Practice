package com.vkg.pactice.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NearestSmallerElement {
    public ArrayList<Integer> prevSmaller(List<Integer> arr) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        for(int val : arr) {
            while(!stack.isEmpty() && val <= stack.peek()) {
                stack.pop();
            }

            int newVal = stack.isEmpty() ? -1 : stack.peek();
            result.add(newVal);
            stack.add(val);
        }

        return result;
    }
}
