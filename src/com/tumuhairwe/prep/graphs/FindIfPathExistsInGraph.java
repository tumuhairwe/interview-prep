package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 1971 (easy)
 *
 * Given a bi-directional graph with n vertices where each vertex is labeled 0 to n-1
 *
 * The edges in the graph are represented as a 2D array edges where edges[i] = [u , v] denotes
 * a bi-directional edge between vertex u and v.
 * Every par is connected by at most 1 edge and no vertex has an edge to itself
 *
 * Determine if there is a valid path that exists from source to destination
 */
public class FindIfPathExistsInGraph {
    public static void main(String[] args) {
        int [][] edges = new int[][]{
                {0,1},{1,2},{2,0}
        };
        boolean pathExists = validPath(3, edges,0, 2);
        System.out.println("Path " + (pathExists ? "exists": "does not exist") + " from " + 0 + " to " + 2);

        edges = new int[][]{
                {0,1}, {0,2}, {3,5}, {5,4}, {4,3}
        };
        pathExists = validPath(6, edges,0, 5);
        System.out.println("Path " + (pathExists ? "exists": "does not exist") + " from " + 0 + " to " + 5);
    }

    /**
     * Solution Summary
     * - Create Adjacency list out of 2D array of edges
     *      - adjList is bidirectional (since nodes are unweighted)
     *      - src -> [dest]
     *      - dest -> [source]
     * - do DFS on edges (to get a flattened List<> of all the nodes that originate from source
     *      -> src -> neighbor_1
     *      - neighbor_1 -> neighbor_2
     *      - neighbor_2 -> neighbor_n
     * - Track all nodes into a visited set
     * - Return whether or not vistedSetOfNodes.contains(destination)
     */
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        //0. base case
        if(edges.length == 0 && destination == 0){
            return true;
        }

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        // 1. create adjList
        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][0];

            adjList.putIfAbsent(src, new ArrayList<>());
            adjList.putIfAbsent(dest, new ArrayList<>());

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

        // 1. do DFS on each neighbor & collect visited set
        Set<Integer> visited = new HashSet<>();
        doDfs(adjList, source, visited);

        //n2. return true if destination is visited
        return visited.contains(destination);
    }

    static void doDfs(Map<Integer, List<Integer>> adjList, int source, Set<Integer> visited){
        if(visited.contains(source)){
            return;
        }

        visited.add(source);
        for (Integer neighbor : adjList.get(source)){
            doDfs(adjList, neighbor, visited);
        }
    }
}
