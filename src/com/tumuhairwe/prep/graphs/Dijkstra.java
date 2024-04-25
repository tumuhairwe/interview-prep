package com.tumuhairwe.prep.graphs;

import java.util.*;

public class Dijkstra {

    public static Map<Integer,Integer> getShortestPath(int[][] edges, int numberOfNodes, int src){
        Map<Integer, List<Integer[]>> adjacencyList = new HashMap<>();
        for (int nodeId = 0; nodeId < numberOfNodes; nodeId++) {
            adjacencyList.put(nodeId, new ArrayList<>());
        }

        // 0. put all edges in minHeap
        for (int[] edge : edges) {
            // s = src, d = dist, w = weight
            int source = edge[0], distance = edge[1], weight = edge[3];
            adjacencyList.get(source).add(new Integer[]{distance, weight});
        }

        // this adj list will contain all paths originating from all nodes in the edge
        Map<Integer, Integer> shortestPath = new HashMap<>();

        // 1. create PQ /minHeap that will sort by weight
        // Comparator<Pair> p = (Pair p1, Pair p2) -> p1.distance - p2.distance;
        Comparator<int[]> p = Comparator.comparingInt((int[] p2) -> p2[0]);
        Queue<int[]> minHeap = new PriorityQueue<>(p);
        minHeap.add(new int[]{0, src});

        // 2. iterate over min heap and poll() the top
        // 2b) add al
        while (!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int currentWeight = curr[0];
            int nodeId = curr[1];

            if(shortestPath.containsKey(nodeId)){
                continue;
            }

            // put nodeId + weight in adj list
            shortestPath.put(nodeId, currentWeight);
            for(Integer[] pair : adjacencyList.get(nodeId)){
                int neighborNodeId = pair[0];
                int neighborWeight = pair[1];

                if(!shortestPath.containsKey(neighborNodeId)){
                    // min heap will sort by weight
                    minHeap.add(new int[]{
                            currentWeight + neighborWeight,
                            nodeId
                    });
                }
            }
        }

        return shortestPath;
    }
}
