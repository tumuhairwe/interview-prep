package com.tumuhairwe.prep.intervals;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 252 (easy)
 *
 * Solution Summary:
 * - Sort the interval[] by start time == O(n log_n)
 * - iterate and compare adjacent intervals (if 1 int[][])
 *
 * ref: https://leetcode.com/problems/meeting-rooms/description/
 * ref: Geekific: https://www.youtube.com/watch?v=Iz5GZbvpxqo
 * ref: NeetCode: https://www.youtube.com/watch?v=PaJxqZVPhbg
  */
public class MeetingRooms {

    public int minMeetingRooms(List<Interval> intervals){
        // sort collection by start times
        intervals.sort(Comparator.comparingInt(Interval::getStart));

        // use minHeap to retrieve smallest value efficiently
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (Interval interval : intervals){

            // is current interval startTime > min value stored in queue i.e. do we need another room?
            if(!minHeap.isEmpty() && interval.getStart() >= minHeap.peek()){
                minHeap.poll(); // pull off interval     -- this is when current-intervals' room will be free
            }

            // store the end-value/end-time of the new room
            // this will always keep the end time of an interval
            minHeap.add(interval.getEnd());
        }

        return minHeap.size();  // size of concurrently occupied rooms
    }

    static  List<Integer> getIntersection(List<Integer> l1, List<Integer> l2){
        List<Integer> result = l1.stream()
                .filter(l -> l2.contains(l))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * Solution summary
     * 0. sort intervals by start (given arr[i] = [start, end] configuration
     * 1. iterate the slots starting from index=1, check if prev.end > curr.start
     *
     * TC: O(n log n) -- because of sorting
     * sc: O(1) (no extra space used)
     */
    // LC Summary: sort intervals by start time, if second interval doesn’t overlap with first, then third def wont overlap with first;
    public static boolean canAttendMeetings(int[][] slotsA) {
        // approach 1: sort slots by start time -> iterate comparing next-to-each-other slot (for loop starts at i= 1)
        // FALSE: sorting is necessary only if collection is array
        //0. sort array
        Comparator<int[]> comp = Comparator.comparingInt(a -> a[0]);
        Arrays.sort(slotsA, comp);

        //1.
        for (int i = 1; i < slotsA.length; i++) {
            int[] prev = slotsA[i-1];
            int[] curr = slotsA[i];

            if(prev[1] > curr[0]){
            //if(slotsA[i-1][1] > slotsA[i][0]){
                return false;
            }
        }

        return true;
    }

    // LeetCode 252 (easy)
    // LC Summary: sort intervals by start time, if second interval doesn’t overlap with first, then third def won't overlap with first;
    public static boolean canAPersonAttendAllTheMeetings2(int[][] slotsA) {
        // approach 2
        // 0. transform into array of intervals
        Set<Interval> schedule = new TreeSet<>();
        for (int i = 0; i < slotsA.length; i++) {
            schedule.add(new Interval(slotsA[i][0], slotsA[i][1]));
        }
        Interval[] intervals = (Interval[]) schedule.toArray();
        for (int i = 0; i < intervals.length - 1; i++) {
            if(intervals[i].overlaps(intervals[i + 1])){
                return false;
            }
        }

        return true;
    }
}
