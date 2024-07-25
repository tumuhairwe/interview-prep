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

    /**
     * Solution Summary
     * - Create adjList for all edges
     * - populate adjList bidirectionally (source -> destination) && (destination -> source)
     * - for each nodeId,
     *      - if a neighbor is unvisited, add to visited set and
     *      - call do_dfs() to recursively collect & count all connected/visited neighbors
     *      - increment count of visited nodes
     * - return count
     */
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
            if(visited.add(nodeId)){    // recursively add node to the visited set
                doDfsRecursively(nodeId, adjList, visited);
                count++;
            }
        }

        return count;
    }

    private static void doDfsRecursively(int nodeId, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        for (int destination : adjList.get(nodeId)){
            if(visited.add(destination)){   // if node added successfully, add neighbors
                doDfsRecursively(destination, adjList, visited);
            }

        }
    }

    /**
     * Solution summary
     * - Create adjList of edges
     * - Create boolean visited[] of size n
     * - perform DFS for each univisited node & mark all neighbors as visited
     * - increment count by 1;
     * - return count when for loop exits
     */
    public int countComponents_impl2(int n, int[][] edges) {
        //0. create adjList
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges){
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.putIfAbsent(edge[1], new ArrayList<>());

            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        //
        int count = 0;
        boolean[] visited = new boolean[n];
        for(int node=0; node < n; node++){
            if(!visited[node]){
                doDfs(adjList, visited, node);
                count++;
            }
        }

        return count;
    }

    void doDfs(Map<Integer, List<Integer>> adjList, boolean[] visited, int nodeId){
        Queue<Integer> que = new ArrayDeque<>();
        que.add(nodeId);

        while (!que.isEmpty()) {
            int n = que.poll();
            visited[n] = true;

            if(!adjList.containsKey(n)){
                continue;
            }

            for(int neighbor : adjList.get(n)){
                if(!visited[neighbor]){
                    que.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }
}
