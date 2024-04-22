package com.tumuhairwe.prep.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 57
 * given an array of non-overlapping intervals, where interval[i] = [start, end]
 * represent the start and end of the i-th interval
 * And given an interval newInterval = [start, end] that represents a new one
 *
 * Insert newInterval into the existing intervals such that
 * - interval is still sorted in ascending order by start
 * - intervals still do not have any overlapping intervals (merge overlapping intervals if
 * necessary)
 *
 * NB: You are not required to modify intervals in place (you can create a new one)
 * ref: https://leetcode.com/problems/insert-interval/description/
 */
public class InsertInterval {
    //private static final int[][] inputIntervals = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
    // sorting and then iterating ==> O(n_log(n) )
    public static void main(String[] args) {
        int[] newInterval = new int[]{2, 5};
        int[][] arr1 = new int[][]{{1, 3}, {6, 9}};

        System.out.println("BEFORE inserting ... ");
        System.out.println(Arrays.toString(arr1));

        arr1 = insertInterval(arr1, newInterval);

        System.out.println("AFTER inserting ... ");
        System.out.println(Arrays.toString(arr1));
    }
    public static int[][] insertInterval(int[][] intervals, int[] newInterval){
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals){

            // 0. case: is distinct
            int prevIntervalStart = interval[0];
            int prevIntervalEnd = interval[1];

            int newIntervalStart = newInterval[0];
            int newIntervalEnd = newInterval[1];

            // 0. case: they're non overlapping boundaries
            if(newInterval == null || prevIntervalEnd < newIntervalStart){
                result.add(interval);
            }
            // 1. this new interval ends BEFORE previous start
            else if(prevIntervalStart > newIntervalEnd) {
                result.add(newInterval);
                result.add(interval);
            }
            // they over-lap -> merge them
            else {
                // same
                //newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[0] = Math.min(prevIntervalStart, newIntervalStart);

                // same
                //newInterval[1] = Math.min(interval[1], newInterval[1]);
                newInterval[1] = Math.max(prevIntervalEnd, newIntervalEnd);
            }

            if(newInterval != null){
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }
}
