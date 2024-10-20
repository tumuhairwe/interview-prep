package com.tumuhairwe.prep.lists;

/**
 * LeetCode 2095
 * You are given the head of a LinkedList. Delete the middle node && return the head of the modified LinkedList
 * The middle node of a LinkedList of sizern is the [n/2]-the node from the start using a 0-based indexing
 * where |x| denotes the largest integer less than or equal to 0
 * ref: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
 */
public class DeleteMiddleNodeOfLinkedList {
    public ListNode deleteMiddle(ListNode head) {
        //TC: O(n)
        //SC: O(1)
        //0. base case
        if(head == null){
            return null;
        }

        //1. initialize 3 pointers
        ListNode prev = new ListNode(0);
        prev.next = head;

        ListNode slow = prev;
        ListNode fast = head;

        //2 move the 2 pointers forward. Exit when the fast reaches the end
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //3. skip/delete slow.next by replacing it with slow.next.next
        slow.next = slow.next.next;
        return prev.next;
    }
}
