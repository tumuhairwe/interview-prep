package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 1051
 *
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
 *
 * You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).
 *
 * Return the number of indices where heights[i] != expected[i].
 */
public class HeightChecker {
    public static void main(String[] args) {
        System.out.println(heightChecker(new int[]{1,1,4,2,1,3}) + " should be 3");
    }
    public static int heightChecker(int[] heights) {
        int[] copy = Arrays.copyOf(heights, heights.length);
        Arrays.sort(copy);

        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if(heights[i] != copy[i]){
                count++;
            }
        }
        return count;
    }
}
