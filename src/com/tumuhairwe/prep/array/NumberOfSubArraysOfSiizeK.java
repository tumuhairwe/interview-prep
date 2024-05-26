package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * Given an array of integers arr, and 2 integers k and threshold,
 * reeturn the number of sub-arrays of size K with an average of >= threshold
 * ref: https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/
 * ref: https://www.youtube.com/watch?v=D8B4tKxMTnY
 *
 * Solution Summary
 * - sort array
 * - pre-calculate the summary of the 1st K entries in array
 * - 2. loop over array & [expand-window /  recalculate-sum/ shrink-window]
 * - update current sum (if condition met)
 * - return result
 */
public class NumberOfSubArraysOfSiizeK {

    public static void main(String[] args) {
        int[] arr = new int[]{2,2,2,2,5,5,5,8};
        int result = numOfSubArrays(arr, 3, 4);
        System.out.println(Arrays.toString(arr) + " has " + result + " sub arrays of size k=" + 3 + " where avg>=" + 4);
    }
    public static int numOfSubArrays(int[] arr, int k, int threshold){
        int result = 0;

        // 0. calculate current sum (of the 1st k entries)
        int currentSum = 0;
        for (int i = 0; i < k - 1; i++) {
            currentSum += arr[i];
        }

        // 2. loop over array & [expand-window /  recalculate-sum/ shrink-window]
        for (int i = 0; i < arr.length - k + 1; i++) {

           // 3. expand window
           currentSum += arr[i + k - 1];

           // 4. update result if threshold is met
           if ((currentSum / k) >= threshold){
               result++;
           }

           // 5. update current sim
            currentSum -= arr[i];
        }

        return result;
    }
}
