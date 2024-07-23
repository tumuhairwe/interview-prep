package com.tumuhairwe.prep;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Leetcode 1429 (medium)
 *
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 *
 * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 * int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
 * void add(int value) insert value to the queue.
 */
public class FirstUniqueNumber {
    private Map<Integer, Integer> freqMap;
    private Queue<Integer> queue;

    /**
     * Initialize a frequency map & queue
     * map == track frequency of numbers
     * que == track FIFO/when added (requirement: "first unique number")
     */
    public FirstUniqueNumber(int[] nums) {
        freqMap = new HashMap<>();
        queue = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    /**
     * While !que.isEmpty() .. peek and and find the first uniqe value
     * i.e. value in frequencyMap with numOccurence == 1
     */
    public int showFirstUnique() {
        while (!queue.isEmpty()) {
            int next = queue.peek();
            if(unique(next)){
                return next;
            }
            else{
                queue.poll();
            }
        }
        return -1;
    }

    /**
     * If a value doesn't exist, add it to the que
     * - add value to frequencyMap
     */
    public void add(int value) {
        if(!exists(value)){
            queue.add(value);
        }
        freqMap.put(value, freqMap.getOrDefault(value, 0) + 1);
    }

    boolean exists(int value){
        return freqMap.containsKey(value);
    }

    boolean unique(int value){
        return freqMap.containsKey(value) && freqMap.get(value) == 1;
    }
}
