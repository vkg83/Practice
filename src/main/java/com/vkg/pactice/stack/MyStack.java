package com.vkg.pactice.stack;

import java.util.ArrayList;

public class MyStack<E> {
    private ArrayList<E> list;

    public MyStack() {
        list = new ArrayList<E>();
    }

    public void push(E item) {
        list.add(item);
    }

    public E pop() {
        int size = list.size();
        if(size <= 0) {
            throw new RuntimeException("Empty Stack!");
        }
        E item = list.remove(list.size() - 1);
        return item;
    }
}
