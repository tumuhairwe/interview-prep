package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 207 (medium)
 *
 * Given a total of numCourses courses to be taken (labeled 1 to numCourses - 1)
 * And given an array of prerequisites where prereq[i] = [a, b]
 * indicates that you must take course b before taking course a
 *
 * Return true if you can finish all the courses, otherwise, return false
 *
 * ref: https://leetcode.com/problems/course-schedule/description/
 *
 * Solution Summary:
 * - Create adjacency list index=courseId, value=List_of_courses (List<List<String> )
 * - Populate list with known list of prerequisites
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
public class CourseSchedule_DFS {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prereqs = new int[][]{{1, 0}};
        System.out.println("1. Can you take " + Arrays.toString(prereqs));
        System.out.println(canFinish(numCourses, prereqs));

        prereqs = new int[][]{{1, 0}, {0, 1}};
        System.out.println("2. Can you take " + Arrays.toString(prereqs));
        System.out.println(canFinish(numCourses, prereqs));
    }

    /**
     * Solution summary
     * - Create adjList
     * - populate in-degree array with count of in_coming nodes to a source
     * - create & seed Queue with 0 in-degree nodes
     * - do BFS to count all visited nodes with 0 degrees
     * - match count of visitedNodes with numCourses
     *
     * TC: O(m + n)
     * SC: O(m + n)
     */
    public boolean canFinish_bfs(int numCourses, int[][] prerequisites){
        //0. init vars
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] in_degree = new int[numCourses];

        Arrays.fill(in_degree, 0);

        //1. create adjList
        for (int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][0];
            int dest = prerequisites[i][1];

            //2. populate in-degree array
            in_degree[src]++;
            adjList.putIfAbsent(dest, new ArrayList<>());
            adjList.get(dest).add(src);
        }

        //3. seed queue with 0-degree nodes
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if(in_degree[i] == 0){
                que.offer(i);
            }
        }

        //4. do BFS to count all visited nodes with 0 degrees
        int nodesVisited = 0;
        while (!que.isEmpty()){
            Integer curr = que.poll();
            nodesVisited++;

            for (Integer neighbor : adjList.get(curr)){
                in_degree[neighbor]--;

                if(in_degree[neighbor] == 0){
                    que.add(neighbor);
                }
            }
        }

        // match all visited nodes with numCourses
        return nodesVisited == numCourses;
    }

    static int HAS_BEEN_TRAVERSED = 1;
    static int NOT_YET_VISITED = 0;
    static int IS_IN_PATH = 2;

    /**
     * Solution Summary
     * - Create adjacency list of courses (key=courseId, val=List_of_prereqs)
     * - Create array to track visited courses (index=course_id)
     * - for each course, if its unvisited, call isCyclic(adjList, visited[], courseId)
     */
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

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == NOT_YET_VISITED){
                if(isCyclic(adjList, visited, i)){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Solution Summary
     * - to determine if cyclic, if state IS_IN_PATH, return true, if its not even in adjList, return falss
     * - make visited[ courseId ] = IS_IN_PATH
     * - recursively call isCyclic() on each prereq (if state is not HAS_BEEN_TRAVERSED) ... return true
     * - at the end, mark visited[ courseId ] = HAS_BEEN_TRAVERSED
     */
    private static boolean isCyclic(List<List<Integer>> adjList, int[] visited, int courseId) {
        // check if already visited
        if(visited[courseId] == IS_IN_PATH){
            return true;
        }

        // courseId doesn't have its own prereq
        if(adjList.get(courseId) == null){
            return false;
        }

        // put in path
        visited[courseId] = IS_IN_PATH;
        for (Integer prereqId : adjList.get(courseId)){
            if(visited[prereqId] != HAS_BEEN_TRAVERSED){
                return true;
            }
        }

        // mark as visited
        visited[courseId] = HAS_BEEN_TRAVERSED;
        return false;
    }
}
