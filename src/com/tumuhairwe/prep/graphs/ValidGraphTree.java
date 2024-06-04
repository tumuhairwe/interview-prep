package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 261 (Graph valid tree)
 *
 * Given a graph of n nodes labeled from 1 to n-1.
 * And given a list of edges where edges[i] = [a, b]  means there's an undirected graph between node A and B in the
 * graph
 *
 * Return true if the edges of a given graph make a valid tree
 * definition: valid tree mean no unconnected nodes + no cyclic loops
 * ref: https://leetcode.com/problems/graph-valid-tree/description/
 * ref: https://www.youtube.com/watch?v=bXsUuownnoQ
 */
public class ValidGraphTree {
    Set<Integer> visited = new HashSet<>();
    Map<Integer, List<Integer>> adjList = new HashMap<>();

    // TC: O(E + V)
    public boolean validTree(int n, int[][] edges) {
        // 0. account for base case
        if(n  == 0 || n == 1){
            return true;
        }

        if(edges.length == 0){
            return false;
        }

        // 1. create adj list
        // 2. populate adjList (biderectional)
        for(int[] edge : edges){
            int source = edge[0];
            int destination = edge[1];

            adjList.putIfAbsent(source, new ArrayList<>());
            adjList.putIfAbsent(destination, new ArrayList<>());

            adjList.get(source).add(destination);
            adjList.get(destination).add(source);
        }

        // 3. perform dfs
        return visited.size() == n && depthFirstSearch(edges[0][0], -1, visited);
    }

    boolean depthFirstSearch(Integer nodeId, Integer parent, Set<Integer> visited){
        // 0. check if visited
        if(visited.contains(nodeId)){
            return false;
        }

        // 1. mark as visited
        visited.add(nodeId);

        for(int neighbor : adjList.get(nodeId)){
            if(neighbor == parent){
                continue;
            }
            if(!depthFirstSearch(neighbor, nodeId, visited)){
                return false;
            }
        }
        return true;
    }
}
