package com.vkg.pactice.list;

import org.junit.Assert;
import org.junit.Test;

public class LinkedDigitsTest {

    @Test
    public void shouldAdd() throws Exception {
        final ListNode a = createList(0);
        final ListNode b = createList(1, 0 ,1);
        ListNode expected = createList(1, 0 ,1);
        LinkedDigits digits = new LinkedDigits();
        ListNode result = digits.addTwoNumbers(a, b);
        while(expected != null) {
            Assert.assertEquals(expected.val, result.val);
            result = result.next;
            expected = expected.next;
        }
    }

    @Test
    public void shouldAddWithCarry() throws Exception {
        final ListNode a = createList(1);
        final ListNode b = createList(9, 9, 9);
        ListNode expected = createList(0, 0, 0 ,1);
        LinkedDigits digits = new LinkedDigits();
        ListNode result = digits.addTwoNumbers(a, b);
        while(expected != null) {
            Assert.assertEquals(expected.val, result.val);
            result = result.next;
            expected = expected.next;
        }
    }

    private ListNode createList(final int... vals) {
        ListNode head = null;

        for (int i = vals.length - 1; i >= 0; i--) {
            int val = vals[i];
            ListNode node = new ListNode(val);
            node.next = head;
            head = node;
        }
        return head;
    }

}