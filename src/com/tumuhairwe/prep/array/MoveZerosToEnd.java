package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 283
 * ref: https://leetcode.com/problems/move-zeroes/description/
 *
 * Given a static-sized array of integers arr, move all zeroes in the array to the end of the array.
 * You should preserve the relative order of items in the array.
 *
 * We should implement a solution that is more efficient than a naive brute force.
 *
 * Examples:
 *
 * input:  arr = [1, 10, 0, 2, 8, 3, 0, 0, 6, 4, 0, 5, 7, 0]
 * output: [1, 10, 2, 8, 3, 6, 4, 5, 7, 0, 0, 0, 0, 0]
 * Constraints:
 *
 * [time limit] 5000ms
 * [input] array.integer arr
 * 0 ≤ arr.length ≤ 20
 * [output] array.integer
 */
public class MoveZerosToEnd {
    static int[] moveZerosToEnd(int[] arr) {

        int p1 = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] != 0){
                arr[p1] = arr[i];
                p1++;

                System.out.println("p=" + p1 + ", arr[i]=" + arr[i] + ", i=" + i);
            }
        }

        if(arr.length > 0){
            Arrays.fill(arr, p1, arr.length, 0);
        }

        return arr;
    }
}
