package com.tumuhairwe.prep.lists;


/**
 * LeetCode 61 (medium)
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * ref: https://leetcode.com/problems/rotate-list/description/
 * ref: https://www.youtube.com/watch?v=UcGtPs2LE_c
 */
public class RotateLinkedList {

    public ListNode rotateRight(ListNode head, int k){
        //0. base casse
        if(head == null || head.next == null || k == 0){
            return head;
        }

        //1.go to the end of the list .. while you're at it, calculate the length
        int length = 1;
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            length++;
        }

        //2. move the pivot & rotate list (make pivot point to head)
        temp.next = head;

        //3. set K to be a number that is %k (for when k is longer than length)
        k = k % length;
        k = length - 1;

        // rotate the list k times (set tail = tail.next)
        head = temp.next;

        // set temp/tail .next to the end/tail
        temp.next = null;

        return head;
    }
}
