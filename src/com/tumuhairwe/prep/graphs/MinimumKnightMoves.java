package com.tumuhairwe.prep.graphs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 1197 (medium)
 *
 * In an infinite chess board with coords from -Inf to +Inf, you have a knight at square [0,0]
 * A knight has 8 possible moves it can make. Each move is 2 square3s in a
 * cardinal direction, then 1 square in an orthogonal direction
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].
 * It is guaranteed that the answer exists
 *
 * ref: https://leetcode.com/problems/minimum-knight-moves/description/
 */
public class MinimumKnightMoves {
    public int minKnightMoves(int x, int y) {
        int[][] offsets = {
                {1, 2}, {2, 1}, {2, -1}, {1, -2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}
        };

        //0. create and seed que with origin
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, 0});

        //1. create and seed visited set
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        //3. do dfs
        int numSteps = 0;
        while (!que.isEmpty()){
        int qDepth = que.size();

        while (qDepth-- > 0){
                // poll from queue
                int[] curr = que.poll();
                int row = curr[0];
                int col = curr[1];

                // check if we've reached destination
                if(curr[0] == x && curr[1] == y){
                    return numSteps;
                }

                //visit neighbors
                for (int[] dir : offsets){
                    int newX = row + dir[0];
                    int newY = col + dir[1];

                    String s = newX + "," + newY;
                    if(newX >= -1 && newY >= -1 && !visited.contains(s)){
                        visited.add(s);
                        que.add(new int[]{newX, newY});
                    }
                }
            }
        }

        return -1;
    }
}
