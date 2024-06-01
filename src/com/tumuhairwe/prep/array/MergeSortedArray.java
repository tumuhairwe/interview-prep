package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * Merge 2 sorted arrays into a new arrays
 * LeetCode 88 (Easy)
 *
 * Solution Summary
 * 1. loop over both arrays with 2 pointers  (p1 < arr1.length && p2 < arr2.length)
 * 2. if (arr1[p1] < arr[p2]) -> put arr1[p1] into result array and increment counter
 * 3. else -> put arr2[p2] into result array and increment counter
 * 4. At the end merge the trailing pointer into the result
 *
 * ref: https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 6, 7, 8};

        int[] result = merge(arr1, arr2);

        System.out.println("Arr1");
        System.out.println(Arrays.toString(arr1));

        System.out.println("Arr2");
        System.out.println(Arrays.toString(arr2));

        System.out.println("Result");
        System.out.println(Arrays.toString(result));
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        //int arrIndex = arr1.length >= arr2.length ? arr1.length : arr2.length;
        int firstArrayCounter =0, secondArrayCounter = 0, resultIndexCounter = 0;

        while (firstArrayCounter < arr1.length && secondArrayCounter < arr2.length){
            if (arr1[firstArrayCounter] < arr2[secondArrayCounter]){
                result[resultIndexCounter++] = arr1[firstArrayCounter++];
            }
            else
                result[resultIndexCounter++] = arr2[secondArrayCounter++];
        }

        // store the remaining elements of first array
        while (firstArrayCounter < arr1.length){
            result[resultIndexCounter++] = arr1[firstArrayCounter++];
        }
        while (secondArrayCounter < arr2.length){
            result[resultIndexCounter++] = arr2[secondArrayCounter++];
        }
        return result;
    }
}
