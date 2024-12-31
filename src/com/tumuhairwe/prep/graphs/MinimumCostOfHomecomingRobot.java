package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 2087 (medium)
 * There is an (m x n) grid where (0, 0) is the top-left cell and (m-1, n-1) is the bottom-right cell.
 * You are given an integer array startPos (with [startRow, startCol]) indicates that initially a robot is at that
 * cell.
 * You are also given an integer array homePos (with [startRow, startCol]) indicates that that is the home cell
 *
 * The robot needs to go to its home. It can move one cell in four directions: left, right, up, or down, and it can not move outside the boundary. Every move incurs some cost. You are further given two 0-indexed integer arrays: rowCosts of length m and colCosts of length n.
 *
 * If the robot moves up or down into a cell whose row is r, then this move costs rowCosts[r].
 * If the robot moves left or right into a cell whose column is c, then this move costs colCosts[c].
 * Return the minimum total cost for this robot to return home.
 *
 * ref: https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
 */
public class MinimumCostOfHomecomingRobot {
    /**
     * Solution summary
     * - Use greedy approach (i.e. to actually do BFS, instead, figure out cost of going from startRow to endRow
     *
     * - calculate the cost of going from startRow to endRow
     * - at each step/row-increment, add-to-sum the rowCost[x] of going to that row
     * - calculate the cost of going from endRow to startRow
     * - at each step/col-increment, add-to-sum the colCost[x] of going to that colum
     *
     * return the sum
     */
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts){
        int sum = 0;
        // add cost to go to endRow
        while (startPos[0] != homePos[0]){
            if(startPos[0] < homePos[0]){
                startPos[0]++;
                sum += rowCosts[startPos[0]];
            }
            else{
                startPos[0]--;
                sum += rowCosts[startPos[0]];
            }
        }

        // add cost to go to endCol
        while (startPos[1] != homePos[1]){
            if(startPos[1] < homePos[1]){
                startPos[1]++;
                sum += colCosts[startPos[1]];
            }
            else {
                startPos[1]--;
                sum += colCosts[startPos[1]];
            }
        }

        return sum;
    }
}
