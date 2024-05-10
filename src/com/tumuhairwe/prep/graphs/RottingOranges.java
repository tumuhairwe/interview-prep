package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.Queue;

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
 */
public class RottingOranges {

    public static void main(String[] args) {
        int [][] grid = {{2,1,1},
                {1,1,0},
                {0,1,1}
        };
        System.out.println("should be 4: Number of minutes == " + orangesRotting(grid));

        int [][] grid2 = {{2,1,1},
                {0,1,1},
                {1,0,1}
        };
        System.out.println("should be -1: Number of minutes == " + orangesRotting(grid2));

        int[][] grid3 = {{0,2}};
        System.out.println("should be 0: Number of minutes == " + orangesRotting(grid3));
    }
    
    public static int orangesRotting(int[][] grid){
        Queue<int[]> listOfRottenOranges = new LinkedList<>();
        int freshCount = 0;

        int FRESH = 1;
        int ROTTEN = 2;

        // 1  initialize queue with rotten oranges
        // 2  count all fresh oranges
        // 3  put rotten cells in queue
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == ROTTEN){
                    listOfRottenOranges.offer(new int[]{i, j});
                }
                else if (grid[i][j] == FRESH){
                    freshCount++;
                }
            }
        }
        
        int numberOfMinutes =0;
        int[][] direction_offsets = {
                {0, 1}, {1, 0},
                {0, -1}, {-1, 0}
        };
        // 4. while queue has rotten cells & freshCount is not zero
        while (!listOfRottenOranges.isEmpty() && freshCount != 0){
            numberOfMinutes++;  // 4a) increment number of minutes
            for (int i = 0; i < listOfRottenOranges.size(); i++) {
                int[] rottenOrange = listOfRottenOranges.poll();;

                // 5. for each rotten cell, check neighbor and mark rotten
                int row = rottenOrange[0], column = rottenOrange[1];
                for (int[] offset : direction_offsets){
                    int x = row + offset[0];
                    int y = column + offset[1];

                    boolean rowIsWithinBounds = (0 <= x && x < grid.length);
                    boolean colIsWithinBounds = (0 <= y && y < grid[0].length);
                    boolean cellIsWithinBounds = rowIsWithinBounds && colIsWithinBounds;

                    // 5. if neighbor is FRESH AND is within bounds
                    //boolean isFresh = grid[x][y] == FRESH;    // might throw OutOfBoundsException if is out of bounds
                    if(cellIsWithinBounds && grid[x][y] == FRESH){
                        grid[x][y] = ROTTEN;    // mark neighbor as ROTTEN and add to queue to be polled in the next iteration
                        listOfRottenOranges.offer(new int[]{x, y});
                        freshCount--;
                    }
                }
            }
        }

        // if freshCount is ZERO, return numberOfMinutes, else,-1
        return freshCount == 0 ? numberOfMinutes : -1;
    }
}
