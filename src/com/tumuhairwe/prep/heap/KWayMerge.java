package com.tumuhairwe.prep.heap;

import com.tumuhairwe.prep.lists.ListNode;

/**
 * Given 2 sorted integer arrays, nums1 and nums2 .. and the number
 * of data elements in each array M andN, implement a function that merges the 2nd array into
 * the first one.
 * Must modify nums1 in place (i.e. can't create a new array to consume space)
 *
 * LeetCode 21 Easy
 * ref: https://leetcode.com/problems/merge-two-sorted-lists/description/
 *
 * Leet
 * ref: https://www.youtube.com/watch?v=KVf1Uuqfv8E
 */
public class KWayMerge {

    // creating a new List
    public static ListNode mergeLists(ListNode l1, ListNode l2){
        ListNode tempNode = new ListNode();
        ListNode current_node = new ListNode();

        while (l1 != null && l2 != null){
            if(l1.val < l2.val){
                current_node = l1;
                l1 = l1.next;
            }
            else {
                current_node = l2;
                l2 = l2.next;
            }

            current_node = current_node.next;
        }

        if(l1 != null){
            current_node.next = l1;
            l1 = l1.next;
        }

        if(l2 != null){
            current_node.next = l2;
            l2 = l2.next;
        }

        return tempNode.next;
    }

    // merges in place
    // see MergeKSortedLists
}
