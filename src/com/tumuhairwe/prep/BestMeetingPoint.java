package com.tumuhairwe.prep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 296 Hard
 *
 * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * ref: https://leetcode.com/problems/best-meeting-point/description/
 */
public class BestMeetingPoint {
    /**
     * Solution summary (transform a 2D array problem into 2 1-D array problems)
     * - Collect the coordinates of row & cols
     * - To find a shared center
     *      - sort cols list
     *      - find the meanRow and meanCol
     *      - find the minimum absolute diff between meanRow and all rows && min diff between minCol and all cols
     *  - return the sum of the 2 mean diffs
     */
    public int minTotalDistance(int[][] grid) {
        //0. declare vars
        List<Integer> rowsWithHomes = new ArrayList<>();
        List<Integer> colsWithHomes = new ArrayList<>();

        //1. populate rows & cols with homes
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rowsWithHomes.add(row);
                    colsWithHomes.add(col);
                }
            }
        }

        //3. calc avg dist to "shared center"
        Collections.sort(colsWithHomes);
        //Collections.sort(rowsWithHomes);
        int meanRow = rowsWithHomes.get(rowsWithHomes.size() / 2);
        int meanCol = colsWithHomes.get(colsWithHomes.size() / 2);

        return minDistance1D(rowsWithHomes, meanRow) + minDistance1D(colsWithHomes, meanCol);
    }

    private int minDistance1D(List<Integer> points, int origin){
        int distance = 0;
        for(int point : points){
            distance += Math.abs(point - origin);
        }

        return distance;
    }
}
