package com.tumuhairwe.prep.array;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LeetCode 1207 (easy)
 *
 * Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.
 */
public class UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int existingCount = freqMap.getOrDefault(arr[i], 0);
            freqMap.put(arr[i], existingCount + 1);
        }

        Set<Integer> distinctValues = freqMap.values().stream().distinct().collect(Collectors.toSet());
        Collection<Integer> allValues = freqMap.values();
        return distinctValues.size() == allValues.size();
    }
}
