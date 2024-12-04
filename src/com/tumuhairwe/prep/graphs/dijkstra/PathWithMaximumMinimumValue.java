package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.*;

/**
 * LeetCode 1102 (medium::dijsktra)
 * Given an m x n integer matrix "grid", return the maximum path score of a path starting at (0, 0) and
 * ending at (m-1, n-1) moving in the 4 cardinal directions
 * The score of a path is the minimum value in that path
 *
 * ref: https://leetcode.com/problems/path-with-maximum-minimum-value/
 */
public class PathWithMaximumMinimumValue {

    //ref: https://leetcode.com/problems/path-with-maximum-minimum-value/solutions/1168839/Java-clean-Dijkstra-Solution-with-O(mn-log(mn))-Time-oror-with-comments/
    public int maximumMinimumPath(int[][] grid) {
        int length = grid.length;
        int width = grid[0].length;

        Comparator<int[]> comp = Comparator.comparingInt(arr -> arr[2]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(comp);
        pq.offer(new int[]{0, 0, grid[0][0]});

        int[][] cost = new int[length][width];
        Arrays.stream(cost).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));
        cost[0][0] = 0;

        int targetRow = length - 1;
        int targetCol = width - 1;
        while (!pq.isEmpty()){
            int[] cell = pq.poll();
            int row = cell[0];
            int col = cell[1];
            int pathVal = cell[2];

            // check if we are at destination
            if(row == targetRow && col == targetCol){
                return pathVal;
            }

            // visit neighbors
            int[][] offset = {
                    {1, 0}, {0, 1},
                    {-1, 0}, {0, -1}
            };
            for (int[] dir : offset){
                int newX = dir[0] + row;
                int newY = dir[1] + col;

                if(newX < 0 || newX >= length || newY < 0 || newY >= width){
                    continue;
                }

                int newCost = Math.min(pathVal, grid[newX][newY]);
                if(cost[newX][newY] < newCost){
                    cost[newX][newY] = newCost;
                    pq.offer(new int[]{newX, newY, newCost});
                }
            }
        }

        return -1;
    }
}
