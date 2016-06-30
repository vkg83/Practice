package com.vkg.pactice.list;

/**
 * Definition for singly-linked list.
 */

public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(val).append("=>").append(next);
        return sb.toString();
    }
}
