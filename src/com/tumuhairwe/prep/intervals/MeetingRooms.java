package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * LeetCode 920
 * ref: https://www.youtube.com/watch?v=Iz5GZbvpxqo
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

    public int findMinimumNumberOfRoomsRequired(int[][] slotsA, int[][] slotsB){
        Interval[] schedule = new Interval[slotsA.length];
        Comparator<Interval> comp = Comparator.comparingInt(a -> a.start);
        Arrays.sort(schedule, comp);

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
    public static boolean canAPersonAttendAllTheMeetings(int[][] slotsA) {
        // 0. transform into array of intervals
        Set<Interval> schedule = new TreeSet<>();
        for (int i = 0; i < slotsA.length; i++) {
            schedule.add(new Interval(slotsA[i][0], slotsA[i][1]));
        }

        // 1. sort the intervals based on start time (explicit sorting is unnecessary since Collection (TreeSet) already sorted.
        // sorting is necessary only if collection is array
        // Comparator<Interval> comp = Comparator.comparingInt(Interval::getStart);
        // Comparator<Interval> comp = Comparator.comparingInt(a -> a.start);
        // Arrays.sort((Interval[]) schedule.toArray(), comp);
        Interval[] intervals = (Interval[]) schedule.toArray();
        for (int i = 0; i < intervals.length; i++) {
            if(intervals[i].end > intervals[i + 1].start){
                return false;
            }
        }

        return true;
    }
}
