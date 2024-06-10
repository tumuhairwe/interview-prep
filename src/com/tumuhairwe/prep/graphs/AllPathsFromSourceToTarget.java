package com.tumuhairwe.prep.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 797 (medium)
 *
 * Given a directed acyclic graph DAG of n nodes labeled from 0 - n-1, find all possible paths from node 0 to n-1
 * and return them in any order
 */
public class AllPathsFromSourceToTarget {
    public static void main(String[] args) {

    }
    public static List<List<Integer>> allPahtsSourceToTarget(int[][] graph){
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
        // since graph is n x n, if index reaches graph.length,we've accummulated all results in sourcePath -> add to global resuls set
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
