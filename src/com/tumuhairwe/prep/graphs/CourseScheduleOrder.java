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
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0207-course-schedule.java
 * ref: https://leetcode.com/problems/course-schedule-ii/description/
 */
public class CourseScheduleOrder {

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
        int[] topolgicalOrder = new int[numCourses];

        // create adjacency list while populating in-degree array
        for (int i = 0; i < prerequsites.length; i++) {
            int postReq = prerequsites[i][0];
            int preReq = prerequsites[i][1];

            List<Integer> prereqList = adjList.getOrDefault(preReq, new ArrayList<>());
            prereqList.add(postReq);

            adjList.put(preReq, prereqList);
        }

        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(in_degree[i] == 0){
                que.add(i);
            }
        }

        int i = 0;
        while (!que.isEmpty()){
            int course = que.remove();
            topolgicalOrder[i++] = course;

            if(adjList.containsKey(course)){
                for (Integer neighbor : adjList.get(course)){
                    in_degree[neighbor]--;

                    if(in_degree[neighbor] == 0){
                        que.add(neighbor);
                    }
                }
            }
        }

        if(i == numCourses){
            return topolgicalOrder;
        }

        return new int[0];
    }
}
