package com.tumuhairwe.prep.arrays;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * LeetCode 2418 (easy)
 *
 * You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.
 *
 * For each index i, names[i] and heights[i] denote the name and height of the ith person.
 *
 * Return names sorted in descending order by the people's heights.
 */
public class SortPeople {
    public String[] sortPeople(String[] names, int[] heights) {
        Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < heights.length; i++) {
            map.put(heights[i], names[i]);
        }

        return map.entrySet().stream()
                .map(e -> e.getValue())
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }
}
