package com.vkg.pactice.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedNodeListTest {

    private LinkedNodeList nodeList;

    @Before
    public void setUp() throws Exception {
        nodeList = new LinkedNodeList();
    }

    @Test
    public void shouldPartition() throws Exception {
        ListNode list = ListCreater.createList(5, 2, 3, 4, 6, 8, 1, 9);
        ListNode actual = nodeList.partition(list, 4);
        ListNode expected = ListCreater.createList(2, 3, 1, 5, 4, 6, 8, 9);
        while(expected != null) {
            Assert.assertEquals(expected.val, actual.val);
            actual = actual.next;
            expected = expected.next;
        }
    }

    @Test
    public void shouldSwapPairs() throws Exception {
        ListNode list = ListCreater.createList(5, 2, 3, 4, 6, 8, 1, 9);
        ListNode expected = ListCreater.createList(2, 5, 4, 3, 8, 6, 9, 1);
        ListNode actual = nodeList.swapPairs(list);

        while(expected != null) {
            Assert.assertEquals(expected.val, actual.val);
            actual = actual.next;
            expected = expected.next;
        }

    }

    @Test
    public void shouldReverseBetween() throws Exception {
        ListNode list = ListCreater.createList(5, 2, 3, 4, 6, 8, 1, 9);
        ListNode expected = ListCreater.createList(5, 2, 8, 6, 4, 3, 1, 9);
        ListNode actual = nodeList.reverseBetween(list, 3, 6);

        while(expected != null) {
            System.out.print("->" + actual.val);
            Assert.assertEquals(expected.val, actual.val);
            actual = actual.next;
            expected = expected.next;
        }
    }
}