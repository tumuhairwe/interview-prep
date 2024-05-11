package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 210 (medium)
 *
 * Given a total of numCourses to be taken, labeled from 0 to numCourses - 1
 * Given an array that indicates the prerequisites[i] = [A, B] indicating that B is prereq of A
 *
 * Return the ordering of courses you should take to finish all courses.
 * There are many valid answers so return any of them
 *
 * If its impossible to finish them all, return an empty array
 *
 * Solution Summary
 *
 * - Build the adjacency graph
 *      => (Map<Course, List<Course>> i.e. a course and its list of prerequisites)
 *          because each course is a node in the graph, and each prereq relationship is an edge from the prereq course
 * - Calculate the in-degree of each course/node (i.e. count the number of prereqs for each course)
 * - Perform topological sort
 *
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0207-course-schedule.java
 * ref: https://leetcode.com/problems/course-schedule-ii/description/
 * ref: https://www.youtube.com/watch?v=Akt3glAwyfY
 */
public class CourseSchedule_TopologicalSort {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prereqs = new int[][]{{1,0}};
        int[] result = findOrder(numCourses, prereqs);
        System.out.println("Given that there are " + numCourses + " to take. Correct order is " + Arrays.toString(result));

        numCourses = 4;
        prereqs = new int[][]{{1,0}, {2, 0}, {3, 1}, {3, 2}};
        result = findOrder(numCourses, prereqs);
        System.out.println("Given that there are " + numCourses + " to take. Correct order is " + Arrays.toString(result));

    }
    public static int[] findOrder(int numCourses, int[][] prerequsites){
        // 0. instantiate vars: adjacency-list, in-degree and topological order arrays
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] in_degree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // `1. create adjacency list while populating in-degree array
        for (int i = 0; i < prerequsites.length; i++) {
            int postReq = prerequsites[i][0];
            int preReq = prerequsites[i][1];

            List<Integer> prereqList = adjList.getOrDefault(preReq, new ArrayList<>());
            prereqList.add(postReq);

            adjList.put(preReq, prereqList);
            in_degree[postReq] += 1;
        }

        // 2. perform topological sort (using queue)
        // 2.0 start DFS: add all courses with no prereqs to the queue (i.e. if a course has 0 prereqs, add to queue)
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(in_degree[i] == 0){
                que.add(i);
            }
        }

        // 2.1 while the queue is not empty, remove the node/course from the queue and add it to the result (topologicalOrder array)
        int i = 0;
        while (!que.isEmpty()){
            int course = que.remove();
            topologicalOrder[i++] = course;

            // 2.2 for each of each node's neighbors, decrement the in-degree count by 1
            if(adjList.containsKey(course)){
                for (Integer neighbor : adjList.get(course)){
                    in_degree[neighbor]--;

                    // 2.3 if a neighbors in-degree becomes zero, add it to the queue (i.e. it has no unvisited prereqs)
                    if(in_degree[neighbor] == 0){
                        que.add(neighbor);
                    }
                }
            }
        }

        // 3. check for a cycle -> if a result-size/sorted or processed or visited nodes != number-of-all-courses ...
        if(i == numCourses){
            return topologicalOrder;
        }

        // 3a. -> there's a cycle
        return new int[0];
    }
}
