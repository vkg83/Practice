package com.vkg.pactice.stack;

import java.util.Stack;

public class MinStack {
    Stack<Data> stack;
    public void push(int x) {
        Data data = new Data(x, x);
        if(!stack.isEmpty() && stack.peek().min < x) {
            data.min = stack.peek().min;
        }

        stack.push(data);

    }

    public void pop() {
        if(!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int top() {
        if(!stack.isEmpty()) {
            return stack.peek().val;
        }

        return -1;
    }

    public int getMin() {
        if(!stack.isEmpty()) {
            return stack.peek().min;
        }

        return -1;
    }
}

class Data {
    int val;
    int min;

    public Data(final int val, final int min) {
        this.val = val;
        this.min = min;
    }
}
