package com.tumuhairwe.prep.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 733
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
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[] directions = new int[]{0, 1, 0, -1, 0};
        int rowLength = image.length;
        int colLength = image[0].length;
        int originalValue = image[sr][sc];
        image[sr][sc] = newColor;

        boolean[][] visited = new boolean[rowLength][colLength];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            visited[curr[0]][curr[1]] = true;
            for (int i = 0; i < directions.length - 1; i++) {
                int nextRow = curr[0] + directions[i];
                int nextCol = curr[1] + directions[i + 1];

                boolean rowIsOutOfBounds = nextRow < 0 || nextRow >= rowLength;
                boolean colIsOutOfBounds = nextCol < 0 || nextCol >= colLength;

                if (rowIsOutOfBounds || colIsOutOfBounds || image[nextRow][nextCol] != originalValue || visited[nextRow][nextCol]) {
                    continue;
                }
                image[nextRow][nextCol] = newColor;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        return image;
    }
}
