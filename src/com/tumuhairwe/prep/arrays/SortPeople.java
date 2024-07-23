package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 2418 (easy)
 *
 * You are given an array of strings names, and an array heights that consists
 * of distinct positive integers.
 * Both arrays are of length n.
 *
 * For each index i, names[i] and heights[i] denote the name and height of the ith person.
 *
 * Return names sorted in descending order by the people's heights.
 */
public class SortPeople {
    public static void main(String[] args) {
        String[] names = {"Mary","John","Emma"};
        int[] heights = {180, 165, 170};
        System.out.println(Arrays.toString(sortPeople(names, heights)));

        names = new String[]{"Alice","Bob","Bob"};
        heights = new int[]{155,185,150};
        System.out.println(Arrays.toString(sortPeople(names, heights)));
    }

    /**
     * Solution summary
     * - create TreeMap (DS that sorts by keys)
     * - Add heights and names into map (map is sorted in reverse --- reqL "heights sorted in descending order")
     */
    public static String[] sortPeople(String[] names, int[] heights) {
        // 0. create TreeMap -- DS that sort by keys ... (in reverseOrder)
        Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());

        //1. put heights & names in TreeMap(key, value) whichi will sort them by heights in reverseOrder
        for (int i = 0; i < heights.length; i++) {
            map.put(heights[i], names[i]);
        }

        //2. convert Map values to String[] and return
//        map.entrySet().stream()
//                .map(e -> e.getValue())
//                .collect(Collectors.toList())
//                .toArray(new String[0]);
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .toArray(String[]::new);
    }
}
