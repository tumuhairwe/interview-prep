package com.tumuhairwe.prep.array;

/**
 * Given 2 arrays (nums1 and nums2) merge them into 1 array
 *
 * Solution Summary:
 * - Create output_array of length arr1.length + arr2.length
 * - Use 2 pointers to loop over both input arrays & merge both arrays into output_array
 * - while p1 < arr1.length, copy arr1[p1] into output array
 * - while p2 < arr2.length, copy arr2[p2] into output array
 */
public class MergeSortedArray2 {

    // Merge arr1 and arr2 into resultantArray
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        //int lengthOfArray1 = arr1.length;
        //int lengthOfArray2 = arr2.length;

        // 0. create output_array
        int[] output = new int[arr1.length + arr2.length];
        int p1 = 0, p2 = 0, idx = 0;

        // 1. Traverse both arrays -> merge both arrays into output_array
        while (p1 < arr1.length && p2 < arr2.length) {
            // Check if current element of first
            // array is smaller than current element
            // of second array. If yes, store first
            // array element and increment first array
            // index. Otherwise do same with second array
            if (arr1[p1] < arr2[p2])
                output[idx++] = arr1[p1++];
            else
                output[idx++] = arr2[p2++];
        }

        // 2. Store remaining elements of first array
        while (p1 < arr1.length)
            output[idx++] = arr1[p1++];

        // 3.Store remaining elements of second array
        while (p2 < arr2.length)
            output[idx++] = arr2[p2++];

        return output;
    }

    public static void main(String args[]) {

        int[] arr1 = {1,12,14,17,23}; // creating a sorted array called arr1
        int[] arr2 = {11,19,27};  // creating a sorted array called arr2

        int[] resultantArray = mergeArrays(arr1, arr2); // calling mergeArrays

        System.out.print("Arrays after merging: ");
        for(int i = 0; i < arr1.length + arr2.length; i++) {
            System.out.print(resultantArray[i] + " ");
        }
    }
}
