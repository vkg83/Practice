package com.vkg.pactice.queue;

import java.util.ArrayList;

public class MyPriorityQueue<E extends Comparable<E>> {
    ArrayList<E> queue = new ArrayList<>();

    public void print() {
        int itemsInLevel = 1;
        int previousEnd = 0;
        final int size = queue.size();
        while (previousEnd < size) {
            int start = previousEnd;
            int end = start + itemsInLevel;
            for (int i = start; i < end; i++) {
                System.out.print(" " + queue.get(i));
            }
            System.out.println();
            previousEnd = end;
            itemsInLevel *= 2;
        }
    }

    public void add(E value) {
        queue.add(value);
        shiftUp(queue.size() - 1);
    }

    public E remove() {
        if(queue.isEmpty()) {
            throw new RuntimeException("Empty queue");
        }
        E value = queue.get(0);
        E last = queue.remove(queue.size() - 1);
        if(queue.size() >= 1) {
            queue.set(0, last);
            shiftDown(0);
        }

        return value;
    }

    private void shiftDown(final int pos) {
        int child = getChild(pos);

        if(child < 0 || queue.get(pos).compareTo(queue.get(child)) >= 0) {
            return;
        }

        swap(pos, child);

        shiftDown(child);
    }

    private int getChild(final int pos) {
        int size = queue.size();
        int left = 2 * pos + 1;
        if(left >= size) return -1;
        int right = left + 1;

        int result = left;
        if(left < size && right < size && queue.get(left).compareTo(queue.get(right)) < 0) {
            result = right;
        }

        return result;
    }

    private void shiftUp(final int pos) {
        int parent = getParent(pos);
        if(parent < 0 || queue.get(parent).compareTo(queue.get(pos)) >= 0) {
            return;
        }

        swap(pos, parent);

        shiftUp(parent);
    }

    private void swap(final int pos, final int parent) {
        E tmp = queue.get(pos);
        queue.set(pos, queue.get(parent));
        queue.set(parent, tmp);
    }

    private int getParent(final int pos) {
        return (pos - 1) / 2;
    }
}
