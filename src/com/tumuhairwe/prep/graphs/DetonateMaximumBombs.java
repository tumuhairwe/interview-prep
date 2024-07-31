package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 2102 (medium)
 * You are given a list of bombs. The range of a bomb is defined as the area where
 * its effect can be felt. The area is in the aread is in the shape of a circle with
 * the center as the location of the bomb.
 *
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 *
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.
 *
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 */
public class DetonateMaximumBombs {

    public int maximumDetonation(int[][] bombs) {
        if(bombs.length == 0){
            return 0;
        }

        //0. create graph
        int gridLength = bombs.length;
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                int xi = bombs[i][0], yi = bombs[i][1], radius = bombs[i][2];
                int xj = bombs[j][0], yj = bombs[j][1];

                long blastCircumference = (long) radius * radius;
                long accessibleRange = (long)(xi - xj) * (xi - xj)
                        + (long)(yi - yj) * (yi - yj);
                boolean isInRange = blastCircumference >= accessibleRange;
                if(isInRange){
                    adjList.putIfAbsent(i, new ArrayList<>());
                    adjList.get(i).add(j);
                }
            }
        }

        // do bfs
        int maxNumberOfBombs = 0;
        for (int i = 0; i < gridLength; i++) {
            maxNumberOfBombs = Math.max(maxNumberOfBombs, doBfs(i, adjList));
        }
        return maxNumberOfBombs;
    }

    int doBfs(int node, Map<Integer, List<Integer>> adjList){
        Deque<Integer> que = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        que.offer(node);
        visited.add(node);

        while (!que.isEmpty()) {
            int current = que.poll();
            for(int neighbor : adjList.getOrDefault(current, new ArrayList<>())){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);
                    que.offer(neighbor);
                }
            }
        }
        return visited.size();
    }
}
