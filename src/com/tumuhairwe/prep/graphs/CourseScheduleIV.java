package com.tumuhairwe.prep.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1462 (medium)
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.
 *
 * For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
 *
 * You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
 *
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 */
public class CourseScheduleIV {

    /**
     * Solution summary
     * - Create & populate adjacency list of each course and its prereq
     * - Loop thru queries, forEach query, call isPrereq() and add answer to returnList
     * - in isPrereq() : implement recursive DFS fo reach dependent (returning true if there's a path from src-to-dest and false otherwise
     * - return resultList
     *
     * TC: O(Q X N^2):
     *  - creating the adjList = O(n^2) because we iterate over the prereqs.
     *  - iterate over the queries & do DFS (which takes O(V + E) -- which is equivalent to O(n^2)
     *  - so given number-of-queries = Q and DFS=n^2, Total TC=O(Q x n^2)
     *
     * sc: O(V + E)
     *  - adjList stores every edge in the list of prereqs -> requires O(n ^ 2) in the worst case
     *  - For DFS, we need the visited array of size N
     *  - recursive stack of DFS calls = O(N)
     * ref: https://leetcode.com/problems/course-schedule-iv/solutions/660839/java-topological-sorting/
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries){
        //0. create graph
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }

        //1 populate graph
        for(int[] path : prerequisites){
            int src = path[0];
            int dest = path[1];

            adjList.get(src).add(dest);
        }

        //1. traverse queries and build result
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries){
            int src = query[0];
            int dest = query[1];

            boolean[] visited_state = new boolean[numCourses];
            boolean isPrereq = isPrerequisite(src, dest, visited_state, adjList);
            result.add(isPrereq);
        }
        return result;
    }

    private boolean isPrerequisite(int src, int dest, boolean[] visited_state, Map<Integer, List<Integer>> adjList) {
        if(visited_state[src]){
            return false;
        }
        else if(src == dest){
            return true;
        }

        visited_state[src] = true;
        boolean isPrereq = false;
        for (int neighbor : adjList.get(src)){
            isPrereq = isPrereq || isPrerequisite(neighbor, dest, visited_state, adjList);
        }

        return isPrereq;
    }
}
