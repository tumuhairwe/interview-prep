package com.tumuhairwe.prep.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.function.Predicate;

// implementation of 2 heaps
public class MedianFinder {

    private PriorityQueue<Integer> small; // maxHeap
    private PriorityQueue<Integer> large; // maxHeap

    public MedianFinder(){
        this.small = new PriorityQueue<>(Collections.reverseOrder());   // order by putting smallest on top
        this.large = new PriorityQueue<>();   // order by putting largest on top
    }

    public void insert(int num){
        // push to the max heap & swap with minHeap if needed
        small.add(num);
        int val;
        if(!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()){
            val = small.poll();
            large.add(val);
        }

        // hanbdle uneven size
        if(small.size() > large.size() + 1){
            val = small.poll();
            large.add(val);
        }
        else if (large.size() > small.size() + 1){
            val = large.poll();
            small.add(val);
        }
    }

    public double getMedian(){
        if(small.size() > large.size()){
            return small.peek();
        }
        else if(large.size() > small.size()){
            return large.peek();
        }

        //  even number of elements -> divide by 2 to get average of middle 2
        double median = (small.peek() + large.peek()) / 2;
        return median;
    }
}
