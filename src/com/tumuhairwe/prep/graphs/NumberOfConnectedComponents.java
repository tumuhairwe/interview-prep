package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 323 (medium)
 * Given a graph of n nodes, and an integer array (edges) where
 * edges[i] == [a, b] which indicates that an edge between a and b in the graph exists
 *
 * Return the number of connected components in graph
 * ref: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
 */
public class NumberOfConnectedComponents {

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0,1},{1,2},{3,4}
        };

        int n = 5;
        int number = getNumberOfConnectedComponents(edges, n);
        System.out.println("In a graph of " + n + " nodes, there are " + number + " connected components ");

        n = 5;
        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        number = getNumberOfConnectedComponents(edges, n);
        System.out.println("In a graph of " + n + " nodes, there are " + number + " connected components ");
    }

    private static int getNumberOfConnectedComponents(int[][] edges, int n){
        // 0. create adj list
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        // 1. see adjList
        for (int i = 0; i < n; i++) {
            adjList.put(i, new ArrayList<>());
        }

        // 2. populate adj-list & establish relationships
        for (int[] edge : edges){
            int source = edge[0];
            int destination = edge[1];

            adjList.get(source).add(destination);
            adjList.get(destination).add(source);
        }

        // 3. mark each unvisited node as visited
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int nodeId = 0; nodeId < n; nodeId++) {
            if(visited.add(nodeId)){
                doDfsRecursively(nodeId, adjList, visited);
                count++;
            }
        }

        return count;
    }

    private static void doDfsRecursively(int nodeId, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        for (int destination : adjList.get(nodeId)){
            if(visited.add(destination)){
                doDfsRecursively(destination, adjList, visited);
            }

        }
    }
}
