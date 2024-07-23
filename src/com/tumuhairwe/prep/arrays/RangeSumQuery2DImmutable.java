package com.tumuhairwe.prep.arrays;

/**
 * LeetCode 304 (medium)
 * Range Sum Query 2D
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * You must design an algorithm where sumRegion works on O(1) time complexity.
 *
 * ref: https://leetcode.com/problems/range-sum-query-2d-immutable/description/
 * ref: https://leetcode.com/problems/range-sum-query-2d-immutable/editorial/
 * ref: https://www.youtube.com/watch?v=KE8MQuwE2yA
 */
public class RangeSumQuery2DImmutable {
    class NumMatrix_II{
        int[][] dp;

        // constructor
        public NumMatrix_II(int[][] matrix){
            //0. init dp
            dp = new int[matrix.length][matrix[0].length];

            //1. populate dp
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    int matrixValue = matrix[i][j];
                    int dpValue = dp[i][j];
                    matrix[i][j + 1] = matrixValue + dpValue;
                }
            }
        }

        //sum
        public int sumRegion(int row1, int col1, int row2, int col2){
            int sum = 0;
            for (int r = 0; r <= row2; r++) {
                sum += dp[r][col2 + 1] - dp[r][col1];
            }
            return sum;
        }
    }
}
