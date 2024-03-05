package com.tumuhairwe.prep;

public class MaxSumOfSubArrayOfSizeK {

    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size K: "
                + findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
        System.out.println("Maximum sum of a subarray of size K: "
                + findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));
    }
    private static int findMaxSumSubArray(int k, int[] arr){
        int windowSum = 0, maxSum = 0, windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length-1; windowEnd++) {
            windowSum += arr[windowEnd];
            if(windowEnd >= k){
                windowSum -= arr[windowStart];
                windowStart++;
                maxSum = Math.max(windowSum, maxSum);
            }
        }

        return maxSum;
    }
}
