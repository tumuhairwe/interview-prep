package com.tumuhairwe.prep.array;

import java.util.Stack;

/**
 * LeetCode 739
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
     * - Use stack to track the day
     * - Use stack to keep track of days & update result[]
     * - Iterate thru array and use stack to track day-with-a-higher-temp
     * - update result when you encounter a temp where temp[day] > temp[stack.pee()];
     */
    public int[] dailyTemperatures(int[] temps){
        //0. build result[] and fill it with zeros
        int[] result = new int[temps.length];

        //1. create stack to track day/index_of_temp
        Stack<Integer> stack = new Stack<>();
        for (int day = 0; day < temps.length; day++) {
            // 2. while temp[day] > too_of_stack -> pop & populate result[]
            while (!stack.isEmpty() && temps[day] > temps[stack.peek()]){
                int prevDay = stack.pop();
                result[prevDay] = day - prevDay;
            }

            stack.push(day);
        }

        return result;
    }
}
