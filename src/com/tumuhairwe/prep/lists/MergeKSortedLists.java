package com.tumuhairwe.prep.lists;

import java.util.LinkedList;
import java.util.List;

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

}
