package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 2577 (dijkstra::hard)
 *
 * You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time
 * required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you
 * visit it is greater than or equal to grid[row][col].
 *
 * You are standing in the top-left cell of the matrix in the 0th second, and you must move to any
 * adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.
 *
 * Return the minimum time required in which you can visit the bottom-right cell of the matrix.
 * If you cannot visit the bottom-right cell, then return -1.
 */
public class MinimumTimeToVisitACellInGrid {
    public int minimumTime(int[][] grid) {
        //0. edge case (can't leave origin)
        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }

        //1. declare vars
        int length = grid.length;
        int width = grid[0].length;
        int[][] offsets = {
                {0, 1}, {1, 0},
                {0, -1}, {-1, 0}
        };

        //2. declare pq & seed with origin & its time with comp that sorts by time
        //Comparator<int[]> comp = Comparator.comparingInt(cell -> cell[2]);
        // sort by time (in [time, row, col])
        Comparator<int[]> comp = (int[] a, int[] b) -> a[2] - b[2];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(comp);
       // int initialTime = grid[0][0];
        minHeap.offer(new int[]{0, 0, 0});

        // create visited 2D []
        boolean[][] visited = new boolean[length][width];

        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int row = curr[0];
            int col = curr[1];
            int time = curr[2];

            //1. check if destination
            if(row == length - 1 && col == width - 1){
                return time;
            }

            //2. skip if already visited
            if(visited[row][col]){
                continue;
            }
            visited[row][col] = true;

            // visit neighbors
            for(int[] dir : offsets){
                int r = row + dir[0];
                int c = col + dir[1];

                // check bounds
                if(r < 0 || r == length || c < 0 || c == width || visited[r][c]){
                    continue;
                }

                // is it possible to move to neighbor cell?
                if(grid[r][c] <= time + 1){
                    minHeap.offer(new int[]{r, c, time + 1});
                }
                else{
                    int diff = grid[r][c] - time;
                    if(diff % 2 == 1){
                        minHeap.offer(new int[]{ r, c, grid[r][c]});
                    }
                    else{
                        minHeap.offer(new int[]{r, c, grid[r][c] + 1});
                    }
                }

            }
        }
        return -1;
    }
}
