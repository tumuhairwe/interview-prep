package com.tumuhairwe.prep.lists;

/**
 * LeetCode 2 (medium)
 * Add Two numbers
 *
 * Given 2 non-empty lists representing 2 non-negative integers. The digits are stored in reverse ordere
 * and each of their nodes is a single digit. Add the 2 numbers and return the sum ans
 * a linked List
 * You may assume the 2 numbers do not contan any leading zero, except the number 0 itself
 *
 * LeetCode: https://leetcode.com/problems/add-two-numbers/description/?envType=problem-list-v2&envId=plakya4j
 */
public class AddTwoNumbers {
    public ListNode addTwoNumber(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        int carry = 0;
        while (l1 != null || l2 != null){
            // forward both lists
            if(l1 != null){
                carry = carry + l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                carry = carry + l2.val;
                l2 = l2.next;
            }

            // calculate the value and append it to dummy.next
            int val = carry % 10;
            carry = carry/10;

            // calculate the carry and carry it forward
            dummy.next = new ListNode(val);
            dummy = dummy.next;
        }

        // if the carry is non-zero, append it to dummy.next
        if(carry > 0){
            dummy.next = new ListNode(carry);
        }

        //return head since its already pointing to the 1st dummy node (created b4 it was forwarded)
        return head.next;
    }
}
