package com.tumuhairwe.prep.arrays;

import java.lang.reflect.Array;
import java.util.*;

/**
 * LeetCode 1636 (easy)
 *
 * Given an array of integers nums, sort the array in increasing order based on the
 * frequency of the values.
 * If multiple values have the same frequency, sort them in decreasing order.
 *
 * Return the sorted array.
 *
 * ref: https://leetcode.com/problems/sort-array-by-increasing-frequency/description/
 */
public class SortArrayByIncreasingFrequency {

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,2,3};
        System.out.println(Arrays.toString(frequencySort(arr)));

        arr = new int[]{-1,1,-6,4,5,-6,1,4,1};
        System.out.println(Arrays.toString(frequencySort(arr)));
    }
    public static int[] frequencySort(int[] nums){
        //0. create freqMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        //1. convert int[] -> Integer[] -> sort
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Comparator<Integer> comp = (Integer a, Integer b) -> {
            if(freqMap.get(a).equals(freqMap.get(b))){
                return Integer.compare(b, a);
            }

            int aFrequency = freqMap.get(a);
            int bFrequency = freqMap.get(b);
            return Integer.compare(aFrequency, bFrequency);
        };
        Arrays.sort(arr, comp);

        //3. convert back into int[]
        int[] results = new int[nums.length];
        for (int i = 0; i < arr.length; i++) {
            results[i] = arr[i];
        }
        return results;
    }
}
