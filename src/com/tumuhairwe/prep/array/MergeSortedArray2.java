package com.tumuhairwe.prep.array;

public class MergeSortedArray2 {

    // Merge arr1 and arr2 into resultantArray
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int lengthOfArray1 = arr1.length;
        int lengthOfArray2 = arr2.length;

        int[] resultantArray = new int[lengthOfArray1+lengthOfArray2];
        int firstArrayIndex = 0, secondArrayIndex = 0, k = 0;

        // Traverse both array
        while (firstArrayIndex < lengthOfArray1 && secondArrayIndex < lengthOfArray2) {
            // Check if current element of first
            // array is smaller than current element
            // of second array. If yes, store first
            // array element and increment first array
            // index. Otherwise do same with second array
            if (arr1[firstArrayIndex] < arr2[secondArrayIndex])
                resultantArray[k++] = arr1[firstArrayIndex++];
            else
                resultantArray[k++] = arr2[secondArrayIndex++];
        }

        // Store remaining elements of first array
        while (firstArrayIndex < lengthOfArray1)
            resultantArray[k++] = arr1[firstArrayIndex++];

        // Store remaining elements of second array
        while (secondArrayIndex < lengthOfArray2)
            resultantArray[k++] = arr2[secondArrayIndex++];

        return resultantArray;
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
