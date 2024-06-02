package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 994 (medium)
 * You are given a graph m x n ... where each cell can have 1 or 3 values
 * 0 = empty cell
 * 1 = fresh orange
 * 2 = rotten orange
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange
 * If this impossible, return -1;
 * 
 * ref: https://leetcode.com/problems/rotting-oranges/description/
 * ref: https://www.youtube.com/watch?v=TzoDDOj60zE
 * ref: https://www.youtube.com/watch?v=y704fEOx0s0
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0994-rotting-oranges.java
 */
public class RottingOranges {

    public static void main(String[] args) {
        int [][] grid = {{2,1,1},
                {1,1,0},
                {0,1,1}
        };
        //System.out.println("should be 4: Number of minutes == " + orangesRotting(grid));

        int [][] grid2 = {{2,1,1},
                {0,1,1},
                {1,0,1}
        };
        //System.out.println("should be -1: Number of minutes == " + orangesRotting(grid2));

        int[][] grid3 = {{0,2}};
        //System.out.println("should be 0: Number of minutes == " + orangesRotting(grid3));

        int[][] board = new int[][]
                {
                        {2, 1, 1},
                        {1, 1, 1},
                        {0, 1, 2}
                };
        System.out.println("should be 2: Number of minutes == " + orangesRotting(board));
    }

    public static int orangesRotting_impl2(int[][] grid){
        int numFreshOranges = 0;
        int numMinutes = 0;

        int EMPTY = 0;
        int ROTTEN = 2;
        int FRESH = 1;

        Queue<int[]> rottenOranges = new LinkedList<>();

        // 0. compile list of rotten oranges
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == ROTTEN){
                    rottenOranges.add(new int[]{i, j});
                }
                else if(grid[i][j] == FRESH){
                    numFreshOranges++;
                }
            }
        }

        //
        return 0;
    }

    // TC = O(m x n) == where m, n = dimensions of the grid
    // SC =  O(m x n) in the worst case bcoz all cells could be rotten and we need to put them in teh queue
    public static int orangesRotting(int[][] grid){
        Queue<int[]> rottenOranges = new LinkedList<>();
        int freshCount = 0;

        int EMPTY = 0;
        int FRESH = 1;
        int ROTTEN = 2;

        // 1  initialize queue with rotten oranges
        // 2  count all fresh oranges
        // 3  put rotten cells in queue
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == ROTTEN){
                    rottenOranges.offer(new int[]{i, j});
                }
                else if (grid[i][j] == FRESH){
                    freshCount++;
                }
            }
        }

        System.out.println("At the start we have Rotten Oranges: " + rottenOranges.size() + " rotten oranges. Fresh Oranges: " + freshCount);
        int numberOfMinutes = -1;   // accounts for the last loop where nothing happens (i.e. last rotten orange has no neigh)
        int[][] direction_offsets = {
                {0, 1}, {1, 0},
                {0, -1}, {-1, 0}
        };
        // 4. while queue has rotten cells & freshCount is not zero
        while(!rottenOranges.isEmpty() && freshCount != 0 ){
            numberOfMinutes++;

            // check neighbors
            int[][] offsets = {
                    {0, 1}, {1, 0},
                    {-1, 0}, {0, -1}
            };

            int qSize = rottenOranges.size();
            for(int i=0; i<qSize; i++){
                int[] orange = rottenOranges.poll();

                for(int[] direction : offsets){
                    int row = orange[0] + direction[0];
                    int col = orange[1] + direction[1];

                    boolean rowIsWithinBounds = 0 <= row && row < grid.length;
                    boolean colIsWithinBounds = 0 <= col && col < grid[0].length;
                    boolean isValidCell = rowIsWithinBounds && colIsWithinBounds;

                    if(isValidCell && grid[row][col] == FRESH){
                        grid[row][col] = ROTTEN;
                        freshCount--;
                        rottenOranges.add(new int[]{row, col});
                    }
                }
            }
        }

        // if freshCount is ZERO, return numberOfMinutes, else,-1
        return freshCount == 0 ? numberOfMinutes : -1;
    }
}
