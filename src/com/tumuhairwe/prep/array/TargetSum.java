package com.tumuhairwe.prep.array;

/**
 * LeetCode 494 Target Sum
 *
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * ref: https://leetcode.com/problems/target-sum/description/?envType=problem-list-v2&envId=plakya4j
 * ref: https://leetcode.com/problems/target-sum/solutions/5148779/java-simple-intuition-easy-to-understand-beginner-friendly/?envType=problem-list-v2&envId=plakya4j
 */
public class TargetSum {

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1};
        System.out.println("Should be 5: " + findTargetSumWays(arr, 3));
    }
    public static int findTargetSumWays(int[] nums, int target){
        int n = nums.length;
        return totalWays(nums, target, n-1);
    }

    /**
     * Solution summary
     * - if we're at the zero-th index,
     *      - if (target - nums[i] == 0) -> count++
     *      - if (target + nums[i] == 0) -> count++
     * - recursively call both scenarios addition and subtraction (passing the index-1)
     * - sum (number of way from addition + num_way_subtraction)
     * - return sum
     */
    private static int totalWays(int[] nums, int target, int index) {
        // base case
        if(index == 0){
            int ways = 0;
           if(target - nums[index] == 0){
               ways++;
           }
           if(target + nums[index] == 0){
               ways++;
           }
           return ways;
        }
        int num_way_addition = totalWays(nums, target - nums[index], index - 1);
        int num_way_subtraction = totalWays(nums, target + nums[index], index - 1);
        return num_way_addition + num_way_subtraction;
    }
}
