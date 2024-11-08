package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 3286 (medium)
 * You are given an m x n grid binary matrix and an integer _health
 * You start on the upper-left corner (0,0) and would like to get to the lower-right corner (m-1, n-1)
 * You can move up, down, left, right, center to another adjacent cells as long as health remains positive
 *
 * cell(i, j) in grid[i][j] = 1 are considered unsafe and reduce health by 1
 * Return true if you can reach the final cell with the health value of 1 or more ... false otherwise
 */
public class FindSafeWalkThruGrid {

    //0. initialization: set up cost tracking queues
    //1. BFS process cells based on their safety and status && update costws
    //2. termination check: if the destination cell has positive health
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        //0. create grid
        int[][] maze = new int[grid.size()][grid.get(0).size()];
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                maze[i][j] = grid.get(i).get(j);
            }
        }

        // declare vars
        int length = maze.length;
        int width = maze[0].length;

        //1a) create and seed que for BFS
        Queue<int[]> que = new LinkedList<>();
        int startingHealth = health - maze[0][0];
        que.add(new int[]{0, 0, startingHealth});

        //1b create and seed visited []
        boolean[][][] visited = new boolean[length][width][startingHealth];
        visited[0][0][startingHealth] = true;

        while (!que.isEmpty()){
            int qDepth = que.size();

            while (qDepth-- > 0){
                int[] curr = que.remove();
                int row = curr[0];
                int col = curr[1];
                int currHealth = curr[2];

                if(row == length-1 && col == width-1 && currHealth > 0){
                    return true;
                }

                int[][] offsets = {
                        {1, 0}, {0, 1},
                        {-1, 0}, {0, -1}
                };
                for (int[] dir : offsets){
                    int x = row + dir[0];
                    int y = col + dir[1];

                    // check boundaries
                    if(x < 0 || x >= length || y < 0 || y >= width){
                        continue;
                    }

                    int cellHealth = currHealth - maze[x][y];
                    if(cellHealth >= 0 && !visited[x][y][currHealth]){
                        visited[x][y][currHealth] = true;
                        que.add(new int[]{x, y, currHealth});
                    }
                }
            }
        }

        return false;
    }
}
