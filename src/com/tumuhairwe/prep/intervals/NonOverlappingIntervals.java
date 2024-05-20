package com.tumuhairwe.prep.intervals;

import java.util.*;

public class NonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1,2},{2,3},{3,4},{1,3}
        };
        int ddx = eraseOverlapIntervals(intervals);
        System.out.println("There are " + ddx + " intervals to be removed");
    }
    public static int eraseOverlapIntervals(int[][] intervals) {
//        int[] arr1, arr2;
        Comparator<int[]> comp = Comparator.comparingInt(arr -> arr[0]);

        Set<TimeRange> output = new TreeSet<>();
        // 1. sort intervals & de-dupe
        for(int i=0; i< intervals.length; i++){
            int[] interval = intervals[i];

            TimeRange range = new TimeRange(interval[0], interval[1]);
            if(output.contains(range)){
                System.out.println("Already contained " + range);
            }
            output.add(range);
        }

        int overlappingCount = 0;
        // 2. remove overlapping intervals
        TimeRange[] timeRangeArray = output.toArray(new TimeRange[output.size()]);
        for(int i=1; i< timeRangeArray.length; i++){
            TimeRange a = timeRangeArray[i - 1];
            TimeRange b = timeRangeArray[i];

            if(a.intersects(b)){
                // merge by removing b
                timeRangeArray[i - 1].end = b.end;
                timeRangeArray[i] = null;
                overlappingCount++;
            }
        }

        // count non-null ranges
        Long count = Arrays.stream(timeRangeArray)
                .filter(Objects::nonNull)
                .count();
        return intervals.length - count.intValue();
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
