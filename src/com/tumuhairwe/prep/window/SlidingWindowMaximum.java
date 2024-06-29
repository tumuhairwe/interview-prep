package com.tumuhairwe.prep.window;

import java.util.*;

/**
 * Given an array of numbers
 * ... and a number K
 * ... find the maximum number that can fit in a window of size
 * K
 * Window is moving  left to right
 * LeetCode 239 (Hard)
 *
 * Solution:
 *
 * ref: https://www.youtube.com/watch?v=z9e-tGD7Z8g
 * ref: https://www.youtube.com/watch?v=DfljaUwZsOk
 * ref: https://www.youtube.com/watch?v=9BwZAwrYC7c
 * ref: https://www.youtube.com/watch?v=DfljaUwZsOk
 * ref: https://leetcode.com/problems/sliding-window-maximum/description/
 * ref: https://www.interviewkickstart.com/problems/sliding-window-maximum
 */
public class SlidingWindowMaximum {
    // time complexity = (O)k * (n-k))
    LinkedList<Character> d = new LinkedList<>();
    
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        int k=3;    // size of sliding window
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        //System.out.println(Arrays.asList(input));
        System.out.println(maxSlidingWindow_deque(input, 3));
    }

    // Time Complexity = O(n)
    // Space Complexity = O(k)
    int[] maxSlidingWindow(int[] nums, int k){
        // need to be able to pop from both ends of the list
        LinkedList<Integer> indices_dq = new LinkedList<>();
        Deque<Integer> que = new ArrayDeque<>();

        List<Integer> result = new ArrayList<>();
        //int numberOfWindows = nums.length - k + 1;
        //int[] result = new int[numberOfWindows];

        for (int i = 0; i < nums.length; i++) {
            // i -k === current_window_size
            // if smaller values exist in queue, pop them
            // 3 remove indices that are out of bounds
            // 3a) pop from the head all numbers less than WINDOW_SIZE
            while (!indices_dq.isEmpty() && indices_dq.peekFirst() <= i - k) {
                indices_dq.pollFirst();    // remove from font of queue
            }

            // remove indices whose corresponding value is less than num[i]
            //3b pop from the tial all number > nums[i]
            while (!indices_dq.isEmpty() && nums[indices_dq.peekLast()] < nums[i]) {
                indices_dq.pollLast();
            }

            // add index to window
            indices_dq.offer(i);

            // add to result

            if(i >= k - 1){
                result.add(nums[indices_dq.getFirst()]);
            }
        }
        return result.stream().mapToInt(x -> x).toArray();
    }

    static List<Integer> maxSlidingWindow_deque(int[] nums, int k){
        List<Integer> output = new ArrayList<>();
        Deque<Integer> indices_dq = new ArrayDeque<>();  // will contain indices
        int windowStart = 0, windowEnd = 0;

        while (windowEnd < nums.length){
            // pop smaller values from Q
            while (!indices_dq.isEmpty() && nums[indices_dq.getLast()] < nums[windowEnd]){
                indices_dq.pop();
            }

            // remove left val from window
            if(windowStart + 1 > k){
                indices_dq.removeFirst();
            }

            if(windowEnd + 1 > k){
                output.add(nums[indices_dq.removeFirst()]);
            }
        }
        return output;
    }
}
