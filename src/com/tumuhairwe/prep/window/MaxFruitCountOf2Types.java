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
        System.out.println("Maximum number of fruits: " + totalFruit(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " + totalFruit(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }

    /**
     * Solution summary
     * - create frequency Map
     * - while freqMap.keys > boundary => decrement frequency  & slide window forward
     * - remove from map if key's freq == 1
     * - update max every iteration
     * - return max
     *
     * TC: O(n) -- we iterate thru the [] once & the 2 pointers (windowStart/windowEnd) are monotonically increasing
     * SC: O(1) -- there are (at most) n/numBaskets types of fruits in the window/hashMap -> SC: (1)
     */
    public static int totalFruit(char[] fruits){
        //0 declare vars
        int numberOfBaskets = 2;;
        int windowStart = 0;
        int maxLength = 0;

        //1. create freqMap
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {
            freqMap.put(fruits[windowEnd], freqMap.getOrDefault(fruits[windowEnd], 0)+1);

            // while freqMap.keys.size < boundary (in this case numberOfBaskets)
            // decrement frequency & slide window forward (remove from map if key's frequency == 0)
            // shrink the sliding window until size = 2
            while (freqMap.size() > numberOfBaskets){
                freqMap.put(fruits[windowStart], freqMap.get(fruits[windowStart]) -1);
                if(freqMap.get(fruits[windowStart]) == 0){
                    freqMap.remove(fruits[windowStart]);
                }

                windowStart++;  // move the window forward

                //3. update max every iteration
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
            }
        }

        return maxLength;
    }
}
