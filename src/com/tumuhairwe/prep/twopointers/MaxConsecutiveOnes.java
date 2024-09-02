package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 485 (easy)
 * Given a binary array nums, return the max number of consecutive 1 in the array
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] arr = {1,1,0,1,1,1};
        System.out.println("SShould  be 3 " + findMaxConsecutiveOnes(arr));

        int[] arr2 = {1,0,1,1,0,1};
        System.out.println("SShould  be 2 " + findMaxConsecutiveOnes(arr2));
    }

    /**
     * - create 2 vars(1 to track counter, & 1 to track maxOnes)
     * - iterate thru nums
     * - when nums[i] == 1, counter++, else reset to 0
     * - update maxOnes(counter, maxOnes)
     *
     * TC: O(n)
     * SC: O(1)
     */
    static int findMaxConsecutiveOnes(int[] nums){
        int counter = 0;
        int maxOnes = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1){
                counter++;
            }
            else {
                counter = 0;
            }

            maxOnes = Math.max(maxOnes, counter);
        }
        return maxOnes;
    }
}
