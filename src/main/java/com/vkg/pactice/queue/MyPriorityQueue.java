package com.vkg.pactice.queue;

import java.util.ArrayList;

public class MyPriorityQueue {
    ArrayList<Integer> queue = new ArrayList<>();

    public void print() {
        int max = 1;
        int end = 0;
        final int size = queue.size();
        while (end < size) {
            for (int i = end; i < max + end; i++) {
                System.out.print(" " + queue.get(i));
            }
            System.out.println();
            end = max + end;
            max *= 2;
        }
    }

    public void add(int value) {
        queue.add(value);
        shiftUp(queue.size() - 1);
    }

    public int remove() {
        if(queue.isEmpty()) {
            new RuntimeException("Empty queue");
        }
        int value = queue.get(0);
        int last = queue.remove(queue.size() - 1);
        if(queue.size() >= 1) {
            queue.set(0, last);
            shiftDown(0);
        }

        return value;
    }

    private void shiftDown(final int pos) {
        int child = getChild(pos);

        if(child < 0 || queue.get(pos) >= queue.get(child)) {
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
        if(left < size && right < size && queue.get(left) < queue.get(right)) {
            result = right;
        }

        return result;
    }

    private void shiftUp(final int pos) {
        int parent = getParent(pos);
        if(parent < 0 || queue.get(parent) >= queue.get(pos)) {
            return;
        }

        swap(pos, parent);

        shiftUp(parent);
    }

    private void swap(final int pos, final int parent) {
        int tmp = queue.get(pos);
        queue.set(pos, queue.get(parent));
        queue.set(parent, tmp);
    }

    private int getParent(final int pos) {
        return (pos - 1) / 2;
    }
}
