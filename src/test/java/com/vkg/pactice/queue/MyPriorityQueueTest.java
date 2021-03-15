package com.vkg.pactice.queue;

import org.junit.Assert;
import org.junit.Test;

public class MyPriorityQueueTest {
    @Test
    public void shouldName() throws Exception {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(5);
        queue.add(7);
        queue.add(6);
        queue.add(9);
        queue.add(3);
        queue.add(1);
        queue.add(10);
        queue.print();
        Assert.assertEquals(Integer.valueOf(10), queue.remove());
        Assert.assertEquals(Integer.valueOf(9), queue.remove());
        Assert.assertEquals(Integer.valueOf(7), queue.remove());
        Assert.assertEquals(Integer.valueOf(6), queue.remove());
        Assert.assertEquals(Integer.valueOf(5), queue.remove());
        Assert.assertEquals(Integer.valueOf(3), queue.remove());
        Assert.assertEquals(Integer.valueOf(1), queue.remove());
    }
    @Test
    public void shouldSwapDown() throws Exception {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
        queue.add(5);
        queue.add(7);
        queue.add(6);
        queue.print();
        Assert.assertEquals(Integer.valueOf(7), queue.remove());
        Assert.assertEquals(Integer.valueOf(6), queue.remove());
        Assert.assertEquals(Integer.valueOf(5), queue.remove());
    }
}