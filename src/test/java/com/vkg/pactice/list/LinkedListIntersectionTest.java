package com.vkg.pactice.list;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListIntersectionTest {

    @Test
    public void shouldGetIntersection() throws Exception {
        LinkedListIntersection intersection = new LinkedListIntersection();

        final ListNode c = ListCreater.createList(5, 2, 3);
        final ListNode a = ListCreater.createList(c, 1);
        final ListNode b = ListCreater.createList(c);
        ListNode node = intersection.getIntersectionNode(a, b);

        Assert.assertEquals(c, node);
    }

    @Test
    public void shouldGetIntersection2() throws Exception {
        LinkedListIntersection intersection = new LinkedListIntersection();

        final ListNode c = ListCreater.createList(36, 1, 94, 100, 70, 83, 78, 34, 59, 79);
        final ListNode a = ListCreater.createList(c, 83, 44, 39, 95, 100);
        final ListNode b = ListCreater.createList(c, 35, 97, 71, 32, 13, 11, 12, 15);
        ListNode node = intersection.getIntersectionNode(a, b);

        Assert.assertEquals(c, node);
    }
}