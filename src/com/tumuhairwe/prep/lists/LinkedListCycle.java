package com.tumuhairwe.prep.lists;

/**
 * LeetCode 141 (easy)
 * ref: https://leetcode.com/problems/linked-list-cycle/description/
 *
 * Solution Summary
 * - Create 2 pointers [fast and slow]. Slow -> head, Fast -> head.next
 * - while (fast != slow) -> move fast = fast.next.next, slow = slow.next
 * - if there's a cycle, at some point fast will == slow .
 * - Terminate condition
 *      - return false if any  of them reaches end (null
 *      - return true if they equal
 */
public class LinkedListCycle {

    private boolean hasCycle(ListNode head){
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow){
                return true;
            }
        }

        return false;
    }
}
