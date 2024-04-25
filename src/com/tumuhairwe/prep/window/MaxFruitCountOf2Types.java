package com.tumuhairwe.prep.window;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 904 (medium)
 *
 * Given a farm that has single row of fruit. The trees are represented by a char[] (array fruits)
 * where fruit[i] = the type of fruit the ith-tree produces.
 * You can collect as much as possible however the followng constraints apply
 * - You only have 2 baskets && each basket can only hold 1 types of fruit. There's no limit to the amout of fruit ach basket can hold
 * - Starting from any tree of your choice, you must pick exactly 1 fruit from every tree including the starting tree (while moving to the right)
 * - The picked fruit must fit in one your baskets
 * - Once you reach a tree that connote fit into a basket, you must stop
 *
 * Goal: Find the maximum number of fruits you can pick
 *
 * ref: https://leetcode.com/problems/fruit-into-baskets/description/
 */
public class MaxFruitCountOf2Types {
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " + findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " + findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }
    public static int findLength(char[] fruits){
        int numberOfBaskets = 2;;
        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> fruitFrequencyMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {
            fruitFrequencyMap.put(fruits[windowEnd], fruitFrequencyMap.getOrDefault(fruits[windowEnd], 0)+1);

            // shrink the sliding window until size = 2
            while (fruitFrequencyMap.size() > numberOfBaskets){
                fruitFrequencyMap.put(fruits[windowStart], fruitFrequencyMap.get(fruits[windowStart]) -1);
                if(fruitFrequencyMap.get(fruits[windowStart]) == 0){
                    fruitFrequencyMap.remove(fruits[windowStart]);
                }

                windowStart++;  // shrink the window
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
            }
        }

        return maxLength;
    }
}
