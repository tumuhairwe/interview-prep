package com.tumuhairwe.prep.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2013 (medium)
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 *
 *
 * ref: https://leetcode.com/problems/detect-squares/description/
 * ref: https://leetcode.com/problems/detect-squares/solutions/1471958/c-java-python-2-approaches-using-hashmap-with-picture-clean-concise/
 */
public class DetectSquares {

    private int[][] coordinates;
    private List<int[]> points;

    // initialize both vars:
    // coordinates is for tracking the number of points/dots on the 2D graph [0][0] = 1 means there's 1 point on the graph at that location
    public DetectSquares(){
        this.coordinates = new int[1001][1001];
        this.points = new ArrayList<>();
    }

    // a) mark the graph as having 1 dot at that location
    // b) save the coordinates
    public void add(int[] point){
        int x = point[0];
        int y = point[0];
        coordinates[x][y] += 1; // goal is to track the number of points/dots at that coordinate (could be overlayeed)

        points.add(point);
    }

    /***
     * Solution summary
     * - Given 1 point (p1), find the diagonal of a non-empty square
     * - Since we have 2 points, p1 and p3, we can form a square by computing the positions of 2 remaining points p2 and p4
     * p3 = (p1.x, p3.y)
     * p4 = (p3.x, p1.y)
     * - now that we know that (p1 and p2 are present), count the (dots in p3 and p3) and sum them up
     * - return the sum
     */
    public int count(int[] point){
        int sum = 0;
        int x1 = point[0];
        int y1 = point[1];

        for (int[] p3 : points){
            int x3 = p3[0];
            int y3 = p3[1];
            boolean diagonal_is_non_empty_x = Math.abs(y1 - y3) == 0;
            boolean diagonal_is_non_empty_y = Math.abs(x1 - x3) == 0;
            boolean diff_btween_diagonals_is_different = Math.abs(x1 - x3) != Math.abs(y1 - y3);
            if(diagonal_is_non_empty_x || diagonal_is_non_empty_y || diff_btween_diagonals_is_different){
                continue;
            }

            sum += coordinates[x1][y3] * coordinates[x3][y1];
        }

        return sum;
    }
}
