package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 802 (medium) Find eventual safe states
 * Given a directed graph of n nodes labeled from o to n-1
 * & the graph is a 0-indexed 2D int array where graph[i] is an int[] of nodes
 * adjacent to node_i (i.e. there's an edge from node-i to each node in graph[i]
 *
 * A node is a terminal node if there are no outgoing edges.
 * A node is a safe node if every possible path starting from that node ... leas to a terminal node (or another safe node)
 *
 * Return an array containing all the safeNodes of the graph (sorted in ascending order)
 */
public class EventualSafeStates {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        //0. build adjList
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] in_degree = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            adjList.put(i, new ArrayList<>());

            for (int node = 0; node < graph[i].length; node++) {
                adjList.get(node).add(i);
                in_degree[i]++;
            }
        }

        //1. do bfs on all unvisited nodes
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < graph.length; i++) {
            if(in_degree[i] == 0){
                que.add(i);
            }
        }

        //3. create safe[] & init to false for all nodes
        boolean[] safe = new boolean[graph.length];
        Arrays.fill(safe, false);

        while (!que.isEmpty()){
            int curr = que.poll();
            safe[curr] = true;  // mark node as true since it has 0 in_degree count

            for(int neighbor : adjList.get(curr)){
                // decrease in_degree count per loop
                in_degree[neighbor]--;

                // if 0 -> to que -> mark as safe
                if(in_degree[neighbor] == 0){
                    que.add(neighbor);
                }
            }
        }

        //3. convert terminal nodes to list
        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if(safe[i]){
                safeNodes.add(i);
            }
        }
        safeNodes.sort(Comparator.naturalOrder());
        return safeNodes;
    }
}
