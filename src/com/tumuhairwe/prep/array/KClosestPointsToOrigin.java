package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * Given an array of points where a point[i] = [x, y]
 * represents a point on the X-Y plan and integer K,
 * return the K closest points to the origin (0,0)
 *
 * The distance between 2 points on the x & y plan is the euclidean distance
 * √(x1 - x2)2 + (y1 - y2)2).
 *
 * Example:
 * points = { [1,3], [-2,2]}, k = 1
 * output = [[-2, 3]]
 *
 * LeetCode 938 (medium)
 * You may return the answer in any order. The answer is guaranteed to be unique
 * ref: https://leetcode.com/problems/k-closest-points-to-origin/description/
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3} ,{-2,2}};
        int[][] result = new int[][]{{-2,2}};
        int k = 1;

        int[][] res = kClosest(arr, k);
        System.out.println("The top k are " + List.of(arr));
        System.out.println("Match == " + Arrays.equals(res, result));;
    }

    static int[][] kClosest(int[][] points, int k){
        Comparator<int[]> comparator = (int[] a, int[] b) -> {
            int x = a[0] - a[1];
            int xSquared = x * x;

            int y = b[0] - b[1];
            int ySquared = y * y;
            double sqRoot = Math.sqrt(xSquared + ySquared);
            return Double.valueOf(sqRoot).intValue();

//            int aZeroSquared = a[0] * a[0];
//            int aOneSquared = a[0] - a[1];
//
//            int bZeroSquared = b[0] * b[0];
//            int bOneSquared = b[1] * b[1];
//            return (aZeroSquared + aOneSquared) + (bZeroSquared + bOneSquared);
        };
        // 0. create PQ
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);

        // 1. populate pq
        for (int[] point : points){
            pq.add(point);
        }

        // populate top k
        int [][] output = new int[k][2];
        int count = 0;
        while (k-- > 0){
            output[count++] = pq.poll();
        }

        return output;
    }
}
