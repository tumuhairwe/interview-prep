package com.tumuhairwe.prep.intervals;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 352 (hard)
 * Data Stream as Disjoint Intervals
 *
 * Given a data stream input of non-negative integers [a1, a2, a3, ... aN]
 * summarize the numbers seen so far as a list of disjoint intervals
 *
 * Implement the SummaryRanges class
 * - constructor() initializes the object with an empty stream
 * - void addNum(int val) -- add the integer val to the stream
 * - int[][] getIntervals() -- returns a summary of the integers int the stream currently as a list of disjoint
 * intervals [start, end]. The answer should be sorted by start
 *
 * ref: https://leetcode.com/problems/data-stream-as-disjoint-intervals/description/
 * ref: https://leetcode.com/problems/data-stream-as-disjoint-intervals/solutions/82553/java-solution-using-treemap-real-o-logn-per-adding/
 */
class SummaryRangesV2 {
    private TreeMap<Integer, Integer> intervals;
    public SummaryRangesV2(){
        intervals = new TreeMap<>();
    }

    // use treeMap to find the lower & higher keys ... the key is the start of the interval
    // TC: O(log n)
    public void addNum(int value){
        Map.Entry<Integer, Integer> lowerEntry = intervals.floorEntry(value);
        int left = value;
        int right = value;

        if(lowerEntry != null){
            int prev = lowerEntry.getValue();
            if(prev >= value){
                return;
            }
            if(prev == value - 1){
                left = lowerEntry.getKey();
            }
        }

        Map.Entry<Integer, Integer> higherEntry = intervals.higherEntry(value);
        if(higherEntry != null && higherEntry.getKey() == value + 1){
            right = higherEntry.getValue();
            intervals.remove(value + 1);
        }

        // merge the lower and higher keys when necessary
        intervals.put(left, right);
    }

    // convert treeMap to 2D int[]
    public int[][] getIntervals(){
        int[][] ans = new int[intervals.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()){
            ans[idx][0] = entry.getKey();
            ans[idx][1] = entry.getValue();
        }

        return ans;
    }
}
