package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 743 (medium)
 * ref: https://leetcode.com/problems/network-delay-time/description/
 *
 *  Problem Description
 *  - Given a network of N nodes (labeled from 1 to n)
 *  - Given a list of travel times (int[][] times) such that times[i] = (sourceNode, targetNode, time) where time = time from source to target
 *  - Given a given node k
 *  Return the minimum time it takes for all the n nodes to receive the signal.
 *  If impossible for all the n nodes to receive the signal, return -1
 *
 * Solution:
 * - Create an adjacency list to store the graph (node + edges_and_cost)
 * - Use PQ to store nodes and distance/delay-time. Initialize queue with the source + distance = 0
 * - Use a visited set to track the nodes that have already been processed
 * - Process nodes from the PQ by visiting the node with the smallest delay time & update if necessary
 * - Add the unvisited neighbors of the processed node to the PQ (with their new distance/delay times)
 * - Return the delay time of all the nodes that have been processed, otherwise -1
 *
 * TC: O(E log N) -- where E = total number of edges & N = number of nodes in the network
 * (since push() and pop() operations take O(log n ) time)
 *
 * SC: O(N + E) where N = number of nodes in graph and E = number of edges required by the adjacency matrix and PQ
 */
class NetworkDelayTime {
    public static int networkDelayTime(int[][] times, int n, int k) {
        // 0. create adjacency list Map<key=nodeId, value=L[destination, time]>
        Map<Integer, Integer> m = new HashMap<>();
        m.values().stream().sorted().findFirst();
        Map<Integer, List<int[]>> adjacency = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int targetNodeId = time[1];
            int travelTime = time[2];
            adjacency.computeIfAbsent(source, key -> new ArrayList<>()).add(new int[]{travelTime, targetNodeId});
        }

        // 1. create PQ to sort by time (index=0) == entry = {0=time, 1=nodeId}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // 2. see pq with self (0=time, 1=nodeId)
        pq.offer(new int[]{0, k});
        Set<Integer> visited = new HashSet<>();
        int delays = 0;

        while (!pq.isEmpty()) {
            // 3. pull top of minHeap
            int[] current = pq.poll();
            int time = current[0];
            int nodeId = current[1];

            // 4. check if unvisited
            if (visited.contains(nodeId))
                continue;

            // 5. add to visited
            visited.add(nodeId);

            // 6. update delays/time-taken
            delays = Math.min(delays, time);

            // 7. pull neighbors from adjaceny List
            List<int[]> neighbors = adjacency.getOrDefault(nodeId, new ArrayList<>());

            // 8. DFS on each neighbpr (add to PQ (0=newTime = (time-from-THIS-node + time-to-get-to-neighbor, nodeId=neighborId) )
            for (int[] neighbor : neighbors) {
                int neighborTime = neighbor[0];
                int neighborNodeId = neighbor[1];
                if (!visited.contains(neighborNodeId)) {
                    int newTime = time + neighborTime;

                    // 9. add to pq
                    pq.offer(new int[]{newTime, neighborNodeId});
                }
            }
        }

        if (visited.size() == n)
            return delays;

        return -1;
    }

    public static void main(String[] args) {
        int[][][] times = {
            { {2, 1, 1}, {3, 2, 1}, {3, 4, 2} },
            { {2, 1, 1}, {1, 3, 1}, {3, 4, 2}, {5, 4, 2} },
            { {1, 2, 1}, {2, 3, 1}, {3, 4, 1} },
            { {1, 2, 1}, {2, 3, 1}, {3, 5, 2} },
            { {1, 2, 2} }
        };

        int[] n = {4, 5, 4, 5, 2};
        int[] k = {3, 1, 1, 1, 2};

        for (int i = 0; i < times.length; i++) {
            System.out.println((i + 1) + ".\t times = " + Arrays.deepToString(times[i]));
            System.out.println("\t number of nodes 'n' = " + n[i]);
            System.out.println("\t starting node 'k' = " + k[i] + "\n");
            System.out.println("\t Minimum amount of time required = " + networkDelayTime(times[i], n[i], k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}