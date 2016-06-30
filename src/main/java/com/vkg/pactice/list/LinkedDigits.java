package com.vkg.pactice.list;

public class LinkedDigits {
    public ListNode addTwoNumbers(ListNode a, ListNode b) {

        ListNode last = new ListNode(0);
        ListNode result = last;
        int carry = 0;
        while(a != null && b != null) {
            int val = carry + a.val + b.val;
            carry = val / 10;
            last.next = new ListNode(val % 10);
            last = last.next;
            a = a.next;
            b = b.next;
        }

        while(a != null) {
            int val = carry + a.val;
            carry = val / 10;
            last.next = new ListNode(val % 10);
            last = last.next;
            a = a.next;
        }
        while(b != null) {
            int val = carry + b.val;
            carry = val / 10;
            last.next = new ListNode(val % 10);
            last = last.next;
            b = b.next;
        }

        if(carry > 0) {
            last.next = new ListNode(carry);
        }
        return result.next;
    }
}
