package com.tumuhairwe.prep.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1213 (easy)
 * Given 3 integer arrays arr1, arr2, and arr3 sorted in strictly increasing order,
 * return a sorted array of only the integers that appeared in all 3 arrays
 *
 * ref: https://leetcode.com/problems/intersection-of-three-sorted-arrays/description/
 */
public class IntersectionOfThreeSortedArrays {
    /**
     * Solution summary
     * - init 3 pointers to 0 & results list
     * - if the values at each pointer are all equal, increment all 3 pointers & add 1 of them to resultList
     * - if value p2 < p3, increment p2
     * - else increment just p3
     * - return resultList which contains the values that occur in all 3
     */
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        //0. create 3 pointers
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        List<Integer> result = new ArrayList<>();
        while(p1 < arr1.length && p2 < arr2.length && p3 < arr3.length){
            if(arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]){
                result.add(arr1[p1]);

                //move all 3
                p1++;
                p2++;
                p3++;
            }
            else if(arr2[p2] < arr3[p3]){
                p2++;
            }
            else{
                p3++;
            }
        }

        return result;
    }
}
