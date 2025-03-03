package com.tumuhairwe.prep.linesweep;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 1353 (medium)
 * Given an array of events where events[i] = [startDay, endDay]. Every event i starts at startDay and ends at endDay
 * You can attend an event i at any day d where (startTime <= d <= endTime). You can only attend 1 event at a time
 * Return the max number of events you can attend.
 *
 * ref: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/?envType=problem-list-v2&envId=mzw3cyy6
 */
public class MaxEventsThatCanBeAttended {

    /**
     * Solution summary
     * - sort events by startDay
     * - traverse events starting from 1
     * - remove all events that ended the day before
     * - add all the events starting on current day
     * - remove all events 1 by 1 and increment numEvents
     * TC: O (n log n)
     * SC: (n)
     */
    public int maxEvents(int[][] events) {
        //0. base case
        if(events.length <= 1){
            return events.length;
        }

        //1. sort by startDay
        Comparator<int[]> orderByStart = Comparator.comparingInt(a -> a[0]);
        Arrays.sort(events, orderByStart);

        //2. declare vars
        PriorityQueue<Integer> pqOfEndDay = new PriorityQueue<>();

        //3. traverse events starting from 1
        int numEvents = 0;
        int idx = 0;
        for (int currentDay = 1; currentDay < 1000000; currentDay++) {
            //4. remove all events that ended the day before
            while (!pqOfEndDay.isEmpty() &&  pqOfEndDay.peek() < currentDay){
                pqOfEndDay.poll();
            }

            //5. add all the events starting on current day
            while (idx < events.length && events[idx][0] == currentDay){
                pqOfEndDay.add(events[idx][1]);
            }

            //6. remove all events 1 by 1 and increment numEvents
            if(!pqOfEndDay.isEmpty()){
                pqOfEndDay.poll();
                numEvents++;
            }
        }

        return numEvents;
    }
}
