package com.tumuhairwe.prep.array;

/**
 * LeetCode 169 (Easy)
 *
 * Given an array of nums of size N, return the majority element
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * ref: https://leetcode.com/problems/majority-element/description/
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0169-majority-element.java
 * ref: https://www.youtube.com/watch?v=c1B3LZQtZ_s&t=43s
 */
public class MajorityElement {

    public int majorityElement(int[] nums){
        int candidate = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if(count == 0){
                candidate = nums[i];
            }

            count += (nums[i] == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
