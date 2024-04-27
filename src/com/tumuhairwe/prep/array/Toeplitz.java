package com.tumuhairwe.prep.array;

/**
 * LeetCode 766 (Easy)
 *
 * Summary: Given a matrix of m x n (rows x columns) ... determine if matrix is Toeplitz
 * i.e "every diagonal from left to bottom has the same elements"
 *
 * Solution summary:
 * - iterate both arrays (nested for-loop) (starting from 1 until arr.length)
 * - compare the diff in values on each cell's diagonal neighbor
 * - if diff != 0, return false
 * - if you exit the nested for loops, return treu
 *
 * ref: https://leetcode.com/problems/toeplitz-matrix/
 * ref: https://www.youtube.com/watch?v=VDw9y6nX_ss
 */
public class Toeplitz {
    public static void main(String[] args) {
        int[][] array = {{5,9,4,1},
                {2,5,9,4},
                {3,2,5,9}};

        boolean isToeplitz = isToeplitz(array);
        System.out.println(isToeplitz);
    }

    private static boolean isToeplitz(int[][] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                if(array[i-1][j-1] - array[i][j] != 0){
                    return false;
                }
            }
        }

        return true;
    }
}
