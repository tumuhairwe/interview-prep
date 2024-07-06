package com.tumuhairwe.prep.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 1207 (easy)
 *
 * Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.
 *
 * ref: https://leetcode.com/problems/unique-number-of-occurrences/description/
 */
public class UniqueNumberOfOccurrences {

    public static void main(String[] args) {
        int[] arr1 = {1,2,2,1,1,3};
        System.out.println("Should be true " + uniqueOccurrences(arr1));

        int[] arr2 = {1,2};
        System.out.println("Should be false " + uniqueOccurrences(arr2));   // each number occurs 1

        int[] arr3 = {-3,0,1,-3,1,1,1,-3,10,0};
        System.out.println("Should be false " + uniqueOccurrences(arr3));   // each number occurs 1
    }
    /**
     * Solution summary
     * - Create frequencyMap of arr
     * - put all keys into a set
     * - put all values into a set
     * - return where the 2 sets are equal is size
     */
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int existingCount = freqMap.getOrDefault(arr[i], 0);
            freqMap.put(arr[i], existingCount + 1);
        }

        Set<Integer> distinctFrequencies = new HashSet<>(freqMap.values());
        Set<Integer> distinctValues = freqMap.keySet();
        return distinctFrequencies.size() == distinctValues.size();
    }
}
