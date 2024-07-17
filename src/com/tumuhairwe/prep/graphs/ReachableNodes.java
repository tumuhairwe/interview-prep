package com.tumuhairwe.prep.graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 2368
 *
 * There is an undirected tree with n nodes labled from 0 to n-1, and n-1 edges
 * You're given a 2D integer array of edges of length n-1 where edges[i] = [a- b] inddicates
 * that there is an edge between nodes a and b in the tree
 * You're also given an integer array restricted which represent nodes.
 *
 * return the maximum number of nodes you can reach from node 0 without visiting a restricted node
 *
 */
public class ReachableNodes {

    /**
     * Solution summary
     * - Create adjList of edges
     * - Create visited set of restricted edges (and add startingPoint)
     * - Create deque and seed it with startingPoint
     * - while !que.isEmpty()
     *      - pull currentNode from que
     *      - increment count
     *      - for all unvisited neighbors of currentNode -> add to visitedSet -> add to que
     * - return maxNodes
     *
     * TC: Typical BFS tc = O(V+E), but since In this problem, there are n nodes and n−1 edges -> tc = O(n)
     * SC: O(n) == There may be up to n nodes stored in queue which takes O(n) space.
     */
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        //0. create adjList
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges){
            int src = edge[0];
            int dest = edge[1];

            adjList.putIfAbsent(src, new ArrayList<>());
            adjList.putIfAbsent(dest, new ArrayList<>());

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

        //1. create visited set
        Set<Integer> visited = Arrays.stream(restricted).boxed().collect(Collectors.toSet());
        visited.add(0); // add starting point

        Deque<Integer> que = new ArrayDeque<>();
        que.add(0);

        int maxNodes = 0;
        while (!que.isEmpty()){
            int currentNode = que.poll();
            maxNodes++;

            for (Integer neighbor : adjList.get(currentNode)){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);
                    que.offer(currentNode);
                }
            }
        }

        return maxNodes;
    }
}
