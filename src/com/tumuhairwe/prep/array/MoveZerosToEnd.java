package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 283
 * ref: https://leetcode.com/problems/move-zeroes/description/
 * ref: https://www.youtube.com/watch?v=aayNRwUN3Do&t=11s
 *
 * Given a static-sized array of integers arr, move all zeroes in the array to the end of the array.
 * You should preserve the relative order of items in the array.
 *
 * We should implement a solution that is more efficient than a naive brute force.
 *
 * Examples:
 * input:  arr = [1, 10, 0, 2, 8, 3, 0, 0, 6, 4, 0, 5, 7, 0]
 * output: [1, 10, 2, 8, 3, 6, 4, 5, 7, 0, 0, 0, 0, 0]
 *
 * Solution Summary
 * - Iterate thru the whole array using 2 pointers (fast & slow)
 * - Find the non-zero number -> swap it with slow-pointer & move slowPointer++
 * - Repeat until whole array is traversed
 */
public class MoveZerosToEnd {

    // method signature == do this in place
    static void moveZerosToEnd(int[] arr) {

        int leftPointer = 0;
        // iterate thru array
        for(int rightPointer=0; rightPointer<arr.length; rightPointer++){

            // find non-zero number
            if(arr[rightPointer] != 0){

                // swap it with rightPointer/slowPointer
                int temp = arr[rightPointer];
                arr[leftPointer] = arr[rightPointer];
                arr[rightPointer] = temp;
                leftPointer++;

                System.out.println("leftPointer=" + leftPointer + ", arr[i]=" + arr[rightPointer] + ", rightPointer=" + rightPointer);
            }
        }

        if(arr.length > 0){
            Arrays.fill(arr, leftPointer, arr.length, 0);
        }
    }
}
