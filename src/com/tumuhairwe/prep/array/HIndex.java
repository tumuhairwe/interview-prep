package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 274 H-Index
 * Given an array of integers (citations) where citations[i] is the number of citations
 * a researcher received for their i-th paper, return the H-index
 *
 * def: H-index is defined as the maximum value of H such that the given researchers
 * has published at least H papers that have each been cited at least H times
 *
 * ref: https://leetcode.com/problems/h-index/description/
 */
public class HIndex {

    public static void main(String[] args) {
        int[] arr = {3,0,6,1,5};
        System.out.println(hIndex(arr));

        arr = new int[]{1,3,1};
        System.out.println(hIndex(arr));
    }
    /**
     * Solution summary
     * - sort array
     - iterate array (in ascending order) and find the 1st index where i the index's value is > than the index
     - return i
     *
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations){
        //0. sort
        Arrays.sort(citations);

        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i){
            i++;
        }
        return i;
    }
}
