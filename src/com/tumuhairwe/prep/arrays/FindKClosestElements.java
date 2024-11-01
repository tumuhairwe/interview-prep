package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 658 (medium)
 * Given a sorted int[] & 2 integers (k and x) reutrn the K closest integers to  int eh array
 * The result should be in ascending order
 *
 * ref: https://leetcode.com/problems/find-k-closest-elements/description/
 */
public class FindKClosestElements {

    /**
     * Solution summary
     * - Convert [] into sortable collection (List)
     * - Sort by absolute value closest to K (use custom comparator)
     * - Get the 1st K elements -> assign to list
     * - sort the list of (of returnable elements)
     * - return elements
     *
     * TC: O(n log_n)
     * SC: O(n)
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //0. collect[] into List
        List<Integer> elements = Arrays.stream(arr).boxed().collect(Collectors.toList());

        //1 sort with custom comparator (absolute distance from node to X)
        Comparator<Integer> comp = Comparator.comparingInt((Integer a) -> Math.abs(a - x));
        Collections.sort(elements, comp);

        //2. get the 1st K elements
        List<Integer> closest = elements.subList(0, k);

        // sort with natural order
        Collections.sort(closest);

        return closest;
    }
}
