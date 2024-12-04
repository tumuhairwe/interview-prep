package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 1631 (medium::dijkstra)
 * You're a hiker preparing for an upcoming hike. You're given heights, a 2D array of size rows X columns,
 * where heights[row][col] represents the height of a cell (row, col).
 * You are situated in the top-left cell (0, 0) and hope you travel to the bottom-right cell (rows-1, cols-1)
 * i.e. 0-indexed.
 * You can move up, left, right, down & you wish to find a route that requires minimum effort.
 *
 * The route's EFFORT is maximum absolute difference in heights between 2 consecutive cells of the route.
 *
 * Return the minimum effort required to travel from top-left cell to the bottom-right cell.
 */
public class PathWithMinimumEffort {

    record Cell(int row, int col, int effort){
    }

    // ref: https://leetcode.com/problems/path-with-minimum-effort/solutions/1168825/Java-clean-Dijkstra-Solution-with-O(mn-*-log(mn))-Time-oror-with-comments/
    //TC: O(m * n)
    //SC: O(m * n)
    public int minimumEffortPath(int[][] heights) {
        int length = heights.length;
        int width = heights[0].length;

        //0. create pq
        Comparator<Cell> comp = Comparator.comparingInt(c -> c.effort);
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(comp);
        minHeap.offer(new Cell(0, 0, heights[0][0]));

        //1. create cost/effort matrix and seed it
        int[][] effortMatrix = new int[length][width];
        Arrays.stream(effortMatrix).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));
        effortMatrix[0][0] = 0;

        //2. do dfs
        while (!minHeap.isEmpty()){
            Cell cell = minHeap.poll();

            // check if we're at destination
            boolean isDestination = cell.row == length-1 && cell.col == width - 1;
            if(isDestination){
                return cell.effort;
            }

            //check neighbors
            int[][] offset = {
                    {1, 0},{0, 1},
                    {-1, 0}, {0, -1}
            };
            for (int[] dir : offset){
                int newRow = dir[0] + cell.row;
                int newCol = dir[1] + cell.col;

                // check if within bounds
                if(newRow < 0 || newRow >= length || newCol < 0 || newCol >= width){
                    continue;
                }
                int newEffort = Math.min(cell.effort, Math.abs(heights[newRow][newCol] - heights[cell.row][cell.col]));
                // If the new effort is less than previously recorded effort, update and add to the queue
                if (newEffort < effortMatrix[newRow][newCol]) {
                    effortMatrix[newRow][newCol] = newEffort;
                    minHeap.add(new Cell(newRow, newCol, newEffort));
                }
            }
        }
        return effortMatrix[length - 1][width - 1];
    }
}
