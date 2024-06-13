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
 *
 * ref: https://www.youtube.com/watch?v=EaphyqKU4PQ
 */
class NetworkDelayTime {
    static class Node{
        int nodeId;
        int time;
        public Node(int id, int weight){
            this.nodeId = id;
            this.time = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "nodeId=" + nodeId +
                    ", time=" + time +
                    '}';
        }
    }

    //Dijkstra TC: O(E log_V) == where E = number of edges, v = vertices
    public static int networkDelayTime(int[][] times, int numberOfNodes, int k) {
        //0. create adjacency list
        Map<Integer, Set<Node>> adjList = new HashMap<>();
        for(int i=0; i<times.length; i++){
            int source = times[i][0];   // u
            int targetNode = times[i][1];   // v
            int timeTaken = times[i][2];    //time

            Set<Node> existingNodes = adjList.getOrDefault(source, new HashSet<>());

            Node node = new Node(targetNode, timeTaken);
            existingNodes.add(node);
            adjList.put(source, existingNodes);
        }

        //1 create pq
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.time));

        // 1.1 seed pq
        Node startingNode = new Node(k, 0);
        pq.offer(startingNode);     // will sort Node by time to get to that node

        // 2.1 track visited
        Set<Integer> visited = new HashSet<>();

        //2.2 initialize minimumTimeTaken
        int minimumTimeTaken = 0;

        // 3. loop over pq and calculate minimumTimeTaken
        while(!pq.isEmpty()){
            Node currentNode = pq.poll();

            // 3.1 mark as visited
            if(visited.contains(currentNode.nodeId)){
                continue;
            }
            visited.add(currentNode.nodeId);

            // 3.4 update time
            minimumTimeTaken = Math.max(minimumTimeTaken, currentNode.time);

            // 3.2 get neighbors
            Set<Node> neighbors = adjList.getOrDefault(currentNode.nodeId, new HashSet<>());

            // 3.3 for each neighbor calc distance
            for(Node neighbor : neighbors){
                if(!visited.contains(neighbor.nodeId)){
                    int newTime = currentNode.time + neighbor.time;
                    pq.add(new Node(neighbor.nodeId, newTime));
                }
            }
        }

        if(numberOfNodes == visited.size()){
            return minimumTimeTaken;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][][] times = {
            { {2, 1, 1}, {2, 3, 1}, {3, 4, 1} },
            { {2, 1, 1}, {1, 3, 1}, {3, 4, 2}, {5, 4, 2} },
            { {1, 2, 1}, {2, 3, 1}, {3, 4, 1} },
            { {1, 2, 1}, {2, 3, 1}, {3, 5, 2} },
            { {1, 2, 2} }
        };

        int[] n = {4, 5, 4, 5, 2};
        int[] k = {2, 1, 2, 1, 2};

        for (int i = 0; i < times.length; i++) {
            System.out.println((i + 1) + ".\t times = " + Arrays.deepToString(times[i]));
            System.out.println("\t number of nodes 'n' = " + n[i]);
            System.out.println("\t starting node 'k' = " + k[i] + "\n");
            System.out.println("\t Minimum amount of time required = " + networkDelayTime(times[i], n[i], k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}