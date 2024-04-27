package com.tumuhairwe.prep.array;


/**
 * LeetCode 167 (medium)
 *
 * Given a 1-indexed array of integers numbers that is sorted in non-decreasing oder,
 * Find 2 numbers such that they add up too a specific target numbers.
 *
 * They numbers such that numbers[i] and numbers[j] are 1 <= j <= j <= numbers.length
 *
 * Solution Summary:
 *  - initialize 2 pointers to (p1 = 0, p2 = nums.length - 1)
 *  - when sum of sum[p1] + nums[p2] == target return
 *  - when sum of sum[p1] + nums[p2] > target, decrement p2
 *  - when sum of sum[p1] + nums[p2] > target, increment p1
 */
public class TwoSumInSortedArray {
    public static void main(String[] args) {
        int[] numbers = new int[]{2,7,11,15};
        int target = 9;
        System.out.println("Result is " + getTwsoSum(numbers, target));
    }

    private static int[] getTwsoSum(int[] numbers, int target) {
        int pointer_b = numbers.length - 1;
        int pointer_a = 0;

        // base case
        while (pointer_a < pointer_b){
            int currentSum = numbers[pointer_a] + numbers[pointer_b];
            if (currentSum == target){
                return new int[]{pointer_a + 1, pointer_b + 1};
            }
            else if(currentSum > target){
                pointer_b--;
            }
            else {
                pointer_a++;
            }
        }

        return new int[]{-1, -1};
    }
}
