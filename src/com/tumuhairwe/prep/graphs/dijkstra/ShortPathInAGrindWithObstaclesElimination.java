package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 1293 (hard::dijkstra)
 * You are given an [m x n] integer grid
 * where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty
 * cll in 1 step.
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1)
 * given that you can eliminate at most K obstacles.
 * If its not possible to find such a walk, return -1
 */
public class ShortPathInAGrindWithObstaclesElimination {

    /**
     * ref: https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/solutions/1188835/Java-Clean-O(MNK-log(MNK))-Time-Dijkstra's-BFS-Solution-oror-with-comments/
     */
    public int shortestPath(int[][] grid, int k) {
        //0. declare vars
        int length = grid.length;
        int width = grid[0].length;

        //1. create visited set
        int[][] visited = new int[length][width];
        Arrays.stream(visited).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));

        //2. start Dijsktra (with que)
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[]{0, 0, 0});
        int numSteps = 0;

        while (!deque.isEmpty()){
            int qDepth = deque.size();

            int targetRow = length - 1;
            int targetCol = width - 1;
            while (qDepth-- > 0){
                int[] cell = deque.poll();

                // return numSteps if we're at destination
                if(cell[0] == targetRow && cell[1] == targetCol){
                    return numSteps;
                }

                // visit neighbor
                int[][] offset = {
                        {0, 1}, {1, 0},
                        {0, -1}, {-1, 0}
                };
                for (int[] dir : offset){
                    int newRow = dir[0] + cell[0];
                    int newCol = dir[1] + cell[1];

                    // skip if out of bounds
                    if(newRow < 0 || newRow >= length || newCol < 0 || newCol >= width){
                        continue;
                    }

                    // skip if number of obstacles is exceeded
                    int obstacleCount = cell[2] + grid[newRow][newCol]; // cost + 1
                    if(obstacleCount > k){
                        continue;
                    }
                    if(visited[newRow][newCol] <= obstacleCount){
                        continue;
                    }

                    // enque x, y, cost
                    deque.add(new int[]{newRow, newCol, obstacleCount});
                    visited[newRow][newCol] = obstacleCount;
                }
            }

            numSteps++;
        }

        // return -1 if we never go to destination
        return -1;
    }
}
