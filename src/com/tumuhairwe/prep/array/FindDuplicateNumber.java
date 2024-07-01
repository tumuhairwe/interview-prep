package com.tumuhairwe.prep.array;

/**
 * LeetCode 287 (medium)
 *
 *  Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * ref: https://www.youtube.com/watch?v=wjYnzkAhcNk
 * ref: https://leetcode.com/problems/find-the-duplicate-number/description/
 */
public class FindDuplicateNumber {
    public static void main(String[] args) {
        int[] arr = {1,3,4,2,2};
        System.out.println(findDuplicate(arr));
    }

    /**
     * Solution summary (Floyd's cycle detection algorithm)
     * - Create & move 2 pointers (fast and slow). Move fast at twice the speed until they're equal
     * - Create a 3rd pointer and move it at same speed until they're equal
     * - return either one (slow or slow2 are now equal)
     *
     * - This algo is based on the assumption that all the numbers in the array point to indices within the array
     * - When 1 pointer is moved at twice the speed, it'll eventually collide with the slow moving pointer
     * - The 2nd loop moves the previously slow pointer at the same pace and moves the 3rd pointer starting at zero
     * - when 2 pointers move together (1 starting at previous_slow_index, and 1 starting at 0) ... when they catch up
     *  (i.e. have equal value) it'll be at the duplicate ... return that duplicate (either pointer)
     */
    private static int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);

        int slow2 = 0;
        do{
            slow = nums[slow];
            slow2 = nums[slow2];
        }while (slow != fast);

        return slow;
    }
}
