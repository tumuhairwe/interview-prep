package com.tumuhairwe.prep.graphs;



import java.util.*;

/**
 * Solution summary
 * - Definition: An algorithm to find the shortest path
 * - Steps
 *  - Create adjacency-list of all nodes (Map: key=nodeId, value=[ {neighborNodeId}, {weight} ]
 *  - Create minHeap/PQ ordered by weight
 *  - Seed minHeap with sourceId + 0 distance
 *  - Populate minHeap (while iterating & getting destinations from adjList.get(sourceNodeId) until all desitnation are exhausted
 *  - Return adjList Map { key=nodeId, value = weight-to-destiation }
 */
public class Dijkstra {

    public static Map<Integer,Integer> getShortestPath(int[][] edges, int numberOfNodes, int src){
        // 0. initialized Adjacency list
        Map<Integer, List<Integer[]>> adjacencyList = new HashMap<>();
        for (int nodeId = 0; nodeId < numberOfNodes; nodeId++) {
            adjacencyList.put(nodeId, new ArrayList<>());
        }

        // 1. put all edges in adjList
        for (int[] edge : edges) {
            // s = src, d = dist, w = weight
            int source = edge[0], neighborNodeId = edge[1], weight = edge[3];
            adjacencyList.get(source).add(new Integer[]{neighborNodeId, weight});
        }

        // this adj list will contain all paths originating from all nodes in the edge
        Map<Integer, Integer> shortestPath = new HashMap<>();

        // 2. create PQ /minHeap that will sort by weight
        //Comparator<Pair> p = Comparator.comparingInt((Pair p2) -> p2.distance);
        Comparator<int[]> p = Comparator.comparingInt((int[] p2) -> p2[0]);
        Queue<int[]> minHeap = new PriorityQueue<>(p);
        minHeap.add(new int[]{0, src});

        // 3. iterate over min heap and poll() the top
        while (!minHeap.isEmpty()){
            int[] curr = minHeap.poll();    // cost opposing from a minHeap/pq = O(log V) -- where V is the number of vertices in the graph
            int currentWeight = curr[0];
            int currentNodeId = curr[1];

            if(shortestPath.containsKey(currentNodeId)){
                continue;
            }

            //4.  put currentNodeId + weight in adj list
            shortestPath.put(currentNodeId, currentWeight);

            // 5. process the neighbor's destination's by pushing them to the minHeap
            List<Integer[]> destinations = adjacencyList.get(currentNodeId);
            for(Integer[] dest : destinations){
                int neighborNodeId = dest[0];
                int neighborWeight = dest[1];

                // if node has not already been visited/processed
                if(!shortestPath.containsKey(neighborNodeId)){
                    // min heap will sort by weight
                    minHeap.add(new int[]{      // O( log_E) -- where E = # of edges)
                            currentWeight + neighborWeight, // cost to reach neighbor_nodeId
                            currentNodeId
                    });
                }
            }
        }

        // 5 return adjList (showing all nodes accessible from src + their cost)
        return shortestPath;
    }
}
