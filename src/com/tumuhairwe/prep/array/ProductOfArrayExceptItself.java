package com.tumuhairwe.prep.array;

/**
 * LeetCode 238
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * ref: https://leetcode.com/problems/product-of-array-except-self/description/
 * ref: https://www.youtube.com/watch?v=bNvIQI2wAjk
 */
public class ProductOfArrayExceptItself {

    /**
     * Solution Summary
     * - Calculate products of all numbers to the left of i -> store in array (seed left[0] with 1)
     * - Calculate products of all numbers to the right of i -> store in array (seed right[nums.length- 1] with 1
     * - populate output[i] by multiplying left[i] * right[i]
     */
    public int[] productExceptSelf(int[] nums){
        //0. declare vars
        int[] output = new int[nums.length];
        int[] left_products = new int[nums.length];
        int[] right_products = new int[nums.length];

        //0. seed & loop forward
        left_products[0] = 1;
        for (int i = 1; i < nums.length - 1; i++) {
            left_products[i] = nums[i - 1] * left_products[i - 1];
        }

        //1. seed and loop backward
        for (int i = nums.length -2; i >=0; i--) {
            right_products[i] = nums[i + 1] * nums[i + 1];
        }

        //2. populate output
        for (int i = 0; i < nums.length; i++) {
            output[i] = left_products[i] * right_products[i];
        }
        return output;
    }
    /**
     * Solution Summary
     * - create output[] if nums.length size
     * - Seed output[0] = 1
     * - iterate forward: Calculate prefix for each element (
     *      output[i + 1] = output[i] * nums[i]
     * - Iterate backward: calculate suffix for each element
     * - return output
     */
    public int[] productExceptSelfNumAsPrefix(int[] nums){
        int[] output = new int[nums.length];

        // 0. iterate forward
        for (int i = 0; i < nums.length - 1; i++) {
            output[i + 1] = output[i] * nums[i];
        }

        // 1. iterate backwards
        for (int i = nums.length - 2; i >=0; i--) {
            output[i] = nums[i + 1] * output[i];
            nums[i] = nums[i] * nums[i + 1];
        }

        return output;
    }
}
