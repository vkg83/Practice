package com.vkg.pactice.stack;

public class ArrayBasedStack<E> {
    private Object[] list;
    private int currentSize;

    public ArrayBasedStack(int capacity) {
        list = new Object[capacity];
    }

    public void push(E item) {
        if(currentSize >= list.length) {
            throw new IllegalStateException("Stack full!");
        }

        list[currentSize++] = item;
    }

    public E pop() {
        if(currentSize <= 0) {
            throw new IllegalStateException("Empty Stack!");
        }
        return (E)list[--currentSize];
    }
}
