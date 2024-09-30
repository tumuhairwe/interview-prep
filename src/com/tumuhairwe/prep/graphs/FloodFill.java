package com.tumuhairwe.prep.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 733 (medium)
 * Given an image (grid that is m x n) where  image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 *
 * ref: https://www.youtube.com/watch?v=aehEcTEPtCs
 * ref: https://leetcode.com/problems/flood-fill/description/
 */
public class FloodFill {
    public static void main(String[] args) {
        int[][]grid = {
                {1,1,1},{1,1,0},{1,0,1}
        };
        floodFill(grid, 1, 1, 2);
        Arrays.stream(grid).forEach(a -> System.out.println(Arrays.toString(a)));
    }

    /**
     * Solution Summary
     * - We'll use DFS .. so create visited 2D array to mark all cells that visited
     * - Create Queue and seed it with starting cell (row, col)
     * - while !que.isEmpty() .. poll() current cell , mark as visited ... and 4-directionally mark neighboring cell with newColor ... a
     * - Repeat until queue is empty
     * - return updated 2-D array
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        final int[] directions = new int[]{0, 1, 0, -1, 0};
        int rowLength = image.length;
        int colLength = image[0].length;
        int originalValue = image[sr][sc];
        image[sr][sc] = newColor;

        // 0. create visited 2D array to track cells that are visited
        boolean[][] visited = new boolean[rowLength][colLength];

        // 1. gonna use DFS so create queue and seed with starting cell
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});

        // 2. while queue is not empty pull cell and process it
        while (!queue.isEmpty()) {
            // 3. poll current cell and mark as visited
            int[] curr = queue.poll();
            visited[curr[0]][curr[1]] = true;

            // 4. 4-directionally mark neighboring cell with newColor
            for (int i = 0; i < directions.length - 1; i++) {
                int nextRow = curr[0] + directions[i];
                int nextCol = curr[1] + directions[i + 1];

                boolean rowIsOutOfBounds = nextRow < 0 || nextRow >= rowLength;
                boolean colIsOutOfBounds = nextCol < 0 || nextCol >= colLength;

                if (rowIsOutOfBounds || colIsOutOfBounds || image[nextRow][nextCol] != originalValue || visited[nextRow][nextCol]) {
                    continue;
                }
                image[nextRow][nextCol] = newColor;

                //5. add cell to queue
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        return image;
    }
}
