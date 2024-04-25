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
 * ref: https://leetcode.com/problems/sliding-window-maximum/description/
 * ref: https://www.interviewkickstart.com/problems/sliding-window-maximum
 */
public class SlidingWindowMaximum {
    // time complexity = I)k * (n-k))
    LinkedList<Character> d = new LinkedList<>();
    
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        int k=3;    // size of sliding window
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        int windowStart = 0;

        ArrayDeque<Integer> result = new ArrayDeque<>();
        for (int windowEnd=0; windowEnd<input.length; windowEnd++){
//            for (int windowStart = 0; windowStart < k; windowStart++) {
//                Arrays.s
//                int max = Math.max(input[j], input[j+1]);
//            }
        }
        System.out.println(Arrays.asList(input));
    }

    // complexity: O(n * k) --- where nn = length of array and k === windowSiee
    int[] bruteForceMaxSlidingWindow(int[] numsa, int k){
        int n = numsa.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i < n - k; i++) {
            int max = numsa[i];

            for (int j = 0; j < i + k; j++) {
                max = Math.max(max, numsa[j]);
            }
            result[i] = max;
        }
        return result;
    }

    // Time Complexity = O(n)
    // Space Complexity = O(k)
    int[] maxSlidingWindow(int[] nums, int k){
        // need to be able to pop from both ends of the list
        LinkedList<Integer> index = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // i -k === current_window_size
            // pop from the head all numbers less than WINDOW_SIZE
            while (!index.isEmpty() && index.getFirst() <= i - k){
                index.removeFirst();
            }

            // pop from the tail
            while (!index.isEmpty() && nums[i] >= nums[index.getLast()]){
                index.removeLast();
            }

            if(i >= k - 1){
                result.add(nums[index.getFirst()]);
            }
        }
        // same
        Comparator<Map.Entry<Character, Integer>> c = Comparator.comparingInt(Map.Entry::getValue);
        Comparator<Map.Entry<Integer, Integer>> entryComparator1 = (Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) -> {
            return entry1.getValue() - entry2.getValue();
        };

        return result.stream().mapToInt(x -> x).toArray();
    }
    List<Integer> maxSlidingWindow_deque(int[] nums, int k){
        List<Integer> output = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();  // will contain indices
        int windowStart = 0, windowEnd = 0;

        while (windowEnd < nums.length){
            // pop smaller values from Q
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[windowEnd]){
                deque.pop();
            }

            // remove left val from window
            if(windowStart + 1 > k){
                deque.removeFirst();
            }

            if(windowEnd + 1 > k){
                output.add(nums[deque.removeFirst()]);
            }

        }
        return output;
    }
}
