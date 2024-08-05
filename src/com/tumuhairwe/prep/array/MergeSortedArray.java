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

    /**
     * Merge 2 arrays into 1 -> return the result (same as MergeSortedArray2)
     * Solution summary
     * - Create result[] of combined length
     * - Init 2 pointers to 0 (p1 -> arr1, p2 -> arr2)
     * - while both pointers are less than length of their respsective arrays,
     *      copy + paste the value from arrX - result based on whichever is smallers
     * - copy + paste trailing vals from arr1 into result[]
     * - copy + paste trailing vals from arr2 into result[]
     */
    private static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int p1 = 0, p2 = 0, resultIdx = 0;

        while (p1 < arr1.length && p2 < arr2.length){
            if (arr1[p1] < arr2[p2]){
                result[resultIdx++] = arr1[p1++];
            }
            else
                result[resultIdx++] = arr2[p2++];
        }

        // store the remaining elements of first array
        while (p1 < arr1.length){
            result[resultIdx++] = arr1[p1++];
        }
        while (p2 < arr2.length){
            result[resultIdx++] = arr2[p2++];
        }
        return result;
    }
}
