package com.tumuhairwe.prep.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 2054 (medium)
 *
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTime_i, endTime_i, value_i].
 * The ith event starts at startTime_i and ends at endTime_i, and if you attend this event, you will receive a value of value_i.
 * You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
 *
 * Return this maximum sum.
 *
 * Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts
 * and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must
 * start at or after t + 1.
 */
public class TwoBestNonOverlappingEvents {

    /**
     * ref: https://leetcode.com/problems/two-best-non-overlapping-events/solutions/3155057/java-priorityqueue-solution/
     */
    public int maxTwoEvents(int[][] events) {
        //0. declare var
        int currentMax = 0;
        int maxSum = 0;

        //1. sort events by start time
        Comparator<int[]> orderByStartTime = Comparator.comparingInt(arr -> arr[0]);
        Arrays.sort(events, orderByStartTime);

        //2. create pq that orders by start time
        PriorityQueue<int[]> pq = new PriorityQueue<>(orderByStartTime);    // arr = [startTime, endTime]

        //3. loop thru events .... pop of earlier-occurring-events (while keeping the max) ...
        for(int[] event : events){
            //0. pop off earlier-occurring events
            while (!pq.isEmpty() && event[0] < pq.peek()[0]){
                currentMax = pq.poll()[1];;
            }

            //5 update maxSum
            maxSum = Math.max(maxSum, currentMax + pq.poll()[1]);
            pq.offer(new int[]{event[1], event[2]});
        }

        return maxSum;
    }
}
