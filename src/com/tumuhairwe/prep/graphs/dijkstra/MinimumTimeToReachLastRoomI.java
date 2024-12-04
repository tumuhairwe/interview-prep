package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 3341 (dijkstra::medium()
 *
 * There is a dungeon with n x m rooms arranged in a grid.
 * You're given 2D array moveTime of size n x m where moveTime[i][j] represents
 * the minimum time in seconds when you can start moving to that room.
 * You can start from room origin (0,0) at time=0, and can move to an adjacent room
 * Moving between adjacent rooms takes 1 second
 *
 * Return the minimum time to reach (n-1, m-1)
 * 2 rooms are adjacent if they share a common wall either vertically or horizontally
 *
 * ref: https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/description/
 * ref: https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/solutions/6000225/easy-clear-solution-java-c-python3/
 */
public class MinimumTimeToReachLastRoomI {
    public class Entry implements Comparable<Entry>{
        int row;
        int col;
        int time;
        public Entry(int r, int c, int t){
            this.row = r;
            this.col = c;
            this.time = t;
        }

        @Override
        public int compareTo(Entry o) {
            return Integer.compare(this.time, o.time);
        }
    }

    /**
     * Solution summary
     * - Create Comparable class that orders by time (fields: time, row, col)
     * - Create & seed minHeap with origin
     * - create & seed 2D array of time/cost with MAX_VALUE. Update origin's cost to be 0
     * - do DFS
     *      - if we've arrived at target/destination -> return node.time
     *      - visit all 4 neighbors
     *      - calculate waitTime & newTime
     *      - if newTime is cheaper, add to minHeap and update 2D array of cost
     */
    public int minTimeToReach(int[][] moveTime){
        int rows = moveTime.length;
        int cols = moveTime[0].length;

        // create minHeap
        PriorityQueue<Entry> minHeap = new PriorityQueue<>();
        minHeap.add(new Entry(0, 0, 0));

        //2. create & seed 2D array to measure time/cost of each cell
        int[][] time = new int[rows][cols];
        Arrays.stream(time).forEach(row -> {
            Arrays.fill(row, Integer.MAX_VALUE);
        });
        time[0][0] = 0;

        // do DFS
        while (!minHeap.isEmpty()){
            Entry curr = minHeap.poll();

            // if we're at target
            if(curr.row == rows - 1 && curr.col == cols - 1){
                return curr.time;
            }

            // check neighbors
            int[][] offsets = {
                    {1, 0}, {0, 1},
                    {-1, 0}, {0, -1}
            };
            for (int[] dir : offsets){
                int newX = curr.row + dir[0];
                int newY = curr.col + dir[1];

                if(newX < 0 || newX >= rows || newY < 0 || newY >= cols){
                    continue;
                }

                // calc wait time + newTime
                int waitTime = Math.max(moveTime[newX][newY] - curr.time, 0);
                int newTime = curr.time + waitTime + 1;

                // update grid && add to pq if its less
                if(newTime < time[newX][newY]){
                    time[newX][newY] = newTime;
                    minHeap.offer(new Entry(newX, newY, newTime));
                }
            }
        }
        return -1;
    }
}
