package com.tumuhairwe.prep.linesweep;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 911 (medium)
 * Online election
 * Given 2 integer arrays (persons and times), in an election the i-th vote was the persons[i] at the time times[i]
 *
 * For each query at a time t, find the person that as leading the election as the time.
 * Votes cast at time t, will count towards our query. In the case of a tie, the most recent vote among tied candidates wins
 *
 * Implement the TopVotedCandidate class
 * - constructor(int[] persons, int[] times) -- initializes the object with time and persons arrays
 * - int q(int i) -- returns the number of the person that was leading the election at the time t.
 *
 * ref: https://leetcode.com/problems/online-election/description/
 */
public class TopVotedCandidate {

    private TreeMap<Integer, Integer> time_by_leadingCandidate = new TreeMap<>();

    /**
     * Solution Summary
     * - Create and populate a voteTally_freqMap
     * - Create and pre-populate the time_by_leadingCandidate treeMap
     * - Update the majorityVotGetter if necessary
     * - Since times is sorted, put() key=time, value=majorityVoteGetter into map
     */
    public TopVotedCandidate(int[] persons, int[] times){
        int majorityVoteGetter = 0;
        int maxVoteCount = 0;
        Map<Integer, Integer> voteTally = new HashMap<>();

        //prepopulate map of time-by-leadingCandidate
        for (int i = 0; i < times.length; i++) {
            //0. update voteTally
            int existingCount = voteTally.getOrDefault(persons[i], 0);
            voteTally.put(persons[i], existingCount + 1);

            //1. update voteTally and majorityGetter
            if(voteTally.get(persons[i]) >= maxVoteCount){
                maxVoteCount = voteTally.get(persons[i]);
                majorityVoteGetter = persons[i];
            }

            //2. put in time_by_leadingCandidate map
            time_by_leadingCandidate.put(times[i], majorityVoteGetter);
        }
    }

    /**
     * Use floorKey() to get vote-getter with <= timestamp
     * if vote-getter is null, return 0, otherwise, return vote-getter
     */
    public int getLeadingVoteGetter(int timestamp){
        Map.Entry<Integer, Integer> floor = time_by_leadingCandidate.floorEntry(timestamp);
        if(floor == null){
            return 0;
        }
        return floor.getValue();

        //Integer closestTabulatedTime = time_by_leadingCandidate.floorKey(timestamp);
        //return closestTabulatedTime == null? 0 : time_by_leadingCandidate.get(time);
    }
}
