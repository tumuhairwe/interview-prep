package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * LeetCode 75 (medium)
 * Sort Colors
 *
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same
 * color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 */
public class SortColors {

    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        new SortColors().sortColors(arr);
        System.out.println("Sorted: -> " + Arrays.toString(arr));

        arr = new int[]{2,0,1};
        new SortColors().sortColors(arr);
        System.out.println("Sorted: -> " + Arrays.toString(arr));
        TreeSet<Integer> set = new TreeSet<>();
        TreeMap<Integer, String> map = new TreeMap<>();
    }
    /**
     * Intuition:
     * The problem requires sorting an array of colors, where each color is represented by a number (0, 1, or 2).
     * The Dutch National Flag algorithm is a well-known approach for efficiently sorting an array of three distinct elements.
     * The intuition behind this algorithm is to maintain three pointers: low, current, and high, representing the boundaries of three sections: 0s, 1s, and 2s.
     * The goal is to move 0s to the left, 2s to the right, and 1s will automatically be in the middle.
     *
     * Solution Summary
     * - init 3 pointers (2 at beginning, 1 at end)
     * - iterate thru the array
     *      - If nums[current] is 0, swap it with the element at the low pointer, increment both low and current.
     *      - If nums[current] is 2, swap it with the element at the high pointer, decrement high.
     *      - If nums[current] is 1, just increment current.
 *      - Continue this process until current exceeds high.
     *
     *  At the end, all zeros will be to the right, and all twos will be to the left
     *
     *  TC: O(n)
     *  SC: O(1)
     */
    public void sortColors(int[] nums){
        // create 3 pointers, low current and high
        int low = 0;
        int current = 0;
        int high = nums.length - 1;

        while (current <= high){
            if (nums[current] == 0){
                swap(nums, low, current);
                low++;
                current++;
            }
            else if (nums[current] == 2){
                swap(nums, high, current);
                high--;
            }
            else {
                current++;
            }
        }
    }

    void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
