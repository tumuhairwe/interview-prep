package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 743
 * Given a network of n nodes, labeled from 1 to n,
 * Given the times list of travel times as directed edges where
 * times[i] = { sourceNode, targetNode, timeTaken}
 *
 * We are sending a signal from a given node. Return the minimum time it
 * takes for all the nodes to receive the signal.
 * If impossible, return -1
 *
 * https://leetcode.com/problems/network-delay-time/description/
 *
 * Solution
 * - Build an adjacency list Map<Integer, int[]> -> Goal: Efficiently access the neighbors of each node and their corresponding edge weights
 * - Initialize distances array to store distances from source (node k) to all other nodes (initial distance-to-self = ZERO, distance-to-all-others=Integer.POSITIVE_INFINITY
 * -Implement Dykstra's algo
 *      - use PQ (minHeap) to track the nodes to be explored next. Start by adding just sourceNode, Repeat below until Q is empty
 *      - Extract the node with the smallest distance from the pq (pq.poll() )
 *      - For each of its neighbors, update their distances if a shorter 1 is found
 *      - Add neighbors to the PQ
 * - Check for unreachable nodes
 *      - If above loop if pq is not empty (i.e. there are still nodes with INFINITE distance i.e. unreachable from source) ... return -1
 * - Return minimum distance found in the previous step
 */
public class NetworkDelay {

    static class Node{
        Integer destination;
        int timeTaken;
        public Node (int d, int cost){
            this.destination = d;
            this.timeTaken = cost;   // aka distance
        }
    }
    public static int networkDelayTime(int[][] times, int numberOfNodes, int startingNode){
        final int MAX_VALUE = 10000;    //Double.POSITIVE_INFINITY;

        // 0. build adjacency list [key = sourceNode, value=SetOf_destinations reachable from that node + weight/distance/distance
        Map<Integer, Set<Node>> adjacencyList = new HashMap<>();
        for (int i=0; i< times.length; i++){
            int source = times[i][0];
            int targetNode = times[i][1];
            int timeTaken = times[i][2];
            Node value = new Node(targetNode,timeTaken);

            adjacencyList.putIfAbsent(source, new HashSet<>());
            adjacencyList.get(source).add(value);
        }

        // 1. create PQ to track the closest distance from k
        // PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.timeTaken));
        pq.offer(new Node(startingNode, 0));    // distance to self == 0


        // 2. create set to track what's visited
        //boolean[] visited = new boolean[numberOfNodes + 1];
        Set<Integer> visited = new HashSet<>();

        // 3. track distances
        int[] distance = new int[numberOfNodes + 1];
        Arrays.fill(distance, MAX_VALUE);

        while (pq.size() > 0){
            Node currentNode = pq.poll();
            if(visited.contains(currentNode.destination)){
                continue;
            }

            visited.add(currentNode.destination);
            numberOfNodes--;

            Set<Node> neighbors = adjacencyList.getOrDefault(currentNode.destination, new HashSet<>());
            for (Node neighbor : neighbors){
                int newDistance = distance[currentNode.destination] + neighbor.timeTaken;

                // 4. update distance if larger
              distance[neighbor.destination] = Math.min(distance[neighbor.destination], newDistance);

                // mark node as visited
                pq.add(new Node(neighbor.destination,newDistance));
            }
        }

        return numberOfNodes == 0 ? distance[1] : -1;
    }
}
