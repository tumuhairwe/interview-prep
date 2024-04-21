package com.tumuhairwe.prep.map;

import java.util.*;

/**
 * Solution:
 * - Create an adjacency list to store the graph (node + edges_and_cost)
 * - Use PQ to store nodes and cost/delay-time. Initialize queue with the source + cost = 0
 * - Use a visited set to track the nodes that have already been processed
 * - Process nodes from the PQ by visiting the node with the smallest delay time & update if necessary
 * - Add the unvisited neighbors of the processed node to the PQ (with their new cost/delay times)
 * - Return the delay time of all the nodes that have been processed, otherwise -1
 *
 * TC: O(E log N) -- where E = total number of edges & N = number of nodes in the network
 * (since push() and pop() operations take O(log n ) time)
 *
 * SC: O(N + E) where N = number of nodes in graph and E = number of edges required by the adjacency matrix and PQ
 */
class NetworkDelayTime {
    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adjacency = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int destination = time[1];
            int travelTime = time[2];
            adjacency.computeIfAbsent(source, key -> new ArrayList<>()).add(new int[]{destination, travelTime});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});
        Set<Integer> visited = new HashSet<>();
        int delays = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int node = current[1];

            if (visited.contains(node))
                continue;

            visited.add(node);
            delays = Math.max(delays, time);
            List<int[]> neighbors = adjacency.getOrDefault(node, new ArrayList<>());

            for (int[] neighbor : neighbors) {
                int neighborNode = neighbor[0];
                int neighborTime = neighbor[1];
                if (!visited.contains(neighborNode)) {
                    int newTime = time + neighborTime;
                    pq.offer(new int[]{newTime, neighborNode});
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