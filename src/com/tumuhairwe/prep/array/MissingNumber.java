package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 268 (easy)
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 *
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{3,0,1};
        System.out.println(calcMissingNumber(nums));

        nums = new int[]{0,1};
        System.out.println(calcMissingNumber(nums));

        nums = new int[]{9,6,4,2,3,5,7,0,1};
        System.out.println(calcMissingNumber(nums));
    }
    // Solution summary:
    // - Uses formula to determine diff between actual and expected sums
    //tc: O(n)
    static int calcMissingNumber(int[] nums){
        int expectedSum = nums.length * (nums.length + 1)/2;
        int actualSum =0;
        for (int i = 0; i < nums.length; i++) {
            actualSum += nums[i];
        }

        return expectedSum - actualSum;
    }

    /**
     * Solution Summary
     * - Takes O(n) space by putting all the numbers in nums[] into a collection
     * and iterating over nums (o to n-1) to see if any is missing -> if so, return it
     *
     * not recommended
     */
    public int missingNumber(int[] nums){
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int missingNumber = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(i)){
                return i;
            }
        }

        return missingNumber;
    }
}
