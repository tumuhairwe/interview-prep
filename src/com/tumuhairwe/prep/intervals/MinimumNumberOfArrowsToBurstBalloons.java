package com.tumuhairwe.prep.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 452 (medium)
 * There are some spherical ballones taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as 2D integer array points where point[i] = [x_start, x_end] denotes a balloon whose horizontal
 * diameter stretches between x_start and x_end.
 * You do not know the exact y-coordinates of the balloons
 * Arrows can be short up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon
 * x_start and x_end is burst by an arrow short at x if x_start <= x <= x_end. There's no limit to the number of ballons that can
 * be shot.
 *
 * Given an array points, return the minimum number of arrows that must be shot to burst all balloons
 * A shot arrow keeps travelling upward indefinitely bursting any balloons in its path
 * ref: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    /**
     * Solution summary
     * - sort points (do for most interval problems)
     *      - by start AND by end
     * - init results = points.length because worst case, there's no intersection all at & all are non-intersecting
     * - iterate point (from index 1) and decrement result when curr.start is before prev.end
     * - set prev.start = curr.start (since they're sorted)
     * - set prev.end = min(curr.end, prev.end)
     * - if no intersection, prev = curr
     * reutrn reslt
     *
     * TC: O(n log n)
     * SC: O(1)
     */
    public int findMinArrowShorts(int[][] points){
        //0. sort all points by star and end (n log n)
        Comparator<int[]> orderByStart = Comparator.comparingInt(a -> a[0]);
        Comparator<int[]> orderByEnd = Comparator.comparingInt(a -> a[1]);
        Arrays.sort(points, orderByStart.thenComparing(orderByEnd));

        //1. init vars (prev = 1st point) ...
        int[] prev = points[0];
        int result = points.length;

        //2. iterate points from index-1
        for (int i = 1; i < points.length; i++) {
            int[] curr = points[i];

            // find intersection that overlaps
            if(curr[0] <= prev[1]){
                result--;
                prev[0] = curr[0];  // prev.start = curr.start
                prev[1] = Math.min(curr[1], prev[1]);
            }
            else {
                prev = curr;    //when there's no intersection, prev=curr
            }
        }

        return result;
    }
}
