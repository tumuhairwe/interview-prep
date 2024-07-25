package com.tumuhairwe.prep.graphs;

import java.util.*;

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
        System.out.println("Should be 2 -> " + findCircleNum_bfs(region));
    }

    public static int findCircleNum_bfs(int[][] connectedEdges){
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
        // if a for each city in provinces, if UNVISITED and CONNECTED ... visit
        for (int neighbor = 0; neighbor < connectedEdges.length; neighbor++) {
            if(connectedEdges[cityId][neighbor] == IS_CONNECTED
                    && !visited.contains(neighbor)){
                visited.add(neighbor);
                doDFS(connectedEdges, visited, neighbor);
            }
        }
    }

    int CONNECTED = 1;
    public int findCircleNum(int[][] edges) {
        int numProvinces = 0;
        int numEdges = edges.length;

        boolean[] visited = new boolean[numEdges];
        for(int i=0; i<numEdges; i++){
            if(!visited[i]){
                visited[i] = true;
                numProvinces++;
                bfs(edges, visited, i);
            }
        }

        return numProvinces;
    }

    void bfs(int[][] adjList, boolean[] visited, int nodeId){
        //1. do dfs
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(nodeId);
        visited[nodeId] = true;

        while (!que.isEmpty()) {
            int node = que.poll();

            for (int neighbor = 0; neighbor < adjList.length; neighbor++) {
                if(!visited[neighbor] && adjList[node][neighbor] == CONNECTED){
                    visited[neighbor] = true;
                    que.offer(neighbor);
                }
            }
        }
    }
    /**
     * Solution summary (UnionFind impl)
     * - create parent[] & fill with UNCONNECTED
     * - loop thru 2D array ... and union(i, j)
     * - after 2D array loop, loop thru parent[] and count all nodes still UNCONNECTED
     * - return count
     */
    int[] parent;
    int UNCONNECTED = -1;
    int findCircleNum_unionFind(int[][] connectedEdges){
        int CONNECTED = 1;

        //0. declare vars
        parent = new int[connectedEdges.length];
        Arrays.fill(parent, UNCONNECTED);

        // 1. union all connected edges
        for (int i = 0; i < connectedEdges.length; i++) {
            for (int j = 0; j < connectedEdges[0].length; j++) {
                if(connectedEdges[i][j] == CONNECTED){
                    union(i, i);
                }
            }
        }

        //3. find all unconnected nodes in parent
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if(parent[i] == UNCONNECTED){
                count++;
            }
        }

        return count;
    }

    int find(int nodeA){
        while (parent[nodeA] != UNCONNECTED){
            nodeA = find(parent[nodeA]);
        }

        return nodeA;
    }
    boolean union(int nodeA, int nodeB){
        int parentOfA = find(nodeA);
        int parentOfB = find(nodeB);

        if(parentOfA != parentOfB){
            parent[parentOfA] = parentOfB;
        }

        return true;
    }
}
