package com.tumuhairwe.prep.twopointers;

/**
 * Given an array, and a target sum, find 2 numbers whose sum is equal to that
 *
 * ref: https://leetcode.com/problems/finding-pairs-with-a-certain-sum/
 * ref: https://www.basedash.com/blog/the-two-pointers-technique-in-javascript
 */
public class SumOfPair {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 5, 7, 9, 1};
        int[] result = findSumPair(arr, 13);

        System.out.println("Result = " + result[0] + " - "+ result[1]);
    }

    private static int[] findSumPair(int[] arr, int targetSum){
       int leftPointer = 0;
        int rightPointer = arr.length - 1;

        while (leftPointer < rightPointer){
            int currentSum = arr[leftPointer] + arr[rightPointer];
            if(currentSum == targetSum){
                return new int[]{arr[leftPointer], arr[rightPointer] };
            }
            else if(currentSum < targetSum){
                leftPointer++;
            }
            else {
                rightPointer++;
            }
        }
       return null;
    }
}
