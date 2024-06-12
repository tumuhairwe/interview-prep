package com.tumuhairwe.prep.intervals;

import java.util.*;

/**
 * A maze consists of n rooms, and some are connected by corridors.
 * Given a 2D array corridors, where corridors[i] = [room_1 and room_2] & vice versa
 *
 * Detect a confusion score (a maze that has a cycle of length 3)
 * e.g. 1 -> 2 -> 3-> == has a cycle of length 3
 * e.g. 1 -> 2 -> 3 -> 4 == has no cycle
 * e.g. 1 -> 2 -> 3 -> 2 -> 1 == has a cycle of length 2
 *
 * ref: https://leetcode.com/problems/paths-in-maze-that-lead-to-same-room/description/
 */
public class PathsInMaze {

    static int numberOfPaths(int n, int[][] corridors){
        Map<Integer, Set<Integer>> adjacencyMatrix = new HashMap<>();

        // 0. create adjList
        for (int[] path : corridors) {
            int src = path[0];
            int dest = path[1];

            adjacencyMatrix.computeIfAbsent(Integer.min(src, dest), k -> new HashSet<>())
                    .add(Integer.max(src, dest));
        }

        //1. find intersections between nodes's lists
        int cycles = 0;
        for (int[] path : corridors){
            int src = path[0];
            int dest = path[1];

            Set<Integer> s1 = adjacencyMatrix.get(src);
            Set<Integer> s2 = adjacencyMatrix.get(dest);

            // 2. if both src/ and dest have a list ... count size of intersection -> add to cycles
            if(s1 != null && s2 != null){
                cycles += s1.stream().filter(s2::contains).count();
            }
        }
        return cycles;
    }
}
