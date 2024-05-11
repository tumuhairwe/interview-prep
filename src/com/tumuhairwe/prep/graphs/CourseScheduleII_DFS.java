package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 210 (medium)
 *
 * Given a total number of courses (numCourse) lables from 0 to numCourses - 1;
 * and Given an array of prerequisites where prerequisite[i] = [a, b] indicates that you must take course b
 * first if you take course a
 *
 * Return the ordering of the courses you should take to finish all course. If its impossible to finish all courses
 * return an empty array
 */
public class CourseScheduleII_DFS {
    // 0. init constants
    static final int UNVISITED_STATE = 0;
    static final int VISITING_STATE = 1;
    static final int VISITED_STATE = 2;

    // 1. initialize adj list and state + result vars based on size of numCourses
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    int[] state;  // tracks the state of each course (index = courseId), value = state)
    int[] result; // stores valid ordering of the courses
    int i = 0;

    // combines DFS + Cycle detection
    // TC = O(V + E) .. where v = number-of-courses, v = edges that are traversed (only once)
    // TC = O(V + E) ... because of the representation of the adjacency list
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // init vars
        state = new int[numCourses];
        result = new int[numCourses];

        // 1. create adjacency list
        for(int courseId = 0; courseId < numCourses; courseId++){
            adjList.put(courseId, new ArrayList<>());
        }
        for(int[] p : prerequisites){
            int preReq = p[1];
            int course = p[0];

            adjList.get(course).add(preReq);
        }

        // 2. start DFS by initializing the Queue with courses with 0 prereqs
        for(int i=0; i < numCourses; i++){
            if(!dfs(i)){
                return new int[0];  // return if there's a cycle
            }
        }

        return result;
    }

    // 3. do DFS
    boolean dfs(int course){
        // 3.0. check for base case of DFS
        if(state[course] == VISITED_STATE){   // 2
            return true;    // no cycle has been encountered
        }
        else if(state[course] == VISITING_STATE){ // 1
            return false;    // we have encountered a cycle (i.e. course on path already is being visited again)
        }

        // 3.1 udpate state of course
        // state must be UNVISITED == 0;
        state[course] = VISITING_STATE;

        // 3.2 check outgoping edge of each vertex
        for(Integer prereq : adjList.get(course)){
            if(!dfs(prereq)){   // recursive call to check each child
                return false;   // we detected a cycle
            }
        }

        state[course] = VISITED_STATE;    // 2
        result[i] = course;
        ++i;
        return true;
    }
}
