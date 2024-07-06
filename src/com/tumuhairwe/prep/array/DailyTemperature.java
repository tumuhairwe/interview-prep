package com.tumuhairwe.prep.array;

import java.util.Stack;

/**
 * LeetCode 739 (medium)
 *
 * Given an array of integers temperatures, representing the daily temps, return an array answer
 * such that answer[i] is the number of days you have to wait after the ith day to get warmer
 * a temperature.
 * If there's no future day for which this is possible, keep answer[i] = 0 instead
 *
 * ref: https://leetcode.com/problems/daily-temperatures/description/
 * aka Monotonic Stack
 */
public class DailyTemperature {

    public static void main(String[] args) {

    }
    /**
     * Solution Summary
     * - Use stack to keep track of days & update result[]
     * - Iterate thru array and use stack to track day-with-a-higher-temp
     *      - push(day) on stack for each iteration
     *      - update result when you encounter a temp where temp[day] > temp[stack.peek()];
     *  - return result[] (number to dyas to wait for warmer
     */
    public int[] dailyTemperatures(int[] temps){
        //0. build result[] and fill it with zeros
        int[] numberOfDaysToWait = new int[temps.length];

        //1. create stack to track day/index_of_temp
        Stack<Integer> days = new Stack<>();
        for (int day = 0; day < temps.length; day++) {
            // 2. while temp[day] > too_of_stack -> pop & populate result[]
            while (!days.isEmpty() && temps[day] > temps[days.peek()]){
                int previousDay = days.pop();
                numberOfDaysToWait[previousDay] = day - previousDay;
            }

            days.push(day);
        }

        return numberOfDaysToWait;
    }
}
