package com.tumuhairwe.prep.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

// implementation of 2 heaps

/**
 * LeetCode 295 (hard)
 * Find median of 2 sorted arrays
 * The overall run time complexity should be O(log (m+n)).
 *
 * Solution Summary:
 * - Create 2 PQs (minHeap & maxHeap) -- minHeap==order-ascending (i.e. smallest on top and order-descending
 * - Initialize both arrays by calling insert() for each element in arr1
 * - Initialize both arrays by calling insert() for each element in arr2
 * - return getMedian() to get the median
 *
 * ref: https://leetcode.com/problems/find-median-from-data-stream/
 * ref: https://www.youtube.com/watch?v=itmhHWaHupI
 *
 * TC
 * - Adding/Removing to any heap: log_n
 * - Finding the max in madHeap = O(1)
 * - Finding the min in minHeap = O(1)
 */
public class MedianFinder {

    // maxHeap: (ordered by smallest at the top
    private PriorityQueue<Integer> maxHeap; // order by putting smallest on top; // maxHeap
    // minHeap: (ordered by largest at the top)
    private PriorityQueue<Integer> minHeap;  // order by putting largest on top

    public MedianFinder(){
        // same
        Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        //smallHeap = new PriorityQueue<>(comp.reversed());
        maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // same
        //minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        minHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void insert(int num){
        // push to the max heap & swap with minHeap if needed
        maxHeap.add(num);
        int val;
        if(!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
            val = maxHeap.poll();
            minHeap.add(val);
        }

        // handle uneven size -> pick from 1 give to another
        if(maxHeap.size() > minHeap.size() + 1){
            val = maxHeap.poll();
            minHeap.add(val);
        }
        else if (minHeap.size() > maxHeap.size() + 1){
            val = minHeap.poll();
            maxHeap.add(val);
        }
    }

    public double findMedian(){
        // if even number of elements -> add top() of each heap & divide by 2 to get average of middle 2
        if(maxHeap.size() == minHeap.size()){
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        }
        if(maxHeap.size() > minHeap.size()){
            return (double) maxHeap.peek();
        }
        else{
            return minHeap.peek();
        }
    }
}
