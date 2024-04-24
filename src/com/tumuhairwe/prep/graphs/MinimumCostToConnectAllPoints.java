package com.tumuhairwe.prep.graphs;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode 1584 (medium)
 * Given an array of points of some points on a 2D plane where
 * points[i] = [x, y]
 *
 * The cost of connecting 2 points [x_i, y] and [x_j, y] is the manhattan distance between them
 * i.e. |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Get the minimum cost to make all points connected if there's exactly one simple path
 * between any 2 points
 */
public class MinimumCostToConnectAllPoints {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0,0},{2,2},{3,10},{5,2},{7,0}
        };

        int result = minCostConnectPoints(arr);
        System.out.println("The minimum cost to connect the whole panning tree is " + result);

        arr = new int[][]{
                {3,12},{-2,5},{-4,1}
        };
        result = minCostConnectPoints(arr);
        System.out.println("The minimum cost to connect the whole panning tree is " + result);

        arr = new int[][]{
                {3,12},{-2,5},{-4,1}
        };
    }

    static int minCostConnectPoints(int[][] points){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});
        int  length = points.length;
        Set<Integer> visited = new HashSet<>();
        int cost = 0;

        // When visited.size() == points.len meaning that all the nodes has been connected.
        while (visited.size() < length){
            int[] arr = pq.poll();;

            int weight = arr[0];
            int currNode = arr[1];

            if(visited.contains(currNode)){
                continue;
            }

            visited.add(currNode);
            cost += weight;

            for (int nextNode = 0; nextNode < length; nextNode++){
                if(!visited.contains(nextNode)){
                    int nextWeight = Math.abs(points[nextNode][0] - points[currNode][0])
                                   + Math.abs(points[nextNode][1] - points[currNode][1]);
                    pq.offer(new int[]{nextWeight, nextNode});
                }
            }
        }

        return cost;
    }
}
