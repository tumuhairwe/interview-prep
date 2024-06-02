package com.tumuhairwe.prep.lists;

import java.util.*;

/**
 * LeetCode 23 hard
 *
 * Summary of solution:
 * - iterate thru the list 2 at a time and
 *  - set i = 0;
 *  - merge lists.get(i) and lists(i + 1) and
 *  - assign results into resultList to list[i]
 *  - repeat (i.e. previous_merged_list && list[next}
 *  - repeat until all lists.size() i.e. all lists are merged
 *  return final (all merged list)
 *
 * ref: https://www.youtube.com/watch?v=3yvecsuv3iQ
 * ref: https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class MergeKSortedLists {

    public static void main(String[] args) {

    }

    public static class LinkedListNode{
        int data;
        LinkedListNode next;
    }
    static class LinkedList{
        LinkedListNode head;
    }
    LinkedListNode mergeKLists(List<LinkedList> lists){
        if(lists.size() > 0){
            int step = 1;
            while (step < lists.size()){
                for (int i = 0; i < lists.size(); i += step * 2) {
                    LinkedListNode current = lists.get(i).head;
                    LinkedListNode next = lists.get(i + step).head;
                    lists.get(i).head = merge2Lists(current, next);
                }
            }
        }

        return null;
    }
    static LinkedListNode merge2Lists(LinkedListNode head1, LinkedListNode head2){
        return null;
    }

    /**
     * Solution Summary
     * - Create a minHeap that will store sorted ListNodes by val
     * - put all lists[] in into minHeap
     * - Create a dummy head ListNode, Create current ListNode assign it to head
     * - loop while minHeap is not empty & pull top of minHeap & assign it to current
     * - if current.next is not null ... add it to minHeap
     * - Return head.next and the top of the merged listNode
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 0. Create PQ to hold all ListNodes in asc order
        Queue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // 1. put all lists into minHeap
        for(ListNode node : lists){
            if(node == null){
                continue;
            }

            minHeap.add(node);
        }

        ListNode head = new ListNode();
        ListNode current = head;

        // 3. traverse lists & until you reach each lists' end (next = null), put the next list node to pq. PQ will maintain order by val
        while(!minHeap.isEmpty()){
            current.next = minHeap.poll();
            current = current.next;

            if(current.next != null){
                minHeap.add(current.next);  // until list is exhausted, add .next on to PQ
            }
        }

        return head.next;
    }

}
