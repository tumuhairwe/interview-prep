package com.tumuhairwe.prep.graphs;

import java.util.Arrays;

/**
 * LeetCode Maximum Total Importance of Roads
 *
 * Given an integer n denoting the number of cities in a country. The cities are number 0 to n - 1
 * Given a 2D int[] roads, where roads[i] = [a, b] denotes that there exists a bidirectional road connecting cities a
 * and b
 * You need to assign each city with an integer value from 1 to n, where each value can only be used once
 * The importance of a road is defined as the su9m of values of the 2 cities it connects
 *
 * Return the maximum total importance of all road, possible after assigning values optimally.
 *
 * ref: https://www.youtube.com/watch?v=NIhXLEiQAPM&t=69s
 * ref: https://leetcode.com/problems/maximum-total-importance-of-roads/description/
 * ref: https://leetcode.com/problems/maximum-total-importance-of-roads/solutions/5380397/easy-with-explanation-java-javascript-c-python3/
 */
public class MaximumTotalImportanceOfRoads {
    public static void main(String[] args) {
        int[][] roads = {
                {0,1},{1,2},{2,3},{0,2},{1,3},{2,4}
        };
        int n = 5;
        System.out.println("Should be 43: " + maximumImportance(n, roads));
    }
    public static long maximumImportance(int n, int[][] roads){
        // create in-degree []
        int[] in_degree = new int[n];
        for (int[] edge : roads){
            int src = edge[0];
            int dest = edge[1];

            in_degree[src]++;
            in_degree[dest]++;
        }

        //1. sort the edge-count ASC
        Arrays.sort(in_degree); // goal: cities/nodes with lower edge-count will have lower importance (i index)

        //2. calculate result
        int result = 0;
        int importance = 1;
        for (int connection : in_degree){
            result += connection * importance;
            importance++;   // cities with more connections progressively get more importance
        }

        return result;
    }
}
