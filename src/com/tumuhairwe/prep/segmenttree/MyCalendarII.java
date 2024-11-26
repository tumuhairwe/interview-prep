package com.tumuhairwe.prep.segmenttree;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 731 (medium)
 * You are implementing a program to use as you calendar. We can add a new event if
 * adding the event will not cause a triple booking.
 *
 * A triple booking happens when 3 events have some non-empty intersection (i.e. some
 * moment si common to all 3 events)
 *
 * The event can be represented as a pair of integers (startTime and endTime) that represents
 * a booking on the half-open interval [startTime, endTime), the range of number x
 * such that startTime <= x <= endTime
 *
 * ref: https://leetcode.com/problems/my-calendar-ii/description/
 */
public class MyCalendarII {
    private TreeMap<Integer, Integer> bookings;
    private int maxAllowedBookings;

    public MyCalendarII(){
        bookings = new TreeMap<>();
        maxAllowedBookings = 3;
    }

    /**
     * Solution: (line-sweep algorithm)
     * info: for each booking, we mark the start (by increasing by 1) and end (by decreasing by 1)
     * We need a treeMap because we want to stare the keys (time) by order
     *
     * - initialize maxAllowedBookings
     * - increment startTime by 1 -- indicating when the booking begins
     * - decrement endTime by 1 --indicating when the booking end
     * - iterate thrue the treeMap and increment number of overlapped bookings
     * - when overlap exceeds allowed threshold, roll back increment/decrement - return false
     *      - if currentEntry.getValue() of bookings == 0, remove it from map
     * - If you exit for loop, (return true) since no booking exceeded threshold
     *
     * TC: O(n) -- because we iterate over all the booking in the map and find the prefix sum. For each entry we have 3 operations
     * (each with log_n complexity. We do return though once we find the tripple booking
     * SC: O(n) -- because we store the start and the end points of each booking the map & since each booking requires 2 entries in
     * the map (i.e. 2N -> n) the SC is N
     */
    public boolean book(int startTime, int endTime){
        // add to line-sweep
        bookings.put(startTime, bookings.getOrDefault(startTime, 0) + 1);
        bookings.put(endTime, bookings.getOrDefault(endTime, 0) - 1);

        int overlappedBookings = 0;
        for(Map.Entry<Integer, Integer> entry : bookings.entrySet()){
            overlappedBookings += entry.getValue();

            if(overlappedBookings > maxAllowedBookings){
                // rollback addition above
                bookings.put(startTime, bookings.getOrDefault(startTime, 0) -1);
                bookings.put(endTime, bookings.getOrDefault(endTime, 0) +1);

                // prune if necessary
                if(entry.getValue() == 0){
                    bookings.remove(entry.getKey());
                }
                return false;   // can not add booking
            }
        }

        return true;    // can add booking
    }
}
