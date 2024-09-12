package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 1136 (medium)
 * You're given an integer n, which indicates that there are n courses
 * labeled from 1 to n
 * You are also given an array relations where rel[i] = [prevCourse, nextCourse]
 * represents a prerequisite relationship between nextCourse and prevCourse
 *
 * In one semester, you can take any number of courses as long as you have taken all the prereqs
 * in the previous semester for the course you are taking
 *
 * Return the minimum number of courses needed to take all course.If there's no way to tkae all
 * courses, return -1
 *
 * ref: https://leetcode.com/problems/parallel-courses/solutions/5554954/easy-beginner-friendly-intuitive-beats-100-topo-sort/?envType=problem-list-v2&envId=topological-sort
 */
public class ParallelCourses {

    /**
     * Solution summary
     *
     * - Create & populate adjList & in_degree  array
     * - Create que and seed with courses with 0 in_degree count
     * - init semesterCount=0, & visitedCourseCount = 0 & do bfs
     * - for each iteration
     * a) increment semesterCount
     * b) while queue depth > 0, pop current course from queue, check all currentCourse's neighbors & decrement each neighbors' in_degree count.
     * - if neighbor's in-degree count == 0, add to queue
     * - when you exit, return -1 if visitedCourseCount = N, else return semesterCount;
     */
    public int minimumSemesters(int n, int[][] relations) {
        //0. create adjList
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] in_degree = new int[n + 1];

        for (int[] rel : relations){
            int prevCourse = rel[0];
            int nextCourse = rel[1];

            adjList.putIfAbsent(prevCourse, new ArrayList<>());
            adjList.get(prevCourse).add(nextCourse);
            in_degree[nextCourse]++;
        }

        //1. create and see queue with courses with in-degree count = 0;
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if(in_degree[i] == 0){
                que.offer(i);
            }
        }

        //2. check if possible before even proceeding
        if (que.isEmpty()){
            return -1;
        }

        //3. init semesterCount and visitedCourseCount & do bfs
        int semesterCount = 0;
        int visitedCourseCount = 0;
        while (!que.isEmpty()){
            int qDepth = que.size();
            semesterCount++;

            while (qDepth-- > 0){
                visitedCourseCount++;
                int currCourse = que.poll();
                if(!adjList.containsKey(currCourse)){
                    continue;
                }

                for(int neighbor : adjList.get(currCourse)){
                    in_degree[neighbor]--;

                    if(in_degree[neighbor] == 0){
                        que.offer(neighbor);
                    }
                }
            }
        }

        return visitedCourseCount == n ? semesterCount : -1;
    }
}
