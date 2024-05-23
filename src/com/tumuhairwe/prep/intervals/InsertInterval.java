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
 * NB: If You are not required to modify intervals in place (you can create a new one)
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

    /**
     * Solution Summary
     * - Binary search interval 2D array (focus on start index) ....
     * - Find the spot to insert new interval
     * - Binary-search should leave you with a LEFT pointer (i.e. every before that can be copy-pasted into result
     * - insert inverval after LEFT
     * - copy + paste all other/later intervals into result
     * - Loop thru result and perform merging by combining every entry by checking preceding entry
     */
    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        // 0. handle base (when intervals is empty)
        if(intervals.length == 0){
            return new int[][]{ newInterval };
        }

        // Solution summary
        // 1. find the insert position
        // 1 a) use binary search
        int numberOfIntervals = intervals.length;
        int target_to_insert = newInterval[0];
        int left = 0, right = intervals.length - 1;

        //1. binary search for the position to insert the interval
        while(left <= right){
            int mid = (left + right) / 2;
            if(intervals[mid][0] < target_to_insert){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        // 2. handle the merging
        List<int[]> result = new ArrayList<>();

        // insert interval into result ... at the appropriate position ... right before MID
        // 2a) copy + paste all intervals before LEFT
        for(int i=0; i<left; i++){
            result.add(intervals[i]);
        }
        // 2b add newInterval
        result.add(newInterval);

        // 2c copy + past all interval after/later-than LEFT/new-interval
        for(int i=left; i<numberOfIntervals; i++){
            result.add(intervals[i]);
        }

        // 3. loop thru and merge any overlapping intervals
        List<int[]> merged = new ArrayList<>();
        for(int[] interval : result){
            // if mergedResult is empty or there's no overlap ... add the interval to the result
            if(merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]){
                merged.add(interval);
            }
            else {
                int preceedingEnd = merged.get(merged.size() - 1)[1];
                merged.get(merged.size() - 1)[1] = Math.max(preceedingEnd, interval[1]);
            }
        }
        // 3. convert to array
        return merged.toArray(new int[0][]);
    }
}
