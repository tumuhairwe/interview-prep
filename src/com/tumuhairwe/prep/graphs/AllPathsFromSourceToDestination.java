package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 1059 (medium)
 * Given the edges of a directed graph where edge[i] = [a, b] indicates there is an edge
 * between nodes a and b, & the nodes source and destination of this graph,
 * determine whether all the paths starting from source eventually end at destination
 * i.e.
 * - at least 1 path exists from source to destination
 * - if a path exists from the source with no outgoing edges then that node is equal to destination
 * - the number of possible paths from src to dest is a finite number
 *
 * Return true if and only if all roads from source lead to destination
 */
public class AllPathsFromSourceToDestination {

    /**
     * Solution summary
     *
 thera    * SC: O(V + E)
     * TC: (V + E)
     */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        //0 build adjList
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges){
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);
        }

        //1 if src has no dest (i.e. leaf node) it should be equal to itself
        if(adjList.containsKey(source) && adjList.get(source).isEmpty()){
            return source == destination;
        }

        //2 destination node should not have outgoing edge.
        if(adjList.containsKey(destination)){
            return false;
        }

        return dfs(adjList, source, destination, new boolean[n]);
    }

    private boolean dfs(Map<Integer, List<Integer>> adjList, int source, int destination, boolean[] visited) {
        if(source == destination){
            return true;
        }

        // non-destination node must have outgoing edge otherwise, we're stuck
        if(!adjList.containsKey(source)){
            return false;
        }

        visited[source] = true;
        for (Integer node : adjList.keySet()){
            if(visited[node]){
                return false;
            }
            else if(!dfs(adjList, node, destination, visited)){
                return false;
            }
        }
        visited[source]= false;
        return true;
    }
}