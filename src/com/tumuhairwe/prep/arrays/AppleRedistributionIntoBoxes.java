package com.tumuhairwe.prep.arrays;

import java.util.Arrays;

/**
 * LeetCode 3074 (easy)
 * You're given an array apple of size n and an array capacity of size m
 * There are n packs where the i-th pack contains apple[i] apples. There are m boxes
 * as well and the i-th box has capacity[i] apples
 * Return the minimum number of boxes you need to select to redistribute these n packs of apples
 *  into boxes
 *
 *  Note that apples from the same pack can be distributed into different boxes
 * ref: https://leetcode.com/problems/apple-redistribution-into-boxes/
 */
public class AppleRedistributionIntoBoxes {
    //TC: O(n log n) to sort (O(n) to iterate)
    //SC: O(1)
    public int minimumBoxes(int[] apple, int[] capacity){
        //0. sort capacity
        Arrays.sort(capacity);

        //1. calc total capacity
        int totalCapacity = Arrays.stream(apple).sum();

        //2. iterate capacity backwards -> subtract capacity[i] from total until == 0
        int numBoxes = 0;
        for (int i = capacity.length; i >= 0; i--) {
            if(totalCapacity <=0){
                return numBoxes;
            }

            //3. decrement totalCapacity by capacity[i]
            totalCapacity -= capacity[i];

            //increment numBoxes
            numBoxes++;
        }

        return numBoxes;
    }
}
