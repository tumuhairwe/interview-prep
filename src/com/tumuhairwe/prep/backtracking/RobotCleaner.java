package com.tumuhairwe.prep.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 489 (hard)
 *
 * You're controlling a robot that is located ina room is that modeled as an
 * m x n binary grid where WALL=0 and EMPTY_SLOT=1
 *
 * The robot starts at an unknown location in the room that is guaranteed to be empty
 *  and you do not have access to the grid, but you can move the robot using the given API (Robot)
 *
 *  You are tasked to use the robot to clean the entire room (i.e. every empty cell in the room)
 *  The robot with 4 given APIs can move forward and turn right. Each turn is 90 degrees
 *
 *  When the robot tries to move into a wall cell, it bumper sensor detects teh obstacle and stays i teh current cell
 *
 *  Design an algorithm to clean the entire room using the following APIs:
 *
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 * Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.
 */
public class RobotCleaner {

    interface Robot{
        void turnLeft();
        void turnRight();
        boolean move();
        void clean();
    }
    class Pair<R, L>{ R row; L col; public Pair(R r, L c){ row = r; col = c;}}

    //0. declare global vars Robot & set of visited pairs
    Robot robot;
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    public void cleanRoom(Robot robot){

        //1
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    /**
     * Solution summary
     * - add tuple(row, col) to visited set
     * - perform action
     * - visit all 4 neighbors
     *     - for each direction
     *          - calc newDistance % 4
     *          - if newTuple is !visited && robot.canMove()
     *          - recurse passing newTuple and newDistance
     *          - backtrack(goBack()) to the cell before the move
     */
    void backtrack(int row, int col, int distance){
        //1. mark current cell as visited
        visited.add(new Pair<>(row, col));

        //1. perform clean
        robot.clean();

        //2. visit all 4 neighbors, if you run into an issue, go back
        int[][] directions = {
                {1, 0}, {0, 1},
                {-1, 0}, {0, -1}
        };
        for (int i = 0; i < 4; i++) {
            int newDistance = (distance + i)  % 4;
            int newRow = row + directions[newDistance][0];
            int newCol = row + directions[newDistance][1];

            // 3. if not yet visited ... and moved() successfully .. backtrack to explore all neighbors
            Pair newTuple = new Pair<>(newRow, newCol);
            if(!visited.contains(newTuple) && robot.move()){
                //3. backtrack to recursively visit unvisited cells
                backtrack(newRow, newCol, newDistance);

                //4. when backtrack returns .. go back (clockwise)
                goBack(robot);
            }

            //5. turn the robot clockwise
        }
    }

    void goBack(Robot robot){
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
