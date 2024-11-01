package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 980 (hard)
 * You are given a m x n int[] called grid, where grid[i][j] could be
 * 1 = starting square. There is exactly 1
 * 2 = ending square. There is exactly 1
 * 0 = empty square. squares you can walk all over
 * -1 = obstacles you can not walk over
 *
 * Return the number of 4 directional walks starting from startingSquare to endingSquare
 * that walk over every non-obstacle square exactly once.
 *
 * ref: https://leetcode.com/problems/unique-paths-iii/description/
 * ref: https://leetcode.com/problems/unique-paths-iii/solutions/2973622/java-code-with-dfs-and-backtracking-100-faster/
 */
public class UniquePathsIII {
    int START = 1;
    int END = 2;
    int EMPTY = 0;
    static int OBSTACLE = -1;

    /**
     * Solution summary
     * - declare vars
     * - traverse 2D grid && count all emptySquares
     * - do recursiveDFS
     *      - return 0 when you hit an OBSTACLE
     *      - blocks all nodes being visited ** unblock when done visiting
     *      - visit all neighbors && increment totalWalks
     *      - return totalWalks
     * - return totalWalks (starting from startRow && startCol)
     */
    public int uniquePathsIII(int[][] grid) {
        //0. declare vars
        int startRow = 0;
        int startCol = 0;
        int emptyCount = 0;

        //1. traverse 2D grid -> count all emptySquares
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == EMPTY){
                    emptyCount++;
                }
                if(grid[i][j] == START){
                    startRow = i;
                    startCol = j;
                }
            }
        }

       //2. call recursive DFS
       return recursiveDFS(grid, startRow, startCol, emptyCount);
    }

    public int recursiveDFS(int[][] grid, int row, int col, int emptyCount){
        //0. if out of bounds, return
        if(row < 0 || row >= grid.length || col < 0 || col >= grid.length){
            return 0;
        }

        //0. if obstacle -> return 0;
        if(grid[row][col] == OBSTACLE){
            return 0;
        }

        //1. if end -> return 0
        if(grid[row][col] == END){
            return emptyCount == -1 ? 1 : 0;
        }

        // 2. mark as VISITING
        grid[row][col] = OBSTACLE;
        emptyCount--;

        //3. visiting neighbors recursively
        int[][] offsets = {
                {1, 0}, {0, 1},
                {-1, 0}, {0, -1}
        };
        int totalWalks = 0;
        for (int[] dir : offsets){
            int pRow = row + dir[0];
            int pCol = col + dir[1];

            totalWalks += recursiveDFS(grid, pRow, pCol, emptyCount);
        }
        //5. unmark as VISITING
        grid[row][col] = EMPTY;
        emptyCount++;

        return totalWalks;
    }
}
