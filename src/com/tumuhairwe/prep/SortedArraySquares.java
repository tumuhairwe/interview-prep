package com.tumuhairwe.prep;

/**
 * LeetCode 977 (Easy)
 *
 * Given an integer array nums, sorted in non-decreasing order,
 * return an array of the squares of each number sorting in non-decreasing order
 *
 * ref: https://leetcode.com/problems/squares-of-a-sorted-array/description/
 */
public class SortedArraySquares {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                new int[] { -2, -1, 0, 2, 3 },
                new int[] { -3, -1, 0, 1, 2 },
                new int[] { -4,-1,0,3,10 },
                new int[] { -7,-3,2,3,11 }
        };
        for (int[] array : arr){
            System.out.print("Given: ");
            for (int num : array)
                System.out.print(num + " ");

            int[] result = makeSquares(array);

            System.out.print("Sorted Squares = ");
            for (int num : result)
                System.out.print(num + " ");
            System.out.println();
        }
    }

    /**
     * Solution Summary
     * - init array to hold squares & var to hold highestIndex
     * - Create 2 pointer (left & right)
     * - while(leftPointer < rightPointer)
     *      - calculate the actual square of (l_square = nums[l_pointer) & (r_square = nums[r_pointer)
     *      - set square[ index-- ] = based on which ever (left or right) is greater
     *  - return squares array
     */
    public static int[] makeSquares(int[] arr){
        int n = arr.length;
        int[] squares = new int[n];
        int highestSquaresIdx = n-1;
        int leftPointer = 0, rightPointer = arr.length -1;

        while(leftPointer <= rightPointer){
            int leftSquare = arr[leftPointer] * arr[leftPointer];
            int rightSquare = arr[rightPointer] * arr[rightPointer];
            if (leftSquare > rightSquare){
                squares[highestSquaresIdx--] = leftSquare;
                leftPointer++;
            }
            else {
                squares[highestSquaresIdx--] = rightSquare;
                rightPointer--;
            }
        }

        return squares;
    }

}
