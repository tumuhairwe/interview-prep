package com.tumuhairwe.prep.window;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * LeetCode 1696 (medium)
 *
 * You are given a 0-indexed array nums and an integer k
 * You are initially standing at index 0, in 1 move you can jump at most K steps forward
 * without going outside the boundaries of the array
 * i.e. you can jump from index i to any index in the range of [i+1, min(n-1, i+k) ] inclusive
 *
 * You want to reach the last index of the array (index n-1). Your score is the sum of all nums[j] for each index j
 *  you visited in the array.
 *
 *  Return the maximum score you can get
 */
public class JumpGameVI {

    public static void main(String[] args) {
        int[] arr = {1,-1,-2,4,-7,3};
        System.out.println(maxResult(arr, 2));
    }

    /**
     * Solution summary (dq-based sliding window impl) -- used dp to return score at target=nums.length-1
     * - Create and seed a dq_of_indices (seed with 0)
     * - Create and seed scores dp array (seed with nums[0])
     * - traverse nums[] starting from index 1 ...
     *      - remove from the front of the dq indices that are out of bounds of the sliding window
     *      - set score[i] = score[dq.peek()] + nums[i];
     *      - remove from monotonically decreasing dq if score_at_ > score_dp.peek()
     *      - add index to dq
     * - when you exit, score will have value at nums.length - 1;
     */
    static int maxResult(int[] nums, int k){
        //0. base case
        if (nums.length == 0){
            return 0;
        }

        //1. create and seed scores
        int windowSize = k;
        int[] score_dp = new int[nums.length];
        score_dp[0] = nums[0];

        // create and seed indices dq
        Deque<Integer> indices_dq = new ArrayDeque<>();
        indices_dq.offer(0);

        //2. iterate over nums (starting with 1)
        for (int i = 1; i < nums.length; i++) {
            //3. pull off old indices
            while (!indices_dq.isEmpty() && indices_dq.peekFirst() < i - windowSize){
                indices_dq.pollFirst();
            }

            //4. update scores[]
            score_dp[i] = score_dp[indices_dq.peek()] + nums[i];

            //5. pull off indices too large (to maintain monotonically increasing que)
            while(!indices_dq.isEmpty() && score_dp[i] > score_dp[indices_dq.peek()]){
                indices_dq.pollLast();
            }

            indices_dq.offer(i);
        }
        return score_dp[nums.length - 1];
    }

    public int maxResult_pq_impl(int[] nums, int k){
        //0. create indices pq (key=score, val=index)
        Comparator<int[]> comp = (int[] a, int[] b) -> b[0] - a[0];
        PriorityQueue<int[]> indices_pq = new PriorityQueue<>(comp);
        indices_pq.add(new int[]{nums[0], 0});  // key=score, val=index

        int[] scores = new int[nums.length];
        int windowSize = k;

        //1. iterate all of nums (from index 1)
        for (int i = 1; i < nums.length; i++) {
            // remove index that is out of boundary
            while (indices_pq.peek()[1] < i - windowSize){
                indices_pq.remove();
            }

            // update scores[]
            scores[i] = scores[indices_pq.peek()[1]] + nums[i];

            //2. add (index, score) to pq
            indices_pq.add(new int[]{scores[i], i});
        }
        return scores[nums.length - 1];
    }
}
