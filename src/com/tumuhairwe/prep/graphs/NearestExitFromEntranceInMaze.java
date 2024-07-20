package com.tumuhairwe.prep.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1926 (medium)
 *
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
 * You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze.
 * Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze.
 * The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 *
 * ref: https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
 */
public class NearestExitFromEntranceInMaze {

    /**
     * Solution Summary (BFS to find nearest boarder)
     * - Create and seed queue with entrance coordinates + distance = 0;
     * - while !que.isEmpty()
     *      - pull() cell from queue (set row, col and distance)
     *      - if its on the boarder & !and entrance -> return distance
     *      - check all 4 neighbors
     *      - if a neighbor is an EMPTY_CELL and is within bounds, add to queue (with disance = distance + 1)
     *      - mark cell as WALL (aka visited)
     */
    public int nearestExit(char[][] maze, int[] entrance){
        char WALL = '+';
        char EMPTY_CELL = '.';

        //0. create and see queue
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(entrance[0], entrance[1], 0));

        //1. mark as visited
        maze[entrance[0]][entrance[1]] = WALL;

        //2. do BFS starting from entrance
        while (!queue.isEmpty()){
            Cell current_cell = queue.poll();;
            //numberOfSteps++;

            int row = current_cell.row;
            int col = current_cell.col;
            int dist = current_cell.distance;

            //3. if it's on the boarder, we found exit -> return
            boolean isOnBorder = row == 0 || row == maze.length - 1 || col == 0 || col == maze[0].length;
            boolean isEntrance = row == entrance[0] && col == entrance[1];
            if(isOnBorder && !isEntrance){
                return dist;
            }

            //4. find all neighbors from current-cell
            int[][] offsets = {
                    {1, 0}, {0, 1},
                    {-1, 0}, {0, -1}
            };
            for (int[] direction : offsets){
                int proposedRow = row + direction[0];
                int proposedCol = col + direction[1];

                boolean isRowInBounds = proposedRow >= 0 && proposedRow < maze.length;
                boolean isColInBounds = proposedCol >=0 && proposedCol < maze[0].length;
                boolean isEmptyCell = maze[proposedRow][proposedCol] == EMPTY_CELL;
                if(isRowInBounds && isColInBounds && isEmptyCell){
                    // 5. add to queue
                    queue.add(new Cell(proposedRow, proposedCol, dist + 1));    // increment stepcOUNT

                    //6. mark as visited
                    maze[proposedRow][proposedCol] = WALL;
                }
            }
        }

        return -1;
    }

    class Cell implements Comparable<Cell>{
        private int row;
        private int col;
        private int distance;

        public Cell(int r, int c, int d){
            this.row = r;
            this.col = c;
            this.distance = d;
        }

        @Override
        public int compareTo(Cell obj) {
            if(!(obj instanceof Cell)){
                return -1;
            }
            Cell c = (Cell) obj;
            return Integer.compare(this.distance, c.distance);
        }
    }
}
