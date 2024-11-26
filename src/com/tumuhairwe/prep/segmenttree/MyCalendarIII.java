package com.tumuhairwe.prep.segmenttree;

import java.util.TreeMap;

/**
 * LeetCode 732 (hard)
 * A k-booking happens when k events have some non-empty intersection (i.e. there some time that is common to all k events
 *
 * Given some events [startTime, endTime], after each given event, return an integer k representing the max k-booking between all
 * the previous events.
 *
 * Implement a class where
 * - constructor initializes the object
 * - int book(int startTime, int endTime) Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.
 *
 * ref: https://leetcode.com/problems/my-calendar-iii/description/
 * ref: https://leetcode.com/problems/my-calendar-iii/solutions/109556/java-c-clean-code/
 */
public class MyCalendarIII {

    private TreeMap<Integer, Integer> treeMap;
    public MyCalendarIII(){
        this.treeMap = new TreeMap<>();
    }

    /**
     * Gaal: Find teh maximum number of ongoing events at any give time
     *
     * Solution summary
     * - Log start & end of each event to the timeline (start == add, end == remove)
     * - Scan the timeline to figure out the max number of ongoing events at any given time
     */
    public int book(int startTime, int endTime){
        // use a TreeMap (sorted map)because with an array, the time spot could be very sparse in between events
        treeMap.put(startTime, treeMap.getOrDefault(startTime, 0) + 1); // +1 at the start
        treeMap.put(endTime, treeMap.getOrDefault(endTime, 0) -1);      // -1 at the end

        int max = 0;
        int ongoing = 0;
        for(Integer val : treeMap.values()){
            ongoing += val;
            max = Math.max(max, ongoing);
        }
        return max;
    }
}
