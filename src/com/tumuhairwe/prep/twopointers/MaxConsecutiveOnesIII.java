package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 1004 (medium)
 * Given a binary array nums and an integer k, return the max number of consecutive 1s in the array
 * if you can filip at most K zeros
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        int[] arr = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println("Should be 6 " + longestOnes(arr, 2));

        int[] arr2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        System.out.println("Should be 10 " + longestOnes(arr, 3));
    }

    /**
     * Solution summary
     * - init 2 vars (maxOnes and numZeros = 0)
     * - iterate over nums
     *    - increment numZeros is nums[end=] == 0
     *     - while numZeros > k, slide window forward
     *  - update maxOnes with windowSize;
     */
    static int longestOnes(int[] nums, int k){
        int maxOnes = 0;
        int numZerosFlipped =0;
        int start = 0;
        //0. iterate over nums
        for (int end = 0; end < nums.length; end++) {

            //1. increment maxNum if nums[end] = 1;
            if(nums[end] == 0){
                numZerosFlipped++;
            }

            //2. slide window forward if num ZerosFlipped > k
            while (numZerosFlipped > k){
                if(nums[start] == 0){
                    numZerosFlipped--;
                }
                start++;
            }

            // update maxOnes with max(ones, windowSize)
            int windowSize = end - start  + 1;
            maxOnes = Math.max(maxOnes, windowSize);
        }
        return  maxOnes;
    }
}
