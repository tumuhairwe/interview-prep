package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 1976 (medium)
 * You are in a city that consists of n intersections (from 0 to n-1)
 * with bi-directional roads between some intersections. The inputs are generated such that you can reach
 * any intersection from any other intersection and there ais at most 1 road between any 2 interesections
 *
 * You are given an integer n and 2D integer array roads where roads[i = [u, v, time]
 * means there is a road between intersections u and v that takes time minutes to travel. You want to know
 * how many ways you can travel from intersection 0 to n-1 in the shortest amoung of time
 *
 * Return the number of ways you can arrive at your destination in the shortest time. Return answer modulo (10^9 + 7)
 */
public class NumberOfWaysToReachDestination {
    class Pair{
        int next;   // nextNode;
        long distance;
        public Pair(int next, long dist){
            this.next = next;
            this.distance = dist;
        }
    }
    public int countPaths(int n, int[][] roads) {
        //0. create adjList with bidirectional paths
        Map<Integer, List<Pair>> adjList = new HashMap<>();
        for (int i = 0; i < roads.length; i++) {
            int source = roads[i][0];
            int dest = roads[i][1];
            int time = roads[i][2];

            adjList.put(source, new ArrayList<>());
            adjList.get(source).add(new Pair(dest, time));

            adjList.put(dest, new ArrayList<>());
            adjList.get(dest).add(new Pair(source, time));
        }

        //1. init arrays for distance and ways
        long[] dist = new long[n];
        long[] ways = new long[n];
        long mod = (int)Math.pow(10, 9) + 7;

        //2. initialize distance and ways arrays
        for (int i = 0; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
            ways[i] = 0;
        }

        //4. seed starting node with 0 distance && 1 way to reach it
        dist[0] = 0;
        ways[0] = 1;

        //5. create && seed pq for dijkstra
        // Comparator<Pair> comp = (Pair p1, Pair p2) -> Long.compare(p1.distance, p2.distance);
        Comparator<Pair> comp = Comparator.comparingLong((Pair p) -> p.distance);
        PriorityQueue<Pair> pq = new PriorityQueue<>(comp);

        pq.add(new Pair(0, 0));

        //6. traverse pq & populate ways[]
        while (!pq.isEmpty()){
            Pair curr = pq.poll();

            // explore neighbors
            if(!adjList.containsKey(curr.next)){
                continue;
            }
            for (Pair neighbor : adjList.get(curr.next)){
                // if shorter path is found -> update dist[]
                long newDist = curr.distance + neighbor.distance;
                if(newDist < dist[neighbor.next]){
                    // update both dist && ways[]
                    dist[neighbor.next] = newDist;
                    ways[neighbor.next] = ways[curr.next];

                    pq.add(new Pair(neighbor.next, newDist));
                }
                else if(newDist == dist[neighbor.next]){
                    // if multiple ways with the same distance are found ... add their ways
                    ways[neighbor.next] = ( ways[neighbor.next] + ways[curr.next] ) % mod;
                }
            }
        }

        //return number of ways to reach (last node & mod)
        return (int)(ways[n - 1] % mod);
    }
}
