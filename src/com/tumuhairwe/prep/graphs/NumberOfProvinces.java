package com.tumuhairwe.prep.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 547 (medium)
 * ref https://leetcode.com/problems/number-of-provinces/description/
 *
 * Given n cities in a region (2D array of edges)
 *
 * Some of them connected i.e. edges[i][j] == 1 means the i-th city and the j-th city are cdirctectly
 * connected, and edges[i][j] = 0 means they're not connected
 *
 * Assuming transitive connection e.g. if a->b and b->c that means a is connected to c (a -> c)
 *
 * A province is a group of directly connected cities and no other cities outside the group
 *
 * return the total number of provinces
 *
 * Solution Summary
 * - Create vars (Set<Integer> visited and a counter)
 * - loop thru all cities in edges -- if a city is unvisted, call doDFS() and increment counter
 * - return counter
 *
 * - doDFS(): recursively call doDFS() for all cities connected to passedIn city AND UNVISITED
 * - add to visited set
 * - call doDFS()
 */
public class NumberOfProvinces {

    private static final int IS_CONNECTED = 1;

    public static void main(String[] args) {
        int[][] region = new int[][]{
                {1,1,0},{1,1,0},{0,0,1}
        };
        System.out.println("Should be 2 -> " + findCircleNum(region));
    }
    public static int findCircleNum(int[][] connectedEdges){
        Set<Integer> visited = new HashSet<>();
        int count = 0;

        // 0. Loop thru all connected provinces
        for (int i = 0; i<connectedEdges.length; i++){
            if(!visited.contains(i)){

                // 2. recursively call doDFS() for all unvisited nodes
                doDFS(connectedEdges, visited, i);

                // 3 increment counter
                count++;
            }
        }

        // 4. return count
        return count;
    }

    private static void doDFS(int[][] connectedEdges, Set<Integer> visited, int cityId) {
        // if a for each city in prpvinces, if UNVISITED and CONNECTED ... visit
        for (int neighbor = 0; neighbor < connectedEdges.length; neighbor++) {
            if(connectedEdges[cityId][neighbor] == IS_CONNECTED
                    && !visited.contains(neighbor)){
                visited.add(neighbor);
                doDFS(connectedEdges, visited, neighbor);
            }
        }
    }
}
