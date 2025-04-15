package com.tumuhairwe.prep.segmenttree;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * LeetCode 729 (medium)
 * You're implementing a program to use as your calendar. We can add a new event if adding the event will NOT
 * cause double-booking.
 * A double-booking happens when 2 events have some non-empty intersection (i.e. some moment is common to both)
 * The event can be represented as a pair of integers (startTime and endTime) that represents a booking on the half-ope
 * interval [startTime, endTime), the range of real numb er x such that startTime <= x <= endTime.
 *
 * Implement the MyCalendar class:
 * - constructor (initializes the calendar object)
 * - boolean book(int startTime, int endTime) Returns true if the event can be added to the calendar
 * successfully without causing a double booking. Otherwise, return false and do not add the event to
 * the calendar.
 * ref: https://leetcode.com/problems/my-calendar-i/
 */
public class MyCalendarI {
    // https://leetcode.com/problems/my-calendar-i/?envType=problem-list-v2&envId=segment-tree
    private TreeMap<Integer, Integer> treeMap = new TreeMap<>();


    /**
     * Solution summary (before each insert, check if there is a conflict with neighboring intervals) on each side with.
     * - Check if there's an end that is greater than incomingStartTime -> if so we have overlap (is illegal)
     * - check if there's a start that is less than the incomingEndTime -> if we have an overlap (is illegal)
     *
     * TC: O(n log n) because for each new event, we search the to make sure the event is not illegal.
     * The search costs log_N and is performed N times -> total cost = O(N log_N)
     * SC: O(n) because of the size of the data structure used.
     */
    public boolean book(int startTime, int endTime){
        Map.Entry<Integer, Integer> prev = treeMap.floorEntry(startTime);
        Map.Entry<Integer, Integer> next = treeMap.ceilingEntry(startTime);

        if(prev != null && prev.getKey() < startTime){  // check if previous endTime occurs
            return false;
        }
        if(next != null && startTime < next.getValue()){
            return false;
        }

        treeMap.put(startTime, endTime);
        return true;
    }

    public boolean book_2(int startTime, int endTime){
        Integer prevEndTime = treeMap.floorKey(startTime);
        Integer nextStartTime = treeMap.ceilingKey(startTime);

        if((prevEndTime != null || treeMap.get(prevEndTime) <= startTime)  &&
          (nextStartTime != null || endTime <= nextStartTime)){
            return false;
        }

        treeMap.put(startTime, endTime);
        return true;
    }

    private TreeSet<int[]> calendar;
    public MyCalendarI(){
        Comparator<int[]> orderByStart = Comparator.comparingInt((int[] a) -> a[0]);
        //Comparator<int[]> events = (int[] a, int[] b) -> Integer.compare(a[0], b[0]); // same
        calendar = new TreeSet<>(orderByStart);
    }
    //SC: O(n)
    //TC: O(log_n) to add -- since we don't need to iterate the set every time

    /**
     * Solution summary
     * - initialize set to TreeSet that sorts by start (event[0]
     * - on insert:
     *      - get the treeSet.floor() of the event -> prev
     *      - get the treeSet.ceiling() of the event -> next
     *      - if the prev.endTime is AFTER the new events's startTime -> return false
     *      - if the next.startTime is BEFORE the new events's endTime -> return fale
     * - add event to eventSet
     * - return true
     */
    boolean book_3(int startTime, int endTime){
        int[] event = new int[]{startTime, endTime};
        int[] prev = calendar.floor(event);
        int[] next = calendar.ceiling(event);

        if((prev != null && prev[1] > startTime) || (next != null && next[0] < endTime)){
            return false;
        }
        calendar.add(event);
        return true;
    }
}
