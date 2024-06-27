package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 1791 (easy)
 * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
 *
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
 *
 * ref: https://leetcode.com/problems/find-center-of-star-graph/description/
 * ref: https://www.youtube.com/watch?v=jBD2_Eh4SlU
 */
public class FindCenterOfGraph {
    public static void main(String[] args) {
        int[][] arr = {{1,2},{2,3},{4,2}};
        System.out.println("Should be 2: " + findCenter(arr));

        arr = new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}};
        System.out.println("Should be 1: " + findCenter(arr));
    }

    /**
     * Solution summary
     * - create & populate [] to count number of edges coming in/out of each node
     * - find node that has edges.length number of nodes on it
     * - return node id
     */
    public static int findCenter(int[][] edges){
        //0. create and populate in_degree count []
        int[] nodeDegreeCount = new int[edges.length + 2];
        for (int[] edge : edges){
            nodeDegreeCount[edge[0]]++;
            nodeDegreeCount[edge[1]]++;
        }

        //1. find node that has in-degree count of size edges.length
        for (int i = 0; i < nodeDegreeCount.length; i++) {
            if(nodeDegreeCount[i] == edges.length){
                return i;
            }
        }

        return -1;
    }
}
