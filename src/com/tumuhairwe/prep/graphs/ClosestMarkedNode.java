package com.tumuhairwe.prep.graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 2737 (medium)
 * You are given a positive integer n which is the number of nodes of a 0-indexed directed weighted graph
 * and a 0-indexed 2D array edges where edges[i] = [ui, vi, wi] indicates that there is an edge from node ui
 * to node vi with weight wi.
 *
 * You are also given a node s and a node array marked; your task is to find the minimum distance from s to
 * any of the nodes in marked.
 *
 * Return an integer denoting the minimum distance from s to any node in marked or -1 if there are no paths
 * from s to any of the marked nodes.
 *
 * ref: https://leetcode.com/problems/find-the-closest-marked-node/?envType=problem-list-v2&envId=shortest-path
 */
public class ClosestMarkedNode {
    /**
     * Solution summary
     * - Create & populate adjacency List
     * - Create set of terminalNode (i.e. marked[]) to make lookups easier -- O(1)
     * - create cost[] and initialize with Integer.MAX_VALUE. Initialize sourceNodeId = 0
     * - Create minHeap pq that sorts by cost index of the array/entry
     * - seed minHeap with sourceNode
     * - Do DFS on minHeap until you reach a terminalNode
     *  - in the process: for each node, visit neighbors
     *  - for reach neighbor, recalculate newDistance (currentDistance + neighbor.cost)
     *  - if cost is cheaper, update cost[]
     *
     * - if you get to the end (i.e. did not reach terminalNode, return -1;
     *
     * TC: O(V + E log E)
     * SC: O(V + E) (we have to traverse all edges
     * ref: https://leetcode.com/problems/find-the-closest-marked-node/solutions/4739140/java-dijkstra-s-algorithm-simple-easy-template-beats-100/?envType=problem-list-v2&envId=shortest-path
     */
    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        //0. create ajdList
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        for (int i=0; i<n; i++){
            adjList.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.size(); i++) {
            int src = edges.get(i).get(0);
            int dest = edges.get(i).get(0);
            int cost = edges.get(i).get(0);

            adjList.get(i).add(new int[]{dest, cost});
        }

        //1. create terminal set
        Set<Integer> terminalNodes = Arrays.stream(marked).boxed().collect(Collectors.toSet());

        //2. create [] to calc dis
        int[] weight = new int[n];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[s] = 0;

        //3. create pq to sort by weight
        Comparator<int[]> comp = Comparator.comparingInt(arr -> arr[1]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(comp);
        minHeap.offer(new int[]{s, 0});

        //4. traverse graph (until you reach any terminalNode)
        while (!minHeap.isEmpty()){
            int[] currentNode = minHeap.poll();
            int currId = currentNode[0];
            int currWeight = currentNode[1];

            // check if we've reached destination
            if(terminalNodes.contains(currId)){
                return weight[currId];
            }

            // DO NOT USE VisitedSEt

            // check neighbors
            for (int[] neighbor : adjList.getOrDefault(currId, new ArrayList<>())){
                int newDist = currWeight + neighbor[1];
                int neighborId = neighbor[0];

                if(newDist < weight[currId]){
                    minHeap.offer(new int[]{neighborId, newDist});
                    weight[neighborId] = newDist;
                }
            }
        }
        return -1;
    }
}
