package com.tumuhairwe.prep.linesweep;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 1288 (medium)
 * Given an array of intervals where intervals[i] = [left, right], remove all intervals
 * that are covered by another interval int the list.
 * Return the remaining number of intervals.
 *
 * ref: https://leetcode.com/problems/remove-covered-intervals/description/?envType=problem-list-v2&envId=mzw3cyy6
 * ref: https://leetcode.com/problems/remove-covered-intervals/solutions/878251/java-python-sorting-clean-concise-o-nlogn/?envType=problem-list-v2&envId=mzw3cyy6
 */
public class CoveredIntervals {

    /**
     * Solution summary
     * 0. Create a custom comparator that sorts removeCoveredIntervalsy startTime ASC, & endTime DESC
     * 1. Sort intervals with custom comparator
     * 2. loop thru sorted intervals, maintain lastEndTime (initialized to -1) & calculate the number of intervals to remove (intervals covered)
     * 3. return intervals≥length - numIntervalsRemoved
     *
     * TC: O(n log n) -- because of sorting
     * SC: O(1
     */
    public int removeCoveredIntervals(int[][] intervals) {
        //0. create custom comparator that sorts by startTime (ASC) and endTime (DESC)
        Comparator<int[]> orderByStart = (int[] a, int[] b) -> {
            if(a[0] != b[0]){
                //if startTime is NOT equal, sort by starTime (ASC)
                return Integer.compare(a[0], b[0]);
            }
            else{
                // if startTime is equal, sort by endTime (DESC)
                return Integer.compare(b[1], a[1]);
            }
        };

        // sort by comparator
        Arrays.sort(intervals, orderByStart);

        // for each interval,  if curr.endTime <= last -> curr overlapped, curr overlapped so increment removed
        int lastEndTime = -1;
        int numRemoved = 0;
        for (int[] curr : intervals){
            if(curr[1] <= lastEndTime){
                numRemoved++;
            }
            else {
                lastEndTime = curr[1];
            }
        }

        //return remainingCount
        return intervals.length - numRemoved;
    }
}
