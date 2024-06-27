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

    class Entry{
        private int nodeId;
        private double probability;
        public Entry(int dest, double prob){
            this.nodeId = dest;
            this.probability = prob;
        }
    }

    /**
     * Solution summary (Dijkstra's)
     * - Create bi-directional adjacency list (between source and destination in edges[][])
     * - Base Case: check if end_node && start_node are in the graph, if not, exit
     * - Create pq (comparator is reversed so sort with HIGHEST probability)
     * - Seed pq with starting node (probability == 1)
     * - Declare vars to use
     *      - (Set<Integer> visited to track visited nodes)
     *      - double[] as global of array of probabilities
     * - while(!pq.isEmpty())
     *      - poll entry from pq
     *          - if its end node, return its probability
     *          - if its probability is greater, skip
     *      - add to visited Set
     *      - visit all neighbors (from adjList)
     *          - if neighbor is visited already, skip
     *          - calculate newProbability ... it greater, add to pq with new probability
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        //0. create adjList
        Map<Integer, List<Entry>> adjList = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];

            adjList.put(source, new ArrayList<>());
            adjList.put(destination, new ArrayList<>());

            Entry e1 = new Entry(destination, succProb[i]);
            adjList.get(source).add(e1);

            Entry e2 = new Entry(source, succProb[i]);
            adjList.get(destination).add(e2);;
        }

        //1. base case: in case start_node or end_node don't exist in graph, return
        if(!adjList.containsKey(start_node) || !adjList.containsKey(end_node)){
            return 0;
        }

        // seed pq and iterate over all neighbors of start_node -
        //2. create maxHeap and order y probability
        Comparator<Entry> comp = Comparator.comparingDouble(e -> e.probability);
        PriorityQueue<Entry> pq = new PriorityQueue<>(comp.reversed());

        //2.1 seed it with starting node
        Entry startingEntry = new Entry(start_node, 1);
        pq.add(startingEntry);

        // 3. declare vars to track visited and SOR for probabilities to be used compare
        Set<Integer> visited = new HashSet<>();
        double[] probabilities = new double[adjList.size()];
        Arrays.fill(probabilities, -1);

        //3. iterate pq and poll entry with max probability
        while (!pq.isEmpty()){
            Entry current = pq.poll();
            double totalProbability = current.probability;;

            // break when we find the target node
            if(current.nodeId == end_node){
                return totalProbability;
            }

            // if probability is greater ... skip
            if(probabilities[current.nodeId] > current.probability){
                continue;   // this node's probability is higher
            }

            //4. mark as visited
            if(!visited.contains(current.nodeId)){
                visited.add(current.nodeId);
            }

            //5. get destination's neighbors ... if (unvisited + probability is lower, add to pq)
            List<Entry> neighbors = adjList.get(current.nodeId);
            for (Entry neighbor : neighbors){
                if(visited.contains(neighbor.nodeId)){
                    continue;
                }

                double newProbability = neighbor.probability * current.probability;
                if(newProbability > probabilities[current.nodeId]){
                    Entry node = new Entry(neighbor.nodeId, newProbability);
                    pq.add(node);
                }
            }
        }

        return 0;   // we've exited the minHeap without finding the end_node
        // (unlikely .. since we're explicitly checking for it before start traversing the graph)
    }
}
