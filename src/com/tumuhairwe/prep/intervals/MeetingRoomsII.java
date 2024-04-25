package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * LeetCode 253
 * Given an array of meeting time intervals of start and end times,
 * find the mininum number of conference roomsm required
 *
 *  ref: https://leetcode.com/problems/meeting-rooms-ii
 */
public class MeetingRoomsII {

    public static void main(String[] args) {
        int[][] scheduleA = new int[][]{
                {0, 30},
                {5, 10},
                {15, 20}
        };
        int[][] scheduleB = new int[][]{
                {7, 10},
                {2, 4}
        } ;
        int numRooms = findMinimumNumberOfRoomsRequired(scheduleA, scheduleB);
        System.out.println("It requires " + numRooms + " to attend all meetings for both schedules");

        numRooms = findMinimumNumberOfRoomsRequired(scheduleA);
        System.out.println("It requires " + numRooms + " to attend all meetings for just scheduleA");
    }
    public static int findMinimumNumberOfRoomsRequired(int[][] intervals){
        // 0. handle base case
        if(intervals == null || intervals.length == 0){
            return 0;
        }

        // 1. sort by start time (index 0) (tc = n log(n) )
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // 2. use PQ to track non-intersecting intervals
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.add(intervals[0]);

        // 3. iterate over schedules and extend end time of most recent intersecting interval (as you iterate)
        for (int i=1; i<intervals.length; i++){
            int[] current = new int[]{ intervals[i][0], intervals[i][1]};
            int[] earliest = minHeap.remove();

            // if they intersect, merge them
            if(current[0] <= earliest[1]){
                int endTime = Math.max(earliest[1], current[1]);   // extend end-date to which ever is larger
                earliest[1] = endTime;
                minHeap.add(earliest);
            }
            else{
                minHeap.add(current);
            }
        }

        // 4. minHeap should have size of all non-intersecting room
        return minHeap.size();
    }
    public static int findMinimumNumberOfRoomsRequired(int[][] slotsA, int[][] slotsB){
        // unnecessary since Interval implements Comparable
        // Comparator<Interval> comp = Comparator.comparingInt(a -> a.start);
        //Arrays.sort(schedule, comp);

        Set<Interval> slotAAvailability = new TreeSet<>();
        for (int i = 0; i < slotsA.length; i++) {
            slotAAvailability.add(new Interval(slotsA[i][0], slotsA[i][1]));
        }

        Set<Interval> slotBAvailability = new TreeSet<>();
        for (int i = 0; i < slotsB.length; i++) {
            slotBAvailability.add(new Interval(slotsA[i][0], slotsA[i][1]));
        }
        long numberOfIntersections = slotAAvailability
                .stream()
                .filter(slotA -> slotBAvailability.stream()
                        .anyMatch(slotB -> slotB.intersects(slotA)))
                .count();

        return (int) numberOfIntersections;
    }
}
