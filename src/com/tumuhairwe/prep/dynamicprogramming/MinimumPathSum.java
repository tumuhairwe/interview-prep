package com.tumuhairwe.prep.dynamicprogramming;

import java.util.Arrays;

/**
 * LeetCode 64 (Medium)
 *
 * Given a m x n grid, filled with non-negative numbers, find a path from the top left
 * to the bottom right, which minimizes the sum of all numbers along its path
 *
 * Example; [[1,3,1],[1,5,1],[4,2,1]]
 * output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * ref: https://leetcode.com/problems/minimum-path-sum/
 * ref: https://www.youtube.com/watch?v=pGMsrvt0fpk
 */
public class MinimumPathSum {
    public static void main(String[] args) {

    }
    public int minPathSum_pq(int[][] grid) {
        int rows = grid.length;
        int cols = grid.length;

        int[][] dp = new int[rows + 1][cols + 1];
        for (int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return dp_recursive(grid, rows, cols, dp);
    }

    /**
     * Solution Summary
     * - Skip the 1st cell,
     * - iterate grid and fill each cell with the cost to get to it from origin [0][0] (i.e. cost of cell-to-right + cell-to-left
     * - set cell-value = min(costToTheRight, costToTheLeft)
     * - Eventually, return cost of getting to the very last cell coordinates [ row-1][col-1]s
     */
    public int minPathSum(int[][] grid){
        //0. declare vars
        int row = grid.length;
        int col = grid[0].length;

        // 1. iterate grid (strategy is to calculate the cost-to-go-down & cost-to-go-left & get the min() until we get to the last cell
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // skip the first cell
                if(i == 0 && j == 0){
                    continue;
                }

                int costToGoRight = Integer.MAX_VALUE;
                if(i != 0){
                    costToGoRight = grid[i][j] + grid[i - 1][j];
                }

                int costToGoDown = Integer.MAX_VALUE;
                if(j != 0){
                    costToGoDown = grid[i][j] + grid[i][j - 1];
                }

                grid[i][j] = Math.min(costToGoDown, costToGoRight);
            }
        }

        return grid[row - 1][col - 1];
    }

    private int dp_recursive(int[][] grid, int rows, int cols, int[][] dp) {
        if(rows == 0 && cols == 0){
            return grid[0][0];
        }

        if (rows == 0){
            dp[rows][cols] = grid[rows][cols] + dp_recursive(grid, rows, cols -1, dp);
            return dp[rows][cols];
        }
        if(cols == 0){
            dp[rows][cols] = grid[rows][cols] + dp_recursive(grid, rows -1, cols, dp);
            return dp[rows][cols];
        }
        if(dp[rows][cols] != -1){
            return dp[rows][cols];
        }
        int rowToTheLeft = dp_recursive(grid, rows, cols - 1, dp);
        int colAbove = dp_recursive(grid, rows-1, cols, dp);
        dp[rows][cols] = grid[rows][cols] + Math.min(rowToTheLeft, colAbove);

        return dp[rows][cols];
    }
}
