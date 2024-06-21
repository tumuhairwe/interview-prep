package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * LeetCode 253
 * Given an array of meeting time intervals of start and end times,
 * find the minimum number of conference rooms required
 *
 * LC Summary:
 * we care about the points in time where we are starting/ending a meeting,
 * we already are given those,
 * just separate start/end and traverse counting num of meetings going at these points in time;
 * for each meeting check if a prev meeting has finished before curr started, using min heap;
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

    /***
     * Solution summary
     * - Create 2 arrays to track start time and end times
     * - populate both arrays from intervals array
     * - Sort both arrays (goal is to track used_number_of_rooms)
     * - Use 2 pointers to track indices of each array ( increment usedRooms counter if startTimes[p1] < endTimes[p2]
     * - Increment p2 only when we need another room
     */
    public static int findMinimumNumberOfRoomsRequired_2Pointers_chronologicalOrdering__LC253(int[][] intervals){
        Integer[] start_times = new Integer[intervals.length];
        Integer[] end_times = new Integer[intervals.length];

        // 0. populate start_time and end_times array
        for (int i = 0; i < intervals.length; i++) {
            start_times[i] = intervals[i][0];
            end_times[i] = intervals[i][1];
        }

        // 2. sort both arrays
        Arrays.sort(start_times);
        Arrays.sort(end_times);

        // 3. track used rooms
        int usedRooms = 0;
        for (int idxStart = 0, idxEnd = 0; idxStart < intervals.length; idxStart++) {
            // if current start-time is less than new end-time being considered .. we need a new room
            if(start_times[idxStart] < end_times[idxEnd]){
                usedRooms++;
            }
            else {
                idxEnd++;   // used rooms stays constant and pointer_2 moves ahead
            }
        }

        return usedRooms;
    }

    /**
     * Solution summary
     * - Sort intervals
     * - Track end times in PQ
     * - b4 adding new end-time, check to see if soonest ending meeting ends before new/current one stats, if so, pq.poll()
     * - return size of pq
     */
    // TC: sorting == O(n logn) .. checking top of heap = constant time O(1) (inserting = O log_n)
    // SC : O(n)
    public static int findMinimumNumberOfRoomsRequired__endTime_pq_LC253(int[][] intervals){
        // 0. handle base case
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        else if(intervals.length == 1){
            return 1;
        }

        // 1. sort intervals by start time
        Comparator<int[]> orderByStart = Comparator.comparingInt(a -> a[0]);
        Arrays.sort(intervals, orderByStart);

        // 2. use minHeap to track non-overlapping meetings' end times
        PriorityQueue<Integer> endTime_pq = new PriorityQueue<>();    // pq to track all end time
        for (int i = 0; i < intervals.length; i++) {
            int[] current = intervals[i];

            // 3a) if queue of end-time is not empty() and soonest-ending is ending sooner that current, remove it
            int currentStartTime = current[0];
            int currentEndTime = current[0];
            if(!endTime_pq.isEmpty() && endTime_pq.peek() <= currentStartTime){
                endTime_pq.poll();
            }

            endTime_pq.offer(currentEndTime);  // always add ending time to minHeap
        }

        // 4. return size of minHeap/non-intersecting meetings
        return endTime_pq.size();
    }
    public static int findMinimumNumberOfRoomsRequired(int[][] intervals){
        // 0. handle base case
        if(intervals == null || intervals.length == 0){
            return 0;
        }

        // 1. sort by start time (index 0) (tc = n log(n) )
        Comparator<int[]> orderByStart = Comparator.comparingInt(a -> a[0]);
        Arrays.sort(intervals, orderByStart);

        // 2. use PQ/minHeap to track non-intersecting intervals
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(orderByStart);
        minHeap.add(intervals[0]);

        // 3. iterate over schedules and extend end time of most recent intersecting interval (as you iterate)
        for (int i=1; i<intervals.length; i++){
            int[] current = intervals[i];
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

    // this just counts intersections ... just doesn't mean they rquire sepate rooms e.g. if intersections are at different times
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
