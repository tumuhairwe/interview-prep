package com.tumuhairwe.prep.intervals;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 715 (hard)
 * A range module is a module that tracks ranges of numbers.
 * Design a data structure to track the ranges represented as half-open intervals and query them
 *
 * A half-open interval [left, right) denotes real numbers x where left <= x < right
 *
 * implement the RangeModule class
 * - constructor() initializes the object of hte data structure
 * - void addRange(int left, int right) add the half-open [left, right) range, tracking every real number
 * in the interval. Adding an interval that partially overlpas with currently tracked numbers should add any numbers
 *  in the interval [left, right that are not already tracked
 *  - boolean queryRange(int left, int) returns true if every real number in the interval [left, right) is currently being tracked and false otherwise
 *  - void removeRange(int left, int right) stops tracking every real number being tracking in the half-open interval
 *
 * ref: https://leetcode.com/problems/range-module/description/
 * ref: https://leetcode.com/problems/range-module/solutions/2904396/cleanest-easiest-to-understand-treemap/
 */
public class RangeModule {
    private TreeMap<Integer, Integer> ranges;
    public RangeModule(){
        ranges = new TreeMap<>();
    }

    // find overlap, calc merged range, clear overlapped ranges, insert merged range

    /**
     * Solution summary
     * - To add a range
     *      - find overlap,
     *      - calculate merged range,
     *      - clear overlapped ranges,
     *      - insert merged range
     *
     * TC: log n
     * SC:
     */
    public void addRange(int left, int right){
        // left = start, right = end
        // find overlap
        Map.Entry<Integer, Integer> lEntry = ranges.floorEntry(left);
        Map.Entry<Integer, Integer> rEntry = ranges.floorEntry(right);

        // calculate merged range
        if(lEntry != null && lEntry.getValue() > left){
            left = lEntry.getValue();
        }
        if(rEntry != null && rEntry.getValue() < right){
            right = rEntry.getValue();
        }

        // clear overlapped ranges
        ranges.subMap(left, right).clear();

        // insert merged range
        ranges.put(left, right);
    }

    public boolean queryRange(int left, int right){
        Map.Entry<Integer, Integer> lEntry = ranges.floorEntry(left);
        return lEntry !=  null && lEntry.getValue() >= right;
    }

    public void removeRange(int left, int right){
        Map.Entry<Integer, Integer> lEntry = ranges.floorEntry(left);
        Map.Entry<Integer, Integer> rEntry = ranges.floorEntry(right);

        if(lEntry != null && lEntry.getValue() > left){
            ranges.put(lEntry.getKey(), left);
        }
        if(rEntry != null && rEntry.getValue() > left){
            ranges.put(rEntry.getKey(), right);
        }

        ranges.subMap(left, right).clear();
    }
}
