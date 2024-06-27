package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 48 (medium)
 *
 *  You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * O NOT allocate another 2D matrix and do the rotation.
 *
 * ref: https://www.youtube.com/watch?v=SA867FvqHrM&t=40s
 * ref: https://leetcode.com/problems/rotate-image/description/?envType=problem-list-v2&envId=plakya4j
 */
public class RotateImage {
    public void rotate(int[][] matrix){
        int n = matrix.length;

        //0. flip the matrix horizontally by swapping all char in the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //1. flip the matrix vertically using the 2 pointer approach
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][n-j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }
}
