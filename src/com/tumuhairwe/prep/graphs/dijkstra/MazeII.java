package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.*;

/**
 * LeetCode 505 (medium) (dijkstra)
 * There is a ball in a maze with empty_spaces (0) and walls (1).
 * The ball can go thru the empty spaces rolling up, down, left and right but it won't stop util it hits a wall
 * When the ball stops it could choose the next direction.
 *
 * Given the M x N maze, the ball's _start_ position and _destination_ position
 * return the shortest distance for the ball to stop at the destination. If the call can not stop at dest .. return -1
 *
 * ref: https://leetcode.com/problems/the-maze-ii/description/?envType=list&envId=53js48ke
 */
public class MazeII {

    /**
     * Solution summary
     * - create 2D grid of shortestDistance
     * - create 2D [] to track distances
     * - seed 2D distance grid with 0 (startRow, startCol)
     * - Create queue of Cells and see with Cell(startRow, startCol)
     * - do BFS
     *      - poll from que (_curr)
     *      - for each neighbor .. check if its boundary && not a wall
     *             -> increment _count, update coordinates
     *
     *      - calculate newDistance (_curr + _count)
     *      - if newDistance < neighbor_distance
     *          - > if !destination -> add Cell to queue
     *  - in the end 2D grid will have all distances
     *  - return distance[destinationRow][destiationCol}
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        //0. create 2D grid of shortestDistance
        int[][] distance = new int[maze.length][maze[0].length];
        Arrays.stream(distance).forEach(d -> Arrays.fill(d, Integer.MAX_VALUE));

        //1 seed start distance in 2D grid
        int startRow = start[0];
        int startCol = start[1];
        distance[startRow][startCol] = 0;

        int destRow = destination[0];
        int destCol = destination[1];

        //2 create and seed que with starting position
        Queue<Map.Entry<Integer, Integer>> que = new LinkedList<>();
        que.offer(new AbstractMap.SimpleEntry<>(startRow, startCol));

        //3. do dfs
        int[][] offsets = {
                {0, 1}, {1, 0},
                {-1, 0}, {0, -1}
        };
        int WALL = 0;
        while (!que.isEmpty()){
            Map.Entry<Integer, Integer> curr = que.poll();

            for(int[] dir : offsets){
                int neiRow = curr.getValue();
                int neiCol = curr.getValue();

                //move util you hit a wall
                int count = 0;
                while (neiRow + dir[0] >=0 && neiRow + dir[0] < maze.length
                        && neiCol + dir[1] >=0 && neiCol < maze[0].length
                        && maze[neiRow + dir[0]][neiCol + dir[1]] == WALL){
                    // increment neighbor coordinates if value
                    neiRow += dir[0];
                    neiCol += dir[1];

                    // increment distance
                    count++;

                    // if distance < currentDistance + count -> update 2d grid with new distance
                    int newDistance = distance[curr.getKey()][curr.getValue()] + count;
                    if(newDistance < distance[neiRow][neiCol]){
                        distance[neiRow][neiCol] = newDistance;

                        boolean isDestination = destCol == neiRow && destCol == neiCol;
                        if(isDestination){
                            continue;
                        }

                        //add non-destination neighbor to que
                        que.offer(new AbstractMap.SimpleEntry<>(neiRow, neiCol));
                    }
                }
            }
        }

        if(distance[destRow][destCol] == Integer.MAX_VALUE){
            return -1;
        }

        //1. return destination distance in 2D grid
        return distance[destRow][destCol];
    }
}
