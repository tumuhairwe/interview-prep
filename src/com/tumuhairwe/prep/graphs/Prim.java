package com.tumuhairwe.prep.graphs;

import java.util.*;

public class Prim {

    // implementation for Prim's algorithm to compute the minimum spanning trees
    public int minimumSpanningTree(List<List<Integer>> edges, int n){
        // 0. build adjacent list
        Map<Integer, List<Integer[]>> adjList = new HashMap<>();
        for (int i = 1; i < n ; i++) {
            adjList.put(i, new ArrayList<>());
        }

        // 1. populate adj list with edges
        for (List<Integer> edge : edges){
            int neighbor1 = edge.get(0);
            int neighbor2  = edge.get(1);
            int weight = edge.get(2);

            adjList.putIfAbsent(neighbor1, new ArrayList<>());
            adjList.putIfAbsent(neighbor2, new ArrayList<>());

            adjList.get(neighbor1).add(new Integer[]{neighbor2, weight});
            adjList.get(neighbor2).add(new Integer[]{neighbor1, weight});
        }

        // 2. initialize minHeap with all edges connected to node 0 (base/initializing nodeIds)
        // value = [weight, nodeId, neighbor]
        Comparator<int[]> comp = Comparator.comparingInt((n1) -> n1[0]);
        Queue<int[]> minHeap = new PriorityQueue<>(comp);
        for (Integer[] neighbor : adjList.get(0)){
            int node = neighbor[0];
            int weight = neighbor[1];
            minHeap.add(new int[]{weight, 0, node});
        }

        // 3. compute the minimum spanning tree
        int totalWeight = 0;
        Set<Integer> visit = new HashSet<>();
        visit.add(0);
        while (visit.size() < n && !minHeap.isEmpty()){
            int[] curr = minHeap.remove();
            int weight = curr[0];
            int nodeId = curr[1];
            int neighbor = curr[2];

            if (visit.contains(neighbor)){
                continue;
            }

            totalWeight += weight;
            visit.add(neighbor);
            for (Integer[] pair : adjList.get(neighbor)){
                Integer nextDoorNeighbor = pair[0];
                Integer nextDoorWeight = pair[0];
                if(!visit.contains(nextDoorNeighbor)){
                    minHeap.add(new int[]{nextDoorWeight, nextDoorNeighbor});
                }
            }
        }

        // 4 return -1 if not all nodes have been visited
        return visit.size() == n ? totalWeight : -1;
    }
}
