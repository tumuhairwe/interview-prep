package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode 1584 (dijkstra::medium)
 * Given an array of points of some points on a 2D plane where
 * points[i] = [x, y]
 *
 * The distance of connecting 2 points [x_1, y_1] and [x_2, y_2] is the manhattan distance between them
 * i.e. |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Get the minimum distance to make all points connected if there's exactly one simple path
 * between any 2 points
 *
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/1584-min-cost-to-connect-all-points.java
 * ref: https://leetcode.com/problems/min-cost-to-connect-all-points/description/
 * ref: https://www.youtube.com/watch?v=8VPIrqwQ8sQ&t=11s
 */
public class MinimumCostToConnectAllPoints {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0,0},{2,2},{3,10},{5,2},{7,0}
        };

        int result = minCostConnectPoints(arr);
        System.out.println("The minimum distance to connect the whole panning tree is " + result);

        arr = new int[][]{
                {3,12},{-2,5},{-4,1}
        };
        result = minCostConnectPoints(arr);
        System.out.println("The minimum distance to connect the whole panning tree is " + result);

        arr = new int[][]{
                {3,12},{-2,5},{-4,1}
        };
    }

    // this Prim's MST algo
    static int minCostConnectPoints(int[][] points){
        // 1. create pq ordered by distance
        Comparator<int[]> comp = Comparator.comparingInt(a -> a[0]);    // entry in pq will be [distance, nodeId]
        PriorityQueue<int[]> pq = new PriorityQueue<>(comp);

        // 2. seed pq with 0,0 as [distance, nodeId]
        int initialDistance = 0;
        int initialIndex = 0;
        int[] origin = new int[]{initialDistance, initialIndex};
        pq.add(origin);

        // 3. create visited set of nodeId
        Set<Integer> visited = new HashSet<>();
        int totalCost = 0;

        // When visited.size() == points.length meaning that all the nodes has been connected.
        while (visited.size() < points.length){
            int[] currentNode = pq.poll();
            int distance = currentNode[0];
            int currentNodeId = currentNode[1];

            if(visited.contains(currentNodeId)){
                continue;
            }

            visited.add(currentNodeId);
            totalCost += distance;

            int[] actualCoordinate = points[currentNodeId];

            // for each point that is not yet seen
            for(int nextNodeId=0; nextNodeId<points.length; nextNodeId++){
                if(visited.contains(nextNodeId)){
                    continue;
                }

                int[] neighborNode = points[nextNodeId];
                int distanceToNeighbor = Math.abs(actualCoordinate[0] - neighborNode[0]) +
                        Math.abs(actualCoordinate[1] - neighborNode[1]);
                pq.add(new int[]{distanceToNeighbor, nextNodeId});
            }
        }

        return totalCost;
        //TC: O(n^2 log(n)  --- where n == # of points & n^2 == max-number of edges in the graph
        // aka
        // TC: O(E log(E)) bcoz we're putting and popping the E edges from the heap
        // SC: O(n^2) -- to store the edges on the heap (log_n ) is for pushing and popping
    }
}
