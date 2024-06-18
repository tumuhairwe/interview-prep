package com.tumuhairwe.prep.lists;

/**
 * LeetCode 19 (medium)
 *
 * Remove Nth Node From End of List
 * Solution Summary
 * - Create 3 pointers (dummy_pointer points to head as next; fast_pointer to dummy and slow_pointer pointing to head)
 * - move fast_pointer N steps ahead ( decrement n for each iteration in the while loop)
 * - advance both pointers forward (until fast is null)
 * - delete the one node by setting slow.next = slow.next.next
 * - return dummy.head (i.e. head)
 */
public class RemoveNthNodeFromList {
    public static void main(String[] args) {

    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 0. create 3 pointers
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;

        // move fast ahead
        while(n > 0 && fast != null){
            fast = fast.next;
            n--;
        }

        //advance both pointers forward
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        // delete the one ListNode
        slow.next = slow.next.next;

        //return dummy
        return dummy.next;
    }
}
