package com.tumuhairwe.prep.graphs.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 3342 (dijkstra::medium)
 * There is a dungeon with n x m rooms arranged in a grid
 * You're given a 2D array (moveTime) of size n x m where moveTime[i][j] represents the minimum time in seconds
 * when you can start moving to that room.
 * You start from the room (0,0) and time=0 and can move to an adjacent room. Moving between adjacent rooms takes 1 seconod
 * for the move and 2 seconds for the next move (alternating) between the 2
 *
 * Return the minimum time to reach the room (n-1, m-1)
 * 2 rooms are adjacent if they share a common wall either vertically or horizontally
 *
 * ref: https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/
 */
public class MinimumTimeToReachLastRoomII {

    class Entry implements Comparable<Entry>{
        int row;
        int col;
        int time;
        int secondsToAdd;
        public Entry(int r, int c, int t, int timeToAdd){
            this.row = r;
            this.col = c;
            this.time = t;
            this.secondsToAdd = timeToAdd;
        }

        @Override
        public int compareTo(Entry o) {
            return Integer.compare(this.time, o.time);
        }
    }

    /**
     * TC: O(n * m) / log(n * m) -- where n == # of rows & n == # of cols
     *      - because each cell (total of n * m) may be added tot he pq at most once
     *      - the offer() operation on the pq takes O(log k) time where k is the number of elements in the queue
     * SC: O(n x m) for storing the minTime array and the pq that may potentially hold all the cells
     * ref: https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/solutions/6000224/constest-3-11-2024-q-3-bfs-heap/
     */
    public int minTimeToReach(int[][] moveTime){
        // declare vars
        int rows = moveTime.length;
        int cols = moveTime[0].length;

        // declare pq & seed with origin
        PriorityQueue<Entry> minHeap = new PriorityQueue<>();
        minHeap.add(new Entry(0, 0, 0, 0)); // zero secondsToAdd is initial so next will be 1 second,

        //declare 2D array to track time
        int[][] time = new int[rows][cols];
        Arrays.stream(time).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        time[0][0] = 0;

        // do DFS
        while (!minHeap.isEmpty()){
            Entry curr = minHeap.poll();

            // check if destination
            if(curr.row == rows - 1 && curr.col == cols - 1){
                return curr.time;
            }

            // check neighbors
            int[][] offsets = {
                    {1, 0}, {0, 1},
                    {-1, 0}, {0, 1}
            };
            for (int[] dir : offsets){
                int r = curr.row + dir[0];
                int c = curr.col + dir[1];

                if(r < 0 || r >= rows || c < 0 || c >= cols){
                    continue;
                }

                int toAdd = curr.secondsToAdd == 1 ? 2 : 1;    // requirements: MUST BE ALTERNATING
                int newTime = Math.max(curr.time + toAdd, moveTime[r][c] + toAdd);
                if(newTime < moveTime[r][c]){
                    moveTime[r][r] = newTime;
                    minHeap.add(new Entry(r, c, newTime, toAdd));
                }
            }
        }
        return -1;
    }
}
