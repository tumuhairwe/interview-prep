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
        int[] result = new CourseSchedule_TopologicalSort().findOrder(numCourses, prereqs);
        System.out.println("Given that there are " + numCourses + " to take. Correct order is " + Arrays.toString(result));

        numCourses = 4;
        prereqs = new int[][]{{1,0}, {2, 0}, {3, 1}, {3, 2}};
        result = new CourseSchedule_TopologicalSort().findOrder(numCourses, prereqs);
        System.out.println("Given that there are " + numCourses + " to take. Correct order is " + Arrays.toString(result));

    }
    // 0. init constants
    static final int UNVISITED_STATE = 0;
    static final int VISITING_STATE = 1;
    static final int VISITED_STATE = 2;

    // 1. initialize adj list and state + result vars based on size of numCourses
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    int[] state;  // tracks the state of each course (index = courseId), value = state)
    int[] topologicalOrder; // stores valid ordering of the courses
    int i = 0;

    // combines DFS + Cycle detection
    // TC = O(V + E) .. where v = number-of-courses, v = edges that are traversed (only once)
    // TC = O(V + E) ... because of the representation of the adjacency list

    /**
     * Solution Summary
     * - init 2 arrays to track
     *      - state = will track the state of each course
     *      - topologicalOrder will track the order in which the courses have to be taken
     * - Create adjacency list of each key=course, val=list_of_prereqs
     * - perform DFS on each course (to make sure each course can be taken) -- dfs method will return false if it can't
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // init vars
        state = new int[numCourses];
        topologicalOrder = new int[numCourses];

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
            if(!dfs_hasNoCycle(i)){
                return new int[0];  // return if there's a cycle
            }
        }

        return topologicalOrder;
    }

    // 3. do DFS

    /**
     * Solution summary
     * - For each course return false it has a cycle
     * - To do that, check each course's prereqs, & iteratively call dfs_hasNoCycle()
     * - make state[courseId] = VISITING
     * - for each course (if state == VISITED or VISITING, return false
     * - when done with all prereqs,
     *      - mark course as VISITED
     *      - add course topological_sort []
     */
    boolean dfs_hasNoCycle(int course){
        // 0. check state of course
        if(state[course] == VISITED_STATE){   // 2
            return true;    // no cycle has been encountered
        }
        else if(state[course] == VISITING_STATE){ // 1
            return false;    // we have encountered a cycle (i.e. course on path already is being visited again)
        }
        // state must be UNVISITED == 0;
        state[course] = VISITING_STATE;

        // 2 check outgoping edge of each vertex
        for(Integer prereq : adjList.get(course)){
            if(!dfs_hasNoCycle(prereq)){   // recursive call to check each child
                return false;   // we detected a cycle
            }
        }

        state[course] = VISITED_STATE;    // 2
        topologicalOrder[i] = course;
        ++i;
        return true;
    }

//    static int[] state;
//    static int[] result;
//    Map<Integer, List<Integer>> adjList;
//    static int i =0;
//    static final int UNVISITED = 0;
//    static final int VISITING_STATE = 1;
//    static final int VISITED_STATE = 2;
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        // init vars
//        state = new int[numCourses];
//        result = new int[numCourses];
//
//        // 1. create adjacency list
//        for(int courseId = 0; courseId < numCourses; courseId++){
//            adjList.put(courseId, new ArrayList<>());
//        }
//        for(int[] p : prerequisites){
//            int preReq = p[1];
//            int course = p[0];
//
//            adjList.get(course).add(preReq);
//        }
//
//        // 2. start DFS by initializing the Queue with courses with 0 prereqs
//        for(int i=0; i < numCourses; i++){
//            if(!dfs(i)){
//                return new int[0];  // return if there's a cycle
//            }
//        }
//
//        return result;
//    }
//
//    // 3. do DFS
//    boolean dfs(int course){
//        // 3.0. check for base case of DFS
//        if(state[course] == VISITED_STATE){   // 2
//            return true;    // no cycle has been encountered
//        }
//        else if(state[course] == VISITING_STATE){ // 1
//            return false;    // we have encountered a cycle (i.e. course on path already is being visited again)
//        }
//
//        // 3.1 udpate state of course
//        // state must be UNVISITED == 0;
//        state[course] = VISITING_STATE;
//
//        // 3.2 check outgoping edge of each vertex
//        for(Integer prereq : adjList.get(course)){
//            if(!dfs(prereq)){   // recursive call to check each child
//                return false;   // we detected a cycle
//            }
//        }
//
//        state[course] = VISITED_STATE;    // 2
//        result[i] = course;
//        ++i;
//        return true;
//    }
}
