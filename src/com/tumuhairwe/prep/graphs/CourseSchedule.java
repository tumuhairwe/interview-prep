package com.tumuhairwe.prep.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 207 (medium)
 *
 * Given a total of numCourses courses to be taken (labedl 1 to numCourses - 1)
 * And given an array of prerequisites where prereq[i] = [a, b]
 * indicates that you must take course b before taking course a
 *
 * Return true if you can finish all the courses, otherwise, return false
 *
 * ref: https://leetcode.com/problems/course-schedule/description/
 *
 * Solution Summary:
 * - Create adjacency list index=courseId, value=List_of_courses (List<List<String> )
 * - Populate list with know list of prerequsites
 * - Loop thru all courses and determine if there's a cycle in each
 *      - isCyclic uses DFS to
 *          - determine if currentIndex is already in path (if it is, return true)
 *          - set visited[currentIndex] = IS_IN_PATH ( val = 2)
 *          - Loop thru all its preqs (of currentIndex), if prereq has been TRAVERESED before, return true
 *          - After all prereqs have not been traversed & loop has ended, mark visited[currentInd] = HAS_BEEN_TRAVERSED (Ee.g. val 1)
 *          - return false
 *
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0207-course-schedule.java
 * ref: https://www.youtube.com/watch?v=EgI5nU9etnU
 */
public class CourseSchedule {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prereqs = new int[][]{{1, 0}};
        System.out.println("1. Can you take " + Arrays.toString(prereqs));
        System.out.println(canFinish(numCourses, prereqs));

        prereqs = new int[][]{{1, 0}, {0, 1}};
        System.out.println("2. Can you take " + Arrays.toString(prereqs));
        System.out.println(canFinish(numCourses, prereqs));
    }
    protected static boolean canFinish(int numCourses, int[][] prerequisites){
        // 0. create and initialize adj list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // 1. populate adjList
        for (int i = 0; i < prerequisites.length; i++) {
            adjList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        // 2. detect if cycle exists by creating and tracking visited[]
        int[] visited = new int[numCourses];
        int NOT_YET_VISITED = 0;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == NOT_YET_VISITED){
                if(isCyclic(adjList, visited, i)){
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isCyclic(List<List<Integer>> adjList, int[] visited, int currentIndex) {
        int IS_IN_PATH = 2;
        if(visited[currentIndex] == IS_IN_PATH){
            return true;
        }

        visited[currentIndex] = IS_IN_PATH;
        int HAS_BEEN_TRAVERSED = 1;
        for (int i = 0; i < adjList.get(currentIndex).size(); i++) {
            int prereqId = adjList.get(currentIndex).get(i);
            if(visited[prereqId] != HAS_BEEN_TRAVERSED){
                return true;
            }
        }

        visited[currentIndex] = HAS_BEEN_TRAVERSED;
        return false;
    }
}