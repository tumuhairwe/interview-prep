package com.tumuhairwe.prep.graphs;

import java.util.*;

public class TopologicalSort {

    public static List<Integer> topologicalSort(int[][] edges, int n){
        // 0. initialize adj list -> create Integer key for every 1 <= x <= n
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Optional<Integer> o = adjList.entrySet()
                .stream()
                .sorted()
                .map(e -> e.getValue().size())
                .findFirst();

        for (int i = 1  ; i < n + 1; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int src = edge[0];
            int dst = edge[1];
            adjList.get(src).add(dst);
        }

        // 1. then do DFS on each i
        List<Integer> topSort = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i < n; i++) {
            dfs(i, adjList, visited, topSort);
        }

        Collections.reverse(topSort);
        return topSort;
    }

    private static void dfs(int src, Map<Integer, List<Integer>> adjList, Set<Integer> visited, List<Integer> topSort) {
        // 0. check if visited
        if(visited.contains(src)){
            return;
        }

        // 1. add to visited
        visited.add(src);

        // 2. recursively call DFS on each neighbor
        for (int neighbor : adjList.get(src)){
            dfs(neighbor, adjList, visited, topSort);
        }

        topSort.add(src);
    }
}
