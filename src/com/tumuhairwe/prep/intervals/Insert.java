package com.tumuhairwe.prep.intervals;

import java.util.ArrayList;
import java.util.List;

public class Insert {

    public static void main(String[] args) {

    }

    /**
     * Solution Summary
     * - Use binary search to find the right spot to me3ge in the new interval
     * - copy all intervals that occur BEFORE newInterval (from incoming-intervals into result) ...
     * - insert newInterval ...
     * - paste in all interval that occur AFTER newInterval has ended
     *
     * - Return result array
     * TC: O(n): binary_search takes O(log_n), however, insertion requires traversing the whole array
     * - It may invlove shifting elements within the list ...
     * - Consequently overall TC = O(N + lgo_N) ... -> O(N)
     *
     * SC: O(N) : Since we need to use additional space to store the resulting merged array.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 0. handle base (when intervals is empty)
        if(intervals.length == 0){
            return new int[][]{ newInterval };
        }
        // Soltuion summary
        // 1. find the insert position
        // 1 a) use binary search
        int numberOfIntervals = intervals.length;
        int target_to_insert = newInterval[0];
        int left = 0, right = intervals.length - 1;

        // binary search for the position to insert the interval
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

    /**
     * Solution Summary
     * Iterate thru the entire array
     * - find the right spot to merge in the new interval
     * - merge in the new interval
     * - Return result array
     * TC: O(n)
     */
    public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {

        // Replace this placeholder return statement with your code
        List<int[]> result = new ArrayList<>();

        for(int i=0; i<existingIntervals[0].length; i++){
            int start = existingIntervals[i][0];
            int end = existingIntervals[i][1];

            // handle start // assumeds start < end
            if(end < newInterval[0]){   // interval starts and ends BEFORE newInterval
                result.add(new int[]{start, end});
                continue;
            }

            if (newInterval[0] >= start){   // newInterval starts AFTER start
                start = Math.min(start, newInterval[0]);
            }

            // handle end of range
            if(end >= newInterval[1]){    // newInterval's end may exceed end
                end = Math.max(end, newInterval[1]);  //  max of the 2 should be upperBound
            }
            result.add(new int[]{start, end});
        }
        return result.toArray(new int[0][]);
    }
}
