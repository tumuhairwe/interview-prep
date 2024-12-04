package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 490 (medium)
 *
 * There is a ball in _maze_ with empty spaces ( 0 ) and walls ( 1 ).
 * The ball can go thru the empty spaces by rolling down, up, left & right but it won't stop until hitting a wall
 *  When the ball stops, it could choose the next direction
 *
 *  Given the m x  n maze, the ball's staring position and the destination, return true if the ball can stop at the destination
 *  , otherwise return false
 *
 * ref: https://leetcode.com/problems/the-maze/
 */
public class Maze {

    int EMPTY_SPACE = 0;

    /**
     * - Create queue and see with startPosition
     * - Create 2D visited [] and seed with startPosition
     * - visit neighbors:
     *    - for each entry in offsets
     *        - move as long as cell is in bounds and is EMPTY_SPACE
     *        - revert back 1 step after you exit (to revert to the last good/valid cell)
     *        - skip if already visited, else mark as visited
     *        - if cell is destination, return true
     *        - add cell to que
     *
     * SC: O(m x n ) where m = rows, n = cols
     * TC: O(m x n (m +n))
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //0. create and seed que with start
        Queue<int[]> que = new ArrayDeque<>();
        que.add(start);

        //1. create visited 2D grid
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;

        int[][] offsets = {
                {1, 0}, {0, 1},
                {-1, 0}, {0, -1}
        };

        //3. perform dfs on que
        while (!que.isEmpty()){
            int[] cell = que.poll();
            int r = cell[0];
            int c = cell[1];

            for (int i = 0; i < offsets.length; i++) {

                // move until you hit a wall
                while (isValid(r, c, maze) && maze[r][c] == EMPTY_SPACE){
                    r += offsets[i][0];
                    c += offsets[i][1];
                }

                // revert the last move to get the valid empty cell to which the rolled
                r -= offsets[i][0];
                c -= offsets[i][1];

                //skip already visited
                if(visited[r][c]){
                    continue;
                }
                // check if destination -> return true
                if(r == destination[0] && c == destination[1]){
                    return true;
                }

                // mark as visited
                visited[r][c] = true;

                // add to que
                que.add(new int[]{r, c});
            }
        }

        return false;
    }

    boolean isValid(int r, int c, int[][] maze){
        return r >=0 && r < maze.length && c >= 0 && c < maze[0].length;
    }
}
