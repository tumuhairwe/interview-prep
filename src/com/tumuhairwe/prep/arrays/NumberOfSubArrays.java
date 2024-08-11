package com.tumuhairwe.prep.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1248 (medium)
 *
 * Given an array of integers nums, and an integer k, a contigous sub-array is called nice if there are k odd numbers on it
 * return the number of nice sub-arrays
 */
public class NumberOfSubArrays {

    public static void main(String[] args) {
        int[] arr = {1,1,2,1,1};
        System.out.println("2 -> " + numberOfSubarrays(arr, 3));

        arr = new int[]{2,4,6};
        System.out.println("0 -> " + numberOfSubarrays(arr, 2));

        arr = new int[]{2,2,2,1,2,2,1,2,2,2};
        System.out.println("2 -> " + numberOfSubarrays(arr, 16));
    }
    /**
     * - create a freqMap of prefixSums
     * - iterate thru the array & accumulate the currentSum of all odd numbers
     * - if the diff (currentSum -k) is in the freqMap, add occurence to result
     * - update freqMap with currentSum frequency
     * - return numSubArrays
     */
    public static int numberOfSubarrays(int[] nums, int k){
        int numSubArrays = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        freqMap.put(0, 1);

        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i] % 2;
            int diff = currentSum - k;
            if(freqMap.containsKey(diff)){
                numSubArrays += freqMap.get(diff);
            }

            freqMap.put(currentSum, freqMap.getOrDefault(currentSum, 0) + 1);
        }
        return numSubArrays;
    }
}
