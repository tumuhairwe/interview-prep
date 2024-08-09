package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 882 Boats to save people
 *
 * You are given array people a where people[i] is the weight of the i-th person
 * and infinite number of boats where each boat can carry a weight of _limit.
 * Each boat carries at most 2 people at the same time, provided the sum of both people
 * is at most _limit
 *
 * Return the minimum number of boats to carry every given person
 * ref: https://leetcode.com/problems/boats-to-save-people/description/
 */
public class BoatsToSavePeople {

    /**
     * Solution summary
     * - sort the array
     * - use 2 pointers (moving towards middle) as indices
     * - if limit of arr[p1] + arr[p2] <= limit, move both pointers towards middle
     * - else only move p2 (large value since array is sorted)
     * - increment the number of boats with each iteration
     * - return number-of-boats
     */
    public int numRescueBoats(int[] people, int limit){
        //0. sort the array
        Arrays.sort(people);

        int p1 = 0;
        int p2 = people.length;
        int numBoats = 0;

        while (p1 <= p2){
            if (people[p1] + people[p2] <= limit){
                p1++;
                p2--;
            }
            else {
                p2--;
            }

            numBoats++;
        }

        return numBoats;
    }
}
