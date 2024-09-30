package com.tumuhairwe.prep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1512 (easy)
 *
 * Given an array of integers nums, return the number of good pairs
 * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
 *
 * ref: https://www.youtube.com/watch?v=BqhDFUo1rjs
 */
public class NumberOfGoodPairs {

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = freqMap.getOrDefault(nums[i], 0);
            result += count;

            freqMap.put(nums[i], count + 1);
        }

        return result;
    }
}
