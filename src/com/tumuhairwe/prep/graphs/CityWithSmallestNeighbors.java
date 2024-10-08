package com.tumuhairwe.prep.graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCod  1334 (medium)
 * There are n cities numbered from 0 to n-1.
 * Given the array edges where edges[i] [_from, _to, weight] represents a bidirectional
 * and weighed edge between cities _from and _to and given the integer _distanceThreshold
 *
 * Return the city with the smallest number of cities that are reachable thru some path
 * and whose distance is at not _distanceThreshold, if there are multiple cities, return the city
 * with the greatest number
 *
 * Notice that the distance of a path connecting cities i and j is equal to the sum of the edges along that path
 *
 * ref: https://www.youtube.com/watch?v=--wKPR3ByJc
 * ref: https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
 */
public class CityWithSmallestNeighbors {
    class Node{
        int cityId;
        int distance;
        public Node(int id, int cost){
            this.cityId = id;
            this.distance = cost;
        }
    }

    /**
     * Solution summary
     * -
     * TC: Dijkstra: O((V + E) log_V) -- when using priority queue where V is the number of vertices and E is the number of edges
     * SC: O(E + v) where V = #_of_vertices and E = #_of_edges
     * ref: https://www.geeksforgeeks.org/time-and-space-complexity-of-dijkstras-algorithm/
     */
    public int findTheCity(int n_umberOfCities, int[][] edges, int distanceThreshold){
        //0. create adjList (bidirectional)
        Map<Integer, List<Node>> adjList = new HashMap<>();
        for (int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            adjList.putIfAbsent(to, new ArrayList<>());
            adjList.putIfAbsent(from, new ArrayList<>());

            adjList.get(from).add(new Node(to, weight));
            adjList.get(to).add(new Node(from, weight));
        }

        //2. iterate minHeap
        // a) while we haven't visited all nodes ... get-all-nodes-reachable from $source ... that are within distanceThreshold
        // b) find and collect all cities reachable with distanceThreshold
        //0. declare minCount of Integer.MAX_VALUE. and cityId = 0;
        int minCount = n_umberOfCities;   // max that an city can be connected to
        int cityId = 0;

        //1. for each city, getCount of neighbors accessible within threshold distance
        for (int nodeId = 0; nodeId < n_umberOfCities; nodeId++) {
            int count = getCountOfAccessibleNeighborsWithinDistance(nodeId, distanceThreshold, adjList);
            if(count <= minCount){
                minCount = count;
                cityId = nodeId;
            }
        }

        return cityId;
    }

    //dijkstra's == shortest path with weighted edges
    public int getCountOfAccessibleNeighborsWithinDistance(int nodeId, int distanceThreshold, Map<Integer, List<Node>> adjList){
        // create pq (cityId, distance) that stores nodes by weight
        Comparator<Node> comp = Comparator.comparingInt(p -> p.distance);
        PriorityQueue<Node> nodeCountPq = new PriorityQueue<>(comp.reversed());
        nodeCountPq.add(new Node(nodeId, 0));

        Set<Integer> visited = new HashSet<>();
        int numberOfNodes = 0;

        while (!nodeCountPq.isEmpty()) {
            Node current = nodeCountPq.poll();
            int cityId = current.cityId;
            int distance = current.distance;
            numberOfNodes++;

            if(visited.contains(cityId)){
                continue;
            }
            visited.add(cityId);

            if(!adjList.containsKey(cityId)){
                continue;
            }

            for(Node neighbor : adjList.get(cityId)){
                int newDistance = distance + neighbor.distance;

                if(!visited.contains(neighbor.cityId) && newDistance <= distanceThreshold){
                    nodeCountPq.add(new Node(neighbor.cityId, newDistance));
                }
            }
        }

        return numberOfNodes;
    }
}
