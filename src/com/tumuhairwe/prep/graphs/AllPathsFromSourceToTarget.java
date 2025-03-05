package com.tumuhairwe.prep.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 797 (medium)
 *
 * Given a directed acyclic graph DAG of n nodes labeled from 0 - n-1, find all possible paths from node 0 to n-1
 * and return them in any order
 *
 * ref: https://leetcode.com/problems/all-paths-from-source-to-target/description/
 * TC
 */
public class AllPathsFromSourceToTarget {
    /**
     * Solution summary (graph problem with a backtracking solution)
     * 0. init results list && sourcePath (seeded with 0)
     * 1. seed source path with source node_id=0
     * 2. call backtracking (will be recursive) -> terminal condition is if index reaches graph.length - 1 (add to globalResults)
     * 3. for-each neighbor of startingIndex, -> add neighbor to sourcePath list, call backtracking(), remove neighbor from sourcePathList
     * 4. return globalResults
     *
     * TC: O(2 ^ n*n)
     * SC: O(V + E)
     */
    public static List<List<Integer>> allPathsSourceToTarget(int[][] graph){
        //0. init vars
        List<List<Integer>> results = new ArrayList<>();
        int startingIndex = 0;

        //1. seed source path with source node_id
        List<Integer> sourcePath = new ArrayList<>();
        sourcePath.add(0);

        //2. call backtracking (will be recursive)
        backtracking(results, sourcePath, graph, startingIndex);

        return results;
    }

    private static void backtracking(List<List<Integer>> results, List<Integer> sourcePath, int[][] graph, int startingIndex) {
        // since graph is n x n, if index reaches graph.length, we've accumulated all results in sourcePath -> add to global results set
        if(startingIndex == graph.length - 1){
            results.add(new ArrayList<>(sourcePath));
            return;
        }

        //for each neighbor of index, ... temporarily add to the source-path ... backtrack ..then remove from sourcePath
        for (Integer neighbor : graph[startingIndex]){
            sourcePath.add(neighbor);
            backtracking(results, sourcePath, graph, neighbor);
            sourcePath.remove(sourcePath.size() - 1);
        }
    }
}
