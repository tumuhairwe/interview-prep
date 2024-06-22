package com.tumuhairwe.prep.array;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1426 (easy) Counting Elements
 * Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.
 * If there are duplicates in arr, count them separately.
 *
 * ref: https://leetcode.com/problems/counting-elements/description/
 */
public class CountingElements {

    /**
     * Solution summary
     * - put all nums in a Set
     * - iterate thru the array and check if set.contains =(n +1)
     */
    public int countElements(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int x : arr) {
            set.add(x);
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i] + 1)) {
                count++;
            }
        }

        return count;
    }
}
