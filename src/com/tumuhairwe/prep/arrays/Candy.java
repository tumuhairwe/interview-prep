package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 135 (hard)
 * There are n children in a line. Each child is assigned a rating value given in the integer
 * array ratings.
 * You are giving candy to these children subject to the following requirements
 * Each child must have at least 1 candy.
 *
 * - Each child must have at least 1
 * - children with a higher rating get more candy than their immediate neighbors
 *
 * Return the minimum number of candies you need to have to distribute the candy to the children
 *
 * ref: https://leetcode.com/problems/candy/description/
 * ref: https://leetcode.com/problems/candy/solutions/4037646/99-20-greedy-two-one-pass/
 * ref: https://www.youtube.com/watch?v=JqrZHuhljps&t=340s
 */
public class Candy {

    /**
     * Solution summary
     * - do forward pass & calculate the value of candies[i] = candy_of_left + 1
     * - do backward pass & calculate the value of candies[i] = max(candy_of_right + 1, candy_of_i)
     * - calculate sum of all candies and return it
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int candy(int[] ratings){
        List<Integer> ratingsList = Arrays.stream(ratings).boxed().collect(Collectors.toList());
        //0. init vars
        int n = ratings.length;
        int[] candies = new int[n];

        //1. fill return value with minimum
        Arrays.fill(candies, 1);

        //2. forward pass: calculate right neighbor
        for (int i = 1; i < n; i++) {
            // if ratingOfLeftNeighbor is greater, set to (candyOfLeftNeighbor + 1)
            if(ratings[i] > ratings[i-1]){
                int candyOfLeftNeighbor = candies[i - 1];
                candies[i] = candyOfLeftNeighbor + 1;
            }
        }

        //3. backward pass: calculate right neighbor
        for (int i = n-1; i >=0; i--) {
            if(ratings[i] > ratings[i + 1]){
                int candyOfRightNeighbor = candies[i + 1];
                candies[i] = Math.max(candies[i], candyOfRightNeighbor + 1);
            }
        }

        //4. calculate sum & return
        return Arrays.stream(candies).sum();
    }

    public int candy2(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }

        //0. init vars
        int returnValue = 1;

        /*
        tracks how many children have INCREASING ratings from the last child
        this helps us determine how many candies we need for a child with a higher rating than the prev child
        */
        int up = 0;

        /*
        tracks how many children have DECREASING rating from the last child
        this helps us determine how many candies we need for a child with a lower rating than the prev child
        */
        int down = 0;

        /*
        keeps track of the last HIGHEST point in an increasing sequence. When we have a decreasing sequence after the peak, we can refer back to peak to adjust the number of candies if needed
        */
        int peak = 0;

        //2. for each pair of adjacent children...
        // List<Integer> ratingsList = Arrays.stream(ratings)
        //     .boxed()
        //     .collect(Collectors.toList());
        // List<Integer> ratingsList = new ArrayList<>();
        // for(int i=0; i<ratings.length; i++){
        //     ratingsList.add(ratings[i]);
        // }
        for(int i=1; i<ratings.length; i++){
            int prev = ratings[i - 1];
            int curr = ratings[i];

            if(prev < curr){
                up++;
                down = 0;
                peak = up;
                returnValue += 1 + up;
            }
            else if(prev == curr){
                up = 0;
                down = 0;
                peak = 0;
                returnValue += 1;
            }
            else{
                up = 0;
                down++;
                returnValue += 1 + down - (peak >= down ? 1 : 0);
            }
        }

        return returnValue;
    }
}
