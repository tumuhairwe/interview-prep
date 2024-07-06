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

        int[][] res = kClosest_justSort(arr, k);
        System.out.println("The top k are " + Arrays.toString(arr));
        System.out.println("Match == " + Arrays.equals(res, result));;
    }

    /**
     * Solution sort
     * - Implement a comparator that uses the formulae
     * - just sort the points with that comparator
     * - return a copy of that array from O to K
     */
    static int[][] kClosest_justSort(int[][] points, int k){
        Comparator<int[]> comp = Comparator.comparingInt(point -> (point[0] * point[0]) + (point[1] * point[1]));
        Arrays.sort(points, comp);

        return Arrays.copyOfRange(points, 0, k);
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
    static int[][] kClosest_usePQ(int[][] points, int k){
        // 0. create PQ
        PriorityQueue<Point> pq = new PriorityQueue<>();

        // 1. populate pq
        for (int[] point : points){
            while (pq.size() > k)

            //remove when size increase k
            if (pq.size() > k) {
                pq.poll();
            }

            int distance = calcDistance(point);
        }

        // populate top k array from pq;
        int [][] output = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point p = pq.poll();
            output[i] = new int[]{p.row, p.col};
        }

        return output;
    }
    static int calcDistance(int[] point){
        return (point[0] * point[0]) + (point[1] * point[1]);
    }
    class Point implements Comparable<Point>{
        int row;
        int col;
        int distance;
        public Point(int r, int c, int d){
            this.row = r;
            this.col = c;
            this.distance = d;
        }

        public int compareTo(Point p){
            return Integer.compare(this.distance, p.distance);  // ascending order
        }
    }
}
