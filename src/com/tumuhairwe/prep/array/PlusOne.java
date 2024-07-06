package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 69 (easy)
 *
 * You are given a large integer represented as an integer array digits,
 * where each digits[i] is the ith digit of the integer.
 *
 * The digits are ordered from most significant to least significant in left-to-right order.
 * The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 * ref: https://www.youtube.com/watch?v=jIaA8boiG1s
 * ref: https://leetcode.com/problems/plus-one/description/
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        System.out.println(Arrays.toString(arr));

        arr = new int[]{4,3,2,1};
        System.out.println(Arrays.toString(arr));

        arr = new int[]{9};
        System.out.println(Arrays.toString(arr));

        arr = new int[] {9,9,9};
        System.out.println(Arrays.toString(arr));

        arr = new int[]{9,8,7,6,5,4,3,2,1,0};
        System.out.println(Arrays.toString(arr));
    }
    /**
     * Solution summary
     * - iterate the array from the end
     * - if a number == 9 (its gonna carry over), set its value to 0
     * - else increment its value and return immediately
     * - if we reach the end
     * ref: https://leetcode.com/problems/plus-one/solutions/3954062/java-runtime-0-ms-beats-100/
     */
    static int[] plusOne(int[] digits){
        //0. iterate array from the end
        for (int i = digits.length-1; i>=0 ; i--) {
            if (digits[i] == 9){
                digits[i] = 0;
            }
            else {
                digits[i]++;    // this means we reached the end and need to return e.g. 199 -> 200
                return digits;
            }
        }

        // this means we reached the end and need a new array to carry the numbers e.g. 99 -> 100
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }
}
