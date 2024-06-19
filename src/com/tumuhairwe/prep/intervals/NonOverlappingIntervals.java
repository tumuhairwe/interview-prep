package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * LeetCode 435. Non-overlapping Intervals
 * Given an array of intervals intervals where intervals[i] = [start_i, end_i],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * ref: https://leetcode.com/problems/non-overlapping-intervals/description/
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1,2},{2,3},{3,4},{1,3}
        };
        int[][] i2 = new int[][]{
                {1,100},{11,22},{1,11},{2,12}
        };
        int ddx = eraseOverlapIntervals(i2);
        System.out.println("There are " + ddx + " intervals to be removed");
    }

    /**
     * Solution summary
     * - Comparison sort the intervals (based on start time)
     * - seed previousInterval with the 1st one ... start iterating from the 2nd/current one
     * - if current_one starts b4 prevous_one ends:
     *      - removeCounter++, and
     *      - determine which one ends later ... update previous_one with the later-ending one
     *  -if there's no overlap, set previous_one to current_one
     */
    public static int eraseOverlapIntervals(int[][] intervals) {

        int removedCount = 0;

        // 0. sort interval first
        Comparator<int[]> comp = Comparator.comparingInt(a -> a[0]);
        Arrays.sort(intervals, comp);
        //Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));

        // 2. remove overalapping intervals
        int[] previous_interval = intervals[0];

        for(int i=1; i< intervals.length; i++){
            //int[] interval_a = intervals[i - 1];
            int[] current_interval = intervals[i];

            if(current_interval[0] < previous_interval[1]){  // b ends before a
                removedCount++; // b is completely removed

                // determine which interval ends last (update previous interval)
                if(previous_interval[1] > current_interval[1]){
                    previous_interval = current_interval;
                }
            }
            else{
                previous_interval = current_interval;
            }

        }

        // count non-null ranges
        return removedCount;
    }

    static class TimeRange implements Comparable<TimeRange>{
        private int start;
        private int end;

        public TimeRange(int start, int end){
            this.start = start;
            this.end = end;
        }
        public boolean startsBefore(TimeRange range){
            return this.start < range.start;
        }
        public boolean intersects(TimeRange range){
            return this.end < range.start;
        }
        public int compareTo(TimeRange range){
            return Integer.compare(this.start, range.start);
        }
        public boolean equals(Object obj){
            if(this == obj){
                return true;
            }
            if(obj.getClass() != getClass()){
                return false;
            }

            TimeRange range = (TimeRange) obj;
            return Objects.equals(this.start, range.start)
                    && Objects.equals(this.end, range.end);
        }

        @Override
        public String toString() {
            return "from: " + start + "  to: " + end;
        }
    }
}
