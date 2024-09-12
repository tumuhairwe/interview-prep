package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1730 (medium)
 *
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
 *
 * You are given an m x n character matrix, grid, of these different types of cells:
 *
 * '*' is your location. There is exactly one '*' cell.
 * '#' is a food cell. There may be multiple food cells.
 * 'O' is free space, and you can travel through these cells.
 * 'X' is an obstacle, and you cannot travel through these cells.
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 *
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
 */
public class ShortestPathToGetFood {

    /**
     * Solution summary
     * - traverse 2D grid to find starting point ... add it to que and break
     * - do BFS on each cell in que until you find FOOD_CELL -> return stepCount
     * - for each iteration
     *      - check all 4 neighbors (if !isVisited && !is_obstacle)
     *      - add cell to que
     * - increment stepCount after each iteration
     * - if you reach food cell, return length
     * - if you exit while loop, return -1 (i.e. no food cell)
     */
    public int getFood(char[][] grid){
        char STARTING_POINT = '*';
        char FOOD_CELL = '#';
        char OBSTACLE = 'X';
        char FREE_SPACE = '0';

        Queue<int[]> que = new LinkedList<>();

        //0. collect starting point
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == STARTING_POINT){
                    que.offer(new int[]{i, j});
                }
            }
        }

        //1. do BFS and return numSteps as soon you find food cell
        int shortestPath = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        while (!que.isEmpty()){
            int qDepth = que.size();
            while (qDepth-- > 0){
                int[] cell = que.poll();
                int x = cell[0];
                int y = cell[1];

                if(grid[x][y] == FOOD_CELL){
                    return shortestPath;
                }

                int[][] offsets = {
                        {1, 0}, {0, 1},
                        {-1, 0}, {0, -1}
                };
                for (int[] dir : offsets){
                    int row = dir[0] + x;
                    int col = dir[1] + y;

                    boolean isRowInBounds = row >=0 && row < grid.length;
                    boolean isColInBounds = col >= 0 && col < grid[0].length;

                    if(isRowInBounds && isColInBounds && grid[row][col] != OBSTACLE && !visited[row][col]){
                        visited[row][col] = true;
                        que.offer(new int[]{row, col});
                    }
                }
            }

            shortestPath++;
        }

        return -1;
    }
}
