package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 1319 (medium)
 *
 * Number of Operations to make network connected
 *
 * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where
 * connections[i] = [a_i, b_i] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
 *
 * You are given an initial computer network connections.
 * You can extract certain cables between two directly connected computers, and place them between any pair of
 * disconnected computers to make them directly connected.
 *
 * Return the minimum number of times you need to do this in order to make all the computers connected.
 * If it is not possible, return -1.
 */
public class NumberOfOperationsToMakeNetworkConnected {

    /**
     * Solution summary
     * - base case: if connections.length < n -1, return
     * - Create adjList of connections
     * - create boolean visited[] of size n - 1
     * - loop thru each node (from 0 to n-1)
     *      - if node is unvisited, mark it as visited, ...
     *      - call bfsVisit()
     *      - increment numberOfOperations
     *  - return numberOfOperations - 1
     */
    public int makeConnected(int n, int[][] connections) {
        //0. base case
        if(connections.length < n -1){
            return -1;
        }

        //1. create adjList
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : connections){
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);

            adjList.putIfAbsent(edge[1], new ArrayList<>());
            adjList.get(edge[1]).add(edge[0]);
        }

        //2. declare vars
        boolean[] visited = new boolean[n - 1];
        int numOperations = 0;
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                bfsVisit(adjList, visited, i);
                numOperations++;
            }
        }

        return numOperations - 1;
    }

    /**
     * Solution summary
     * - Create and seed que with node
     * - while !q.isEmpty()
     *      - pull node from que
     *      - mark node as visited (in visited[])
     *      - for each of the node's neighbors ... if unvisited, add to queue
     * - loop will exit after visiting all the nodes' connections and marking them as visited
     */
    void bfsVisit(Map<Integer, List<Integer>> adjList, boolean[] visited, int node){
        Queue<Integer> que = new ArrayDeque<>();
        que.add(node);

        while (!que.isEmpty()){
            int n = que.poll();
            visited[n] = true;

            if(!adjList.containsKey(n)){
                continue;
            }

            for (int neighbor : adjList.get(n)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    que.offer(neighbor);
                }
            }
        }
    }
}
