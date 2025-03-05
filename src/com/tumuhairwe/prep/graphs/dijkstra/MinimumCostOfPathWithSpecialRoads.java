package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.*;

/**
 * LeetCoded 2662 (medium)
 * -Given an array start where start = [startRow, startCol] represents your initial position (startX and startY) in a 2D space
 * - Given an array target where target = [targetRow, targetCol] represents you target position (targetX and targetY)
 * - The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|
 *
 * - There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, cost_i]
 * indicates that the ith special road goes in one direction from (x1i, y1i) to (x2i, y2i) with a cost equal to cost_i.
 * You can use each special road any number of times.
 *
 * Return the minimum cost required to go from (startX, startY) to (targetX, targetY).
 *
 * ref: https://leetcode.com/problems/minimum-cost-of-a-path-with-special-roads/description/?envType=problem-list-v2&envId=shortest-path
 */
public class MinimumCostOfPathWithSpecialRoads {
    /**
     * TC: O(n^2 * log(n^2))
     * SC: O(n^2)
     * ref: https://leetcode.com/problems/minimum-cost-of-a-path-with-special-roads/solutions/3479468/java-dijkstra-s-algorithm-clean-code-15-lines/?envType=problem-list-v2&envId=shortest-path
     */
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        //0 declare vars
        int startRow = start[01];
        int startCol = start[1];
        int targetRow = target[0];
        int targetCol = target[1];

        //1. create pq (same)
        //Comparator<int[]> comp = (int[] a, int[] b) -> Integer.compare(a[2], b[2]);
        Comparator<int[]> comp = Comparator.comparingInt((int[] a) -> a[2]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(comp);
        minHeap.offer(new int[]{startRow, startCol, 0});

        //2. create visited set (DON'T SEED)
        boolean[][] visited = new boolean[specialRoads.length][specialRoads[0].length];

        //3. traverse space
        while (!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int currRow = curr[0];
            int currCol = curr[1];
            int currCost = curr[2];

            //4. check if visited
            if(visited[currRow][currCol]){
                continue;
            }

            //5. check if at destination
            if(currRow == targetRow && currCol == targetCol){
                return currCost;
            }

            //6. add road from curr direct to target
            int costToTarget = currCost + Math.abs(targetRow - currRow) + Math.abs(targetCol - currCol);
            minHeap.offer(new int[]{targetRow, targetCol, costToTarget});

            //7. add to road from curr to each special road
            for (int[] road : specialRoads){
                int srcRow = road[0];
                int srcCol = road[1];
                int destRow = road[2];
                int destCol = road[3];
                int roadCost = road[4];

                if(!visited[destRow][destCol]){
                    int costToDest = Math.abs(srcRow - currRow) + Math.abs(srcCol - currCol) + currCost + roadCost;
                    minHeap.offer(new int[]{destRow, destCol, costToDest});
                }
            }
        }

        return -1;
    }
}
