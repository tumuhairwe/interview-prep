package com.tumuhairwe.prep.graphs;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 1029 (medium)
 * A company is planning to interview 2n people,
 * Given an array costs where costs[i] = [aCost, bCost],
 * is the cost of flying the i-th person to city-A and to cost of flying to city-B
 *
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city
 *
 */
public class TwoCityScheduling {

    /**
     * Solution summary (greedy)
     * -since the company is paying price_a to send a person to city_a and price_b to city_b
     * - sending a company to city_A, the company would lose [price_a - price_b]
     * Goal: Sort the persons in ascending order [price_A - price_B]
     * - Send the 1st n persons with the smallest cost to city_A ,... and the others to city_B
     *
     * - Implement comparator that will sort by (diff-between-price_a - diff_between_price_B)
     */
    public int twoCitySchedCost(int[][] costs) {
        // 0. sort costs by cost of trip to A
        Comparator<int[]> comp = (int[] scheduleA, int[] scheduleB) -> {
            return (scheduleA[0] - scheduleA[1]) - (scheduleB[0] -scheduleB[1]);
        };
        Arrays.sort(costs, comp);

        // 1. add lowest costs to city A
        int totalCost = 0;
        for (int i = 0; i < costs.length/2; i++) {
            totalCost += costs[i][0];   // cost of trip to A
        }

        //2. add lowest cost to city B
        for (int i = costs.length/2; i < costs.length; i++) {
            totalCost += costs[i][1];   // cost of trip to B
        }

        return totalCost;
    }
}
