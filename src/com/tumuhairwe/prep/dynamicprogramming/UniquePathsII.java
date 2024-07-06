package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 63 (medium)
 *
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner
 * (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid.
 * A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * ref: https://leetcode.com/problems/unique-paths-ii/solutions/3897323/concise-simple-beginner-friendly-approach/
 */
public class UniquePathsII {

    int[][] graph;
    Integer[][] memo;
    final int OBSTACLE = 1;
    //TC: O(m * n)
    //SC: O(m * n)
    public int uniquePathsWithObstacles(int[][] obstaclesGrid){
        int rows = obstaclesGrid.length;
        int cols = obstaclesGrid[0].length;

        graph = obstaclesGrid;
        memo = new Integer[rows][cols];

        return recursiveDFS(0, 0);
    }

    /**
     * Solution summary
     * - check if (is obstacle) || isBoundaryRow || isBoundaryCol -> return 0;
     * - check if target destination/cell -> return 1;
     * - check if cached in memo/cache -> return value from cache
     * - calculate recursiveCall(row+1, col) + recursiveCall(row, col + 1);
     * - save result in memo/cache grid
     * - return value
     */
    private int recursiveDFS(int row, int col) {
        // check if boundary or obstacle
        boolean isBoundaryRow = row == graph.length ;
        boolean isBoundaryCol = col == graph[0].length;
        if (isBoundaryRow || isBoundaryCol || graph[row][col] == OBSTACLE){
            return 0;
        }

        // if is target destination
        boolean isTargetDestination = (row == graph.length -1 && col == graph[0].length - 1);
        if(isTargetDestination){
            return 1;
        }

        // check if cached
        boolean isCached = memo[row][col] != null;
        if(isCached){
            return memo[row][col];
        }

        // do recursive call and cache
        memo[row][col] = recursiveDFS(row + 1, col) + recursiveDFS(row, col + 1);
        return memo[row][col];
        //return memo[row][col] = recursiveDFS(row + 1, col) + recursiveDFS(row, col + 1);
    }
}
