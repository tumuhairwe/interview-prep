package com.tumuhairwe.prep.heap;

import com.tumuhairwe.prep.lists.ListNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 23 (Hard)
 *
 * Description:
 * You are given an arrayof K linkedLists, each linkedList is sorted in ascending order
 * Merge all the linkedLists into one sorted linked-list and return it
 *
 * ref: https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * Solution:
 *  - Use a min heep
 *  ref: https://www.youtube.com/watch?v=L-8LVBPmIpc
 *
 *  Solution
 *      - Use 2 pointers (p1 and p2 starting from last-index of either array
 *      ref: https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-merge-sorted-array
 *
 * Solution:
 * - Initialize 2 pointers (pointing to the last data element in both arrays)
 * - Initialize 3rd pointer that points to the last index of nums1 (list1)
 * - Traverse nums2 from the end (i--) using the 3rd pointer and compare values corresponding to the 1st 2 pointers
 * - Place the larger of the 2 values at the 3rd pointer's index
 * - repeat the process until the 2 arrays are merged
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 7, 92, 0, 0, 0};
        int m = 4;

        int[] nums2 = new int[]{3, 4, 5};
        int n = 3;
        int[] merged = mergeSorted(nums1, m, nums2, n);
        System.out.println("Given nums1 = " + Arrays.toString(nums1) + "m=" + m );
        System.out.println("Given nums2 = " + Arrays.toString(nums2) + ", n=" + n);
        System.out.println("Merged = " +Arrays.toString(merged));

        System.out.println("--------------");
        nums1 = new int[]{1, 12, 57, 98, 0, 0, 0, 0};
        m = 4;

        nums2 = new int[]{1, 4, 5, 57};
        n = 4;
        merged = mergeSorted(nums1, m, nums2, n);
        System.out.println("Given nums1 = " + Arrays.toString(nums1) + "m=" + m );
        System.out.println("Given nums2 = " + Arrays.toString(nums2) + ", n=" + n);
        System.out.println("Merged = " +Arrays.toString(merged));
    }

    // use 2 pointers
    // TC = O(m + n)  where m & n == the counts of the initialized/populated elements in the 2 arrays
    // SC = O(1) == because we don't use any extra space
    public static int[] mergeSorted(int[] nums1, int m, int[] nums2, int n){
        // 0. init pointers
        int p1 = m - 1; // $populated_entries_of_nums1.length - 1
        int p2 = n - 1; // nums2.length - 1 // assumes all of nums2 is populated

        // 1. init pointer p
        int p_index = (m + n) - 1;;
        //int p = p_index;    // aka nums1.length i.e. all populated + unpoulated slots

        // 2. traverse the array from the end
        // 2 a i) if p1 > p2, set value at p to p1
        // 2 a ii) nums1[p] = p1;
        // 2 a iii) decrement p1 (i.e. p1-- )

        // 2 b i) if p1 < p2, set value at p to p1
        // 2 b ii) nums1[p] = p2;
        // 2 b iii) decrement p2 (i.e. p2-- )

        for (int p = p_index; p >0; p--) {
            if(p2 < 0){
                break;
            }

            if(p1 >= 0 && nums1[p1] > nums2[p2]){   // 2 a i
                nums1[p] = nums1[p1];   // 2a II
                p1--;   // 2 a iii
            }
            else {
                nums1[p] = nums2[p2];
                p2--;
            }
        }

        return nums1;
    }

    // use minHeap / PQ
    public ListNode mergekLists(ListNode[] lists){
        // these are the same
        Queue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        //Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists){
            if(node == null){
                continue;
            }
            minHeap.add(node);
        }

        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (!minHeap.isEmpty()){
            ListNode top = minHeap.poll();
            current.next = top;
            current = current.next;

            if(top.next != null){
                minHeap.add(top.next);
            }
        }
        return dummy.next;
    }
}
