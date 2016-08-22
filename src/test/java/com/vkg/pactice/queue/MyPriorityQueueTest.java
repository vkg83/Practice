package com.vkg.pactice.queue;

import org.junit.Assert;
import org.junit.Test;

public class MyPriorityQueueTest {
    @Test
    public void shouldName() throws Exception {
        MyPriorityQueue queue = new MyPriorityQueue();
        queue.add(5);
        queue.add(7);
        queue.add(6);
        queue.add(9);
        queue.add(3);
        queue.add(1);
        queue.add(10);
        queue.print();
        Assert.assertEquals(10, queue.remove());
        Assert.assertEquals(9, queue.remove());
        Assert.assertEquals(7, queue.remove());
        Assert.assertEquals(6, queue.remove());
        Assert.assertEquals(5, queue.remove());
        Assert.assertEquals(3, queue.remove());
        Assert.assertEquals(1, queue.remove());
    }
}