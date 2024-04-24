package com.tumuhairwe.prep.heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// implementation of 2 heaps
public class MedianFinder {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));
    //PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    private PriorityQueue<Integer> small; // maxHeap
    private PriorityQueue<Integer> large; // maxHeap

    public MedianFinder(){
        // same
        //PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        this.small = new PriorityQueue<>(Collections.reverseOrder());   // order by putting smallest on top

        // same
        //PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        this.large = new PriorityQueue<>(Comparator.comparingInt(a -> a));   // order by putting largest on top
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
