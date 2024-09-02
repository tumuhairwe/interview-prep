package com.tumuhairwe.prep.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 286
 * You are given m by n grid (rooms) initialized with 3 possible values
 * -1 = WALL or obstacle
 * -0 = GATE
 * -INF = Integer.MAX_VALUE
 * ref: https://www.youtube.com/watch?v=e69C6xhiSQE
 */
public class WallsAndGates {
    static int WALL = -1;
    static int GATE = 0;
//
//    static class Room{
//        int row;
//        int col;
//        int dist;
//
//        public Room(int r, int c, int d){
//            this.row = r;
//            this.col = c;
//            this.dist = d;
//        }
//    }

    public static void main(String[] args) {
        int[][] rooms = new int[][]{
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}
        };
        wallsAndGates(rooms);
        Arrays.stream(rooms).forEach(r -> System.out.println(Arrays.toString(r)));
    }

    /**
     * Solution summary (DFS)
     * - traverse 2D array and collect all GATES into queue
     * - while !que.isEmpty() ... for each in-bounds neighbor ... if value
     *      == INFinity, add to queue
     *      ELSE - set value = rooms[row][col] + 1;
     */
    public static void wallsAndGates(int[][] rooms) {
        Queue<int[]> que = new LinkedList<>();

        // compile list of rooms
        for(int i=0; i<rooms.length; i++){
            for(int j=0; j< rooms[0].length; j++){
                if(rooms[i][j] != WALL && rooms[i][j] == GATE){
                    que.add(new int[]{i, j});
                }
            }
        }

        //
        while(!que.isEmpty()){
            int[] room = que.poll();
            int row = room[0];
            int col = room[1];

            int[][] offsets = {
                    {0, 1}, {1, 0},
                    {-1, 0}, {0, -1}
            };
            for(int[] direction : offsets){
                int x = row + direction[0];
                int y = col + direction[1];

                boolean isRowInBounds = x >= 0 && x < rooms.length;
                boolean isColInBounds = y >= 0 && y < rooms[0].length;

                if(isRowInBounds && isColInBounds && rooms[x][y] == Integer.MAX_VALUE){
                    rooms[x][y] = rooms[row][col] + 1;
                    que.add(new int[]{x, y});
                }
            }
        }
    }
}
