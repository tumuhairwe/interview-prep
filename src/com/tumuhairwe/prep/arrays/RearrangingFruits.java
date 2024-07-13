package com.tumuhairwe.prep.arrays;

import java.util.*;

/**
 * LeetCode 2561 (hard)
 *
 * You have two fruit baskets containing n fruits each
 * You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket.
 * You want to make both baskets equal. To do so, you can use the following operation as many times as you want:
 *
 * Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
 * The cost of the swap is min(basket1[i],basket2[j]).
 * Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
 *
 * Return the minimum cost to make both the baskets equal or -1 if impossible.
 */
public class RearrangingFruits {

    public static void main(String[] args) {
        int[] arr1 = {4,2,2,2};
        int[] arr2 = {1,4,1,2};
        System.out.println(minCost_impl2(arr1, arr2));

        int[] arr3 = {2,3,4,1};
        int[] arr4 = {3,2,5,1};
        System.out.println(minCost_impl2(arr3, arr4));
    }
    /**
     * Solution summary
     * - Create a unified Tree FrequencyMap of 2 arrays into 1
     * - for each entry,
     *      - if frequency is not modulo 2 -> return false (can't be swapped)
     *      - get (frequency/swapCount) modulo 2 and add to List_of_swaps
     * - accumulate the number of swaps into a result
     * - return result
     */
    public static long minCost(int[] basket1, int[] basket2){
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for(int b1 : basket1){
            countMap.merge(b1, 1, Integer::sum);
            //countMap.merge(b1, 1, (Integer a, Integer b) -> a+b );
        }

        for(int b2 : basket2){
            countMap.merge(b2, -1, Integer::sum);
            //countMap.merge(b2, 1, (Integer a, Integer b) -> a+b );
        }

        List<Integer> swaps = new ArrayList<>();
        long res = 0;
        long small = countMap.firstKey();

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()){
            if(entry.getValue() % 2 > 0){  // freqMap must have 0 or exactly even count for entry to be swappable
                return -1;
            }

            int swapCount = Math.abs(entry.getValue()) / 2;
            for (int i = 0; i < swapCount; i++) {
                swaps.add(entry.getKey());
            }
        }

        for (int i = 0; i < swaps.size(); i++) {
            res += Math.min(swaps.get(i), small * 2);
        }

        return res;
    }

    // https://leetcode.com/problems/rearranging-fruits/solutions/3147420/greedy-solution-in-java/
    public static long minCost_impl2(int[] basket1, int[] basket2) {
        //0. create a freqMap for both bastkets into 1
        Map<Integer, Integer> freqMap = new HashMap<>();
        long min = Long.MAX_VALUE;

        for (int i = 0; i < basket2.length; i++) {
            freqMap.put(basket1[i], freqMap.getOrDefault(basket1[i], 0) + 1);
            freqMap.put(basket2[i], freqMap.getOrDefault(basket2[i], 0) - 1);

            min = Math.min(min, basket1[i]);
            min = Math.min(min, basket2[i]);
        }

        //1.
        long result = 0;
        List<Integer> swaps = new ArrayList<>();
        for(int key : freqMap.keySet()){
            // if frequency !even .. can't be swapped
            if(freqMap.get(key) % 2 != 0){
                return -1;
            }

            int diff = Math.abs(freqMap.get(key));
            diff = diff / 2;

            while (diff-- > 0) {
                swaps.add(key);
                System.out.print(key + " ");
            }
        }

        //2.
        Collections.sort(swaps);
        for (int i = 0; i < (swaps.size() / 2); i++) {
            result += Math.min(2 * min, swaps.get(i));
        }

        return result;
    }
}