package com.tumuhairwe.prep;

import java.util.Map;

public class MinSizeSubArraySum {

    public static void main(String[] args) {
        int result = findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 });
        System.out.println("Smallest subarray length: " + result);
        result = findMinSubArray(8, new int[] { 3, 4, 1, 1, 6 });
        System.out.println("Smallest subarray length: " + result);
        result = findMinSubArray(8, new int[] { 2, 1, 5, 2, 3, 2});
        System.out.println("Smallest subarray length: " + result);
    }

    private static int findMinSubArray(int s, int[] arr){
        int windowStart =0;
        int windowSum = 0;
        int lengthOfSmallestSubArray=Integer.MAX_VALUE;

        for (int windowEnd = 0; windowEnd < arr.length-1; windowEnd++) {
            windowSum += arr[windowEnd];

            while(windowSum >= s){
                lengthOfSmallestSubArray = Math.min(lengthOfSmallestSubArray, windowEnd-windowStart+1);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }

        return lengthOfSmallestSubArray == Integer.MAX_VALUE ? 0 : lengthOfSmallestSubArray;
    }
}
