package com.tumuhairwe.prep.arrays;

import java.util.stream.IntStream;

/**
 * There are n employees numbered from 0 to n-1.
 * Each employee i has worked hours[i] hours in the company.
 * The company requires each employee to work at least target hours
 * You are given a 0-indexed array of non-negative integers of length n and a non-negative integer target
 * Return the integer denoting the number of employees who worked at least target hours
 *
 * ref: https://leetcode.com/problems/number-of-employees-who-met-the-target/description/
 */
public class NumberEmployeesWhoMetTarget {

    /**
     * Solution summary
     * - iterate of hours[]
     * - filter array by hour >= target
     * - return count of matching hours
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        return (int) IntStream.of(hours)
                .filter(h -> h>= target)
                .count();
    }
}
