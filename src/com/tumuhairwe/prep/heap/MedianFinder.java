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
    private PriorityQueue<Integer> smallHeap; // order by putting smallest on top; // maxHeap
    // minHeap: (ordered by largest at the top)
    private PriorityQueue<Integer> largeHeap;  // order by putting largest on top

    public MedianFinder(){
        // same
        Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        //smallHeap = new PriorityQueue<>(comp.reversed());
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

        // handle uneven size -> pick from 1 give to another
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
