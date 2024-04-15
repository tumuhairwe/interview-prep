package com.tumuhairwe.prep.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 703 easy
 *
 * ref: https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
 * ref: https://www.youtube.com/watch?v=m6zDTNzC1bA&list=PLB7ZlVMcmjIAMEeI0p4BAeCeZvu2xxdVu
 */
// TC = O (k + (n - k) x log_k)  -> O(n -k) x log_k)
public class KthLargestSorted {

    public static int findKthLargest(int[] nums, int k){
        // these 2 are the same
        Comparator<Integer> c = (n1, n2) -> n1 - n2;
        Comparator<Integer> comp = Comparator.comparingInt(n -> n);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(comp);
        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }
        return minHeap.peek();
    }
    // Driver code
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 5, 12, 2, 11, 9, 7, 30, 20},
                {5, 2, 9, -3, 7},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 4, 6, 0, 2},
                {3, 5, 2, 3, 8, 5, 3}
        };

        int[] K = {3, 1, 9, 1, 4};

        for(int i=0; i<K.length; i++){
            System.out.print(i+1);
            System.out.println(".\tInput array: "+ Arrays.toString(inputs[i]));
            System.out.println("\tValue of k: "+ K[i]);
            System.out.println("\tkth largest element: " + findKthLargest(inputs[i], K[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}