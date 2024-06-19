package com.tumuhairwe.prep.lists;

/**
 * LeetCode 206
 *
 * ref: https://leetcode.com/problems/reverse-linked-list/description/
 * ref: https://www.youtube.com/watch?v=G0_I-ZF0S38
 */
public class ReverseLinkedList {


    //TC = O(n)
    //SC = O(1)
    public void reverse_iterative(ListNode head){
        ListNode previous = null;
        ListNode current = head;

        while (current != null){
            ListNode temp = current.next;

            current.next = previous;
            previous = current;
            current = temp;
        }
    }
    public void reverse_recursive(ListNode node){

    }
}
