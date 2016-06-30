package com.vkg.pactice.list;

public class LinkedNodeList {
    public ListNode partition(ListNode A, int B) {
        ListNode lessPrev = null;
        ListNode greaterPrev = null;
        ListNode head = A;
        ListNode greaterHead = null;

        while (A != null) {
            if (A.val < B) {
                if (lessPrev != null) {
                    lessPrev.next = A;
                    lessPrev = A;
                } else {
                    lessPrev = A;
                    head = A;
                }
            } else {

                if (greaterPrev != null) {
                    greaterPrev.next = A;
                    greaterPrev = A;
                } else {
                    greaterPrev = A;
                    greaterHead = A;
                }

            }

            A = A.next;
        }

        if (greaterPrev != null)
            greaterPrev.next = null;

        if (lessPrev != null) {
            lessPrev.next = greaterHead;
        }

        return head;
    }

    public ListNode swapPairs(ListNode a) {
        ListNode curr = a;
        ListNode prev = new ListNode(0);
        prev.next = curr;
        a = prev;
        while(curr != null && curr.next != null) {
            prev.next = curr.next;
            curr.next = curr.next.next;
            prev.next.next = curr;
            prev = curr;
            curr = curr.next;
        }

        return a.next;
    }

    public ListNode reverseBetween(ListNode a, int m, int n) {
        ListNode curr = a;
        ListNode prev = new ListNode(0);
        prev.next = curr;
        a = prev;

        int i = 1;
        ListNode mid = null;
        ListNode midLast = null;
        while(curr!=null) {
            if(i >= m && i <= n) {
                prev.next = curr.next;
                curr.next = mid;
                mid = curr;
                curr = prev.next;
                if(midLast == null) {
                    midLast = mid;
                }
            } else if(i > n) {
                break;
            } else {
                prev = curr;
                curr = curr.next;
            }

            i++;
        }

        if(midLast != null) midLast.next = prev.next;
        prev.next = mid;

        return a.next;
    }
}
