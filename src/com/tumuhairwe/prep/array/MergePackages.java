package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Merging 2 Packages
 * Given a package with a weight limit and an array arr of item weights,
 * implement a function getIndicesOfItemWeights
 * that finds two items whose sum of weights equals the weight limit.
 *
 * Your function should return a pair [i, j] of
 * the indices of the item weights, ordered such that i > j. If such a pair doesn’t exist, return an empty array.
 *
 * Analyze the time and space complexities of your solution.
 *
 * Example:
 * input:  arr = [4, 6, 10, 15, 16],  lim = 21
 * output: [3, 1] # since these are the indices of the
 *                # weights 6 and 15 whose sum equals to 21
 *
 * e.g. [4, 6, 10, 10, 4, 15, 16]  limit: 21
 *
 * ref:https://www.educative.io/answers/how-to-solve-the-merging-two-packages-problem
 * ref: https://www.pramp.com/challenge/L3wQBnQYAEh5K97W9ONK
 */
public class MergePackages {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 10, 15, 16};
        int target = 21;
        int[] results = getIndicesOfItemWeights(arr, target);
        System.out.println(Arrays.toString(results));
    }

    /*
    * Solution Summary
    *
    * 0. Sort the array
    * 1. Loop thru the array ... store the difference between an item-weight && limit in a Map
    *       2. Map { key: difference, value: index }
    * 3. return the indices of the matching weights
    *
    * TC: O(n) == where n == length of array
    * SC: O(n) == where n == size of array
    *
    * TC: O(n)
    * SC: O(n)
  */
    static int[] getIndicesOfItemWeights(int[] arr, int limit) {
        // create vars
        Map<Integer, Integer> map = new HashMap<>(); // key = diff, value = index

        //0. sort array
        Arrays.sort(arr);

        // 1. iterate thru the array and populate Map (key=diff and value == index
        for(int i=0; i< arr.length; i++) {
            int diff = limit - arr[i];

            // arr = [4, 6, 4,10, 15, 16],  lim = 21 === [{17 : 0} {15 : 1}, 11 : 2, 6 : 3, 5 : 4 ]
            if (map.containsKey(diff)) {
                //map.put(arr[i], i);   // { key: 17, value = 0}, {15, 1}
                return new int[]{i, map.get(diff)};
            }
            map.put(arr[i],i);
        }

            /**
             * static int[] getIndicesOfItemWeights(int[] arr, int limit) {
             *     // your code goes here
             *     Map<Integer,Integer> map = new HashMap<>();
             *
             *     for(int i = 0; i < arr.length; i++){
             *
             *       if(map.get(limit - arr[i]) != null){
             *         return new int[]{i,map.get(limit - arr[i])};
             *       }
             *       map.put(arr[i],i);
             *     }
             *     return new int[]{};
             *   }
             */

        int[] a = new int[0];
        return a;
    }
}
