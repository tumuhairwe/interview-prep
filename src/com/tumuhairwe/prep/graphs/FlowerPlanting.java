package com.tumuhairwe.prep.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You're given n gardens labeled from 1 to n, and an array paths where path[i = [x, y] describes a bidirectional path between
 * garden_x and garden_y, in each garden, you want to plant 1 of 4 types of flowers.
 *
 * All gardens have at most 3 paths coming into or leaving
 *
 * Your task is to choose a flower type for each garden such that for any 2 gardens connected by a path, they have different types of flowers.
 *
 * Return any such choice as an array ans where ans[i] is the type of flower planted in the (i+1)th garden. The flower types are denoted 1, 2, 3 or 4.
 * It is guaranteed an answer exists
 */
public class FlowerPlanting {
    public int[] gardenNoAdj(int n, int[][] paths) {
        //0. create adjList
        Map<Integer, Set<Integer>> adjList = new HashMap<>();

        //1. init adjList
        for (int i = 0; i < n; i++) {
            adjList.put(i, new HashSet<>());
        }

        //2. populate adjList
        for(int[] path : paths){
            int from = path[0] - 1;
            int to = path[1] - 1;

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        //3.
        int[] answer = new int[n];

        //4. traverse gardens
        for (int garden = 0; garden < n; garden++) {
            int[] colors = new int[5];  // use 1-based indexing

            //5. traverse graph and populate colors[]
            for(Integer flower : adjList.get(garden)){
                int currentColor = answer[flower];
                colors[currentColor] = 1;
            }

            // 6. find a color that has NOT been used -> add to answer
            for (int c = 0; c < 4; c++) {
                if (colors[c] != 1){    // if NOT taken/seen
                    answer[c] = 1;      // add to answer
                }
            }
        }

        return answer;
    }
}
