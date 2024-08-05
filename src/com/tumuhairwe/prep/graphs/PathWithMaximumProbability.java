package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 1514 Path with maximum probability (medium)
 *
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 * ref: https://www.youtube.com/watch?v=kPsDTGcrzGM
 * ref: https://leetcode.com/problems/path-with-maximum-probability/description/
 */
public class PathWithMaximumProbability {

    /**
     * Solution summary (Dijkstra's)
     * - Create bi-directional adjacency list (between source and destination in edges[][])
     * - Base Case: check if end_node && start_node are in the graph, if not, exit
     * - Create maxProbability [] and seed with starting probability (probability of src to dest = 1)
     * - Create dq and seed with startingNode
     * - while(!dq.isEmpty())
     *      - poll entry from dq
     *          - if currentNode exists in graph ... visit neighbors
     *      - if its probability is greater, update maxProbability
     *      - visit all neighbors (from adjList)
     *          - calculate newProbability ... it greater, add to pq with new probability
     *  - return maxProbability[end_node]
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        //0. create adjList
        Map<Integer, List<Map.Entry<Integer, Double>>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];
            Double prob = succProb[i];

            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(Map.entry(dest, prob));
            graph.computeIfAbsent(dest, k -> new ArrayList<>()).add(Map.entry(src, prob));
        }

        //1. create probability array[]
        double[] maxProb = new double[n];
        maxProb[start_node] = 1;

        //2. create and seed dq
        Deque<Integer> que = new ArrayDeque<>();
        que.add(start_node);

        while (!que.isEmpty()){
            Integer currentNode = que.poll();
            double currentProb = maxProb[currentNode];

            // traverse neighbors
            if(!graph.containsKey(currentNode)){
                continue;
            }
            List<Map.Entry<Integer, Double>> neighbors = graph.get(currentNode);
            for (Map.Entry<Integer, Double> neigh : neighbors){
                Integer nextNode = neigh.getKey();
                double nextProb = neigh.getValue();

                if(currentProb * nextProb > maxProb[nextNode]){
                    maxProb[nextNode] = currentNode * nextProb;
                    que.add(nextNode);
                }
            }
        }
        return maxProb[end_node];
    }
}
