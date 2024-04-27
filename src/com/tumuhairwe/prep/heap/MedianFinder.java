package com.tumuhairwe.prep.heap;

import java.util.PriorityQueue;

// implementation of 2 heaps

/**
 * LeetCode 4 (hard)
 * Find median of 2 sorted arrays
 * The overall run time complexity should be O(log (m+n)).
 *
 * Solution Summary:
 * - Create 2 PQs (minHeap
 * ref: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 */
public class MedianFinder {

    // maxHeap: (ordered by smallest at the top
    private PriorityQueue<Integer> smallHeap; // order by putting smallest on top; // maxHeap
    // minHeap: (ordered by largest at the top)
    private PriorityQueue<Integer> largeHeap;  // order by putting largest on top

    public MedianFinder(){
        smallHeap = new PriorityQueue<>((a, b) -> b - a);

        // same
        //minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        largeHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void insert(int num){
        // push to the max heap & swap with minHeap if needed
        smallHeap.add(num);
        int val;
        if(!smallHeap.isEmpty() && !largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek()){
            val = smallHeap.poll();
            largeHeap.add(val);
        }

        // handle uneven size
        if(smallHeap.size() > largeHeap.size() + 1){
            val = smallHeap.poll();
            largeHeap.add(val);
        }
        else if (largeHeap.size() > smallHeap.size() + 1){
            val = largeHeap.poll();
            smallHeap.add(val);
        }
    }

    public double findMedian(){
        // if even number of elements -> add top() of each heap & divide by 2 to get average of middle 2
        if(smallHeap.size() == largeHeap.size()){
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        }
        if(smallHeap.size() > largeHeap.size()){
            return (double) smallHeap.peek();
        }
        else{
            return largeHeap.peek();
        }
    }
}
