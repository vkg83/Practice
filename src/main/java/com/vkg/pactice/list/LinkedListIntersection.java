package com.vkg.pactice.list;

public class LinkedListIntersection {
    public ListNode getIntersectionNode(ListNode a, ListNode b) {
        int m = length(a);
        int n = length(b);
        int d = n - m;
        if (d < 0) {
            ListNode tmp = a;
            a = b;
            b = tmp;
            d = -d;
        }

        while(d > 0) {
            b = b.next;
            d--;
        }

        while(a != null && b != null) {
            if(a == b) {
                return a;
            }
            a = a.next;
            b = b.next;
        }
        return null;
    }

    private int length(ListNode l) {
        int len = 0;
        while(l != null) {
            len++;
            l = l.next;
        }

        return len;
    }
}

