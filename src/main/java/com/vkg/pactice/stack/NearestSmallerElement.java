package com.vkg.pactice.stack;

import java.util.ArrayList;
import java.util.Stack;

public class NearestSmallerElement {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> arr) {
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
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
