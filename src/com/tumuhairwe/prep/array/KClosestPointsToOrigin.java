package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 938 (medium)
 *
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
 * You may return the answer in any order. The answer is guaranteed to be unique
 * ref: https://leetcode.com/problems/k-closest-points-to-origin/description/
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3} ,{-2,2}};
        int[][] result = new int[][]{{-2,2}};
        int k = 1;

        int[][] res = kClosest(arr, k);
        System.out.println("The top k are " + Arrays.toString(arr));
        System.out.println("Match == " + Arrays.equals(res, result));;
    }

    /**
     * Solution Summary
     * - Create a comparator that will order the 2 arrays according to the function
     * - Create pq using above comparator
     * - loop over 2D array and populate pq with arrays
     * - pq.pop() off the pq k times to get the top k entries
     *
     * TC O(NlogK) -- if we don't have store all N points in PQ
     * TC = O(NlogN) = if we have to store all N points in PQ before popping off top x
     */
    static int[][] kClosest(int[][] points, int k){
        Comparator<int[]> comparator = (int[] a, int[] b) -> {
            int aZeroSquared = a[0] * a[0];
            int aOneSquared = a[1] * a[1];

            int bZeroSquared = b[0] * b[0];
            int bOneSquared = b[1] * b[1];
            int a_sum = aOneSquared + aZeroSquared;
            int b_sum = bZeroSquared + bOneSquared;
            return Integer.compare(a_sum, b_sum);
        };
        // 0. create PQ
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);

        // 1. populate pq
        for (int[] point : points){
            pq.add(point);

            //remove when size increase k
            if (pq.size() > k) {
                pq.remove();
            }
        }

        // populate top k array from pq;
        int [][] output = new int[k][2];
        int count = 0;
        while (k-- > 0){
            output[count++] = pq.poll();
        }

        return output;
    }
}
