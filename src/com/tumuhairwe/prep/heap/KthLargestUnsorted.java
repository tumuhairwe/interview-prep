package com.tumuhairwe.prep.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find the Kth Largest Value in a stream. Note that its the Kth element in sorted order
 * not the Kth distinct element
 *
 * LeetCode 703
 * ref: https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
 * ref: https://www.youtube.com/watch?v=m6zDTNzC1bA&list=PLB7ZlVMcmjIAMEeI0p4BAeCeZvu2xxdVu
 */
public class KthLargestUnsorted {

    private PriorityQueue<Integer> topKHeap;
    private int max;

    // constructor take O(n log_k) -> call the constructor N times
    public KthLargestUnsorted(int k, int[] nums){
        this.max = k;
        this.topKHeap = new PriorityQueue<>();

        // iterating thru all elements = O(n)
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);   // takes O (log_k)
        }
    }

    public int add(int val){
        if(topKHeap.size() < max){
            topKHeap.add(val);
        }
        else {
            if(val > topKHeap.peek()){   // heap will always maintain the top K largest elements .. call po() .. or peek() will get the head of the Quer i.e. Kth largest)
                topKHeap.poll(); // remove highest element of the stream
                topKHeap.add(val);
            }
        }

        return topKHeap.peek();  // wull return smallest
    }

    // accessing teh top of the heap  (the smallest if sorted in PQ) ...
    // to get largest (i.e. end of Q, construct PQ with Comparator.reverseOrder() ... and poll()
    public int getMax(){
        return this.topKHeap.peek();
    }
    // iterating thru all elements = O(n)
    public int getMin(){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.reverseOrder());
        this.topKHeap.forEach(x -> minHeap.add(x));
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 8, 2};
        KthLargestUnsorted solution = new KthLargestUnsorted(3, arr);   // Space Compleixyt = O(k) where k == size
        System.out.println("The 3rd LARGEST element in array is " + solution.getMax());
        System.out.println("The 3rd SMALLEST element in array is " + solution.getMin());
    }
}
