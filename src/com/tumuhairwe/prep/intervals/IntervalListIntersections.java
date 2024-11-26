package com.tumuhairwe.prep.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 986 (medium)
 * Given 2 lists of closed intervals, firstList and secondList,
 * where firstList[i] = [start, end] and second[j] = [start, end]
 *
 * Each List of intervals is pairwise disjoint and sorted in order.
 * Return the intersection of these 2 interval lists
 *
 * A closed interval [a, b] with (a <= b) denotes a set of real numbers a where  a <= x <= b
 *
 * The intersection of 2 closed intervals is a set of real numbers that either empty or represented as a closed interval.
 * e.g. the intersection of [1, 3] and [2, 4] is [2, 3]
 * ref: https://leetcode.com/problems/interval-list-intersections/description/
 */
public class IntervalListIntersections {

    //TC: O(m + n)
    //SC: O(m + n)

    /**
     * Solution summary
     * - init 2 pointers to 0, & traverse both arrays (incrementing 1 at time)
     * - calc the maxStartTime of the currentInterval
     * - calc the minEndTime of the currentInterval
     * -if they intersect (startMax >= endMax) ... its an intersection, add to result
     * - if 1st-array's current interval ends 1st, increment pointer-1
     * - else increment 2nd-array's current interval,
     * - repeat until 1 pointer reaches end of it array
     * - transform List<int[]> into int[][]
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int p1 = 0;
        int p2 = 0;
        List<int[]> result = new ArrayList<>();

        while (p1 < firstList.length && p2 < secondList.length){
            int startMax = Math.max(firstList[p1][0], secondList[p2][0]);
            int endMin = Math.min(firstList[p1][1], secondList[p2][1]);

            boolean isIntersect = startMax >= endMin;
            if(isIntersect){
                result.add(new int[]{startMax, endMin});
            }
            if(firstList[p1][1] < secondList[p2][1]){
                p1++;
            }
            else {
                p2++;
            }
        }

        int[][] ans = result.toArray(new int[result.size()][]);
        return ans;
    }
}
