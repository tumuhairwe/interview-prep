package com.tumuhairwe.prep.heap;

import com.tumuhairwe.prep.lists.ListNode;

/**
 * LeetCode 21 Easy
 * Given 2 sorted integer arrays, nums1 and nums2 .. and the number
 * of data elements in each array M andN, implement a function that merges the 2nd array into
 * the first one.
 * Must modify nums1 in place (i.e. can't create a new array to consume space)
 *
 * Solution Summary
 * - Create new node to return e.g. "combined_node"
 * - While both list-1 and list-2 are not null
 *      - Pick which ever is less of the 2 and assign it to current_node
 *      - update <<whichever-list-that-was>> with $listName.next
 *      - forward combined_node i.e. current_node = combined_node.next
 *      - repeat until loop exits (i.e. until 1 of the 2 lists is null ..i.e. reaches end)
 * - for each of the 2 lists,
 *      - forward the list (i.e. currentNode.next = $the_list
 *      - $the_list = $the_list.next
 * - Return combinedNode
 *
 * ref: https://leetcode.com/problems/merge-two-sorted-lists/description/
 * ref: https://www.youtube.com/watch?v=KVf1Uuqfv8E
 */
public class Merge2SortedLists {

    // creating a new List
    public static ListNode mergeLists(ListNode l1, ListNode l2){
        //ListNode tempNode = new ListNode();
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

        //return tempNode.next;
        return current_node;
    }

    /**
     * Solution Summary
     * - Base case: if 1 is null, return the other
     * - Else, find which ever is smaller ...
     * - recursively call merge($smaller_list.next, $otherList)
     * - return the actual ListNode itself
     */
    public ListNode merge2Lists_recursive(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }

        ListNode returnValue = null;
        if(list1.val < list2.val){
            list1.next = merge2Lists_recursive(list1.next, list2);
            returnValue = list1;
        }
        else {
            list2.next = merge2Lists_recursive(list2.next, list2);
            returnValue = list2;
        }

        return returnValue;
    }

    // merges in place
    // see MergeKSortedLists
}
