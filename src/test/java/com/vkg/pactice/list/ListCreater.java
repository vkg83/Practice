package com.vkg.pactice.list;

public class ListCreater {
    public ListCreater() {
    }

    static ListNode createList(final int... values) {
        return createList(null, values);
    }

    static ListNode createList(final ListNode c, final int... values) {
        ListNode head = c;

        for (int i = values.length - 1; i >= 0; i--) {
            int val = values[i];
            ListNode node = new ListNode(val);
            node.next = head;
            head = node;
        }
        return head;
    }
}