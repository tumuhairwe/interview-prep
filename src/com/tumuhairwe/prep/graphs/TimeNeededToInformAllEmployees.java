package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 1376 (medium)
 * Time Needed to inform all employeers
 *
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.
 *
 * The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 *
 * ref: https://www.youtube.com/watch?v=zdBYi0p4L5Q
 * ref: https://leetcode.com/problems/time-needed-to-inform-all-employees/description/
 */
public class TimeNeededToInformAllEmployees {

    /**
     * Solution summary (BFS)
     * - create adjList from managers[] (key=managerId, value=List(of_employees> )
     * . create BFS queue and seed it with Pair/Entry (key=headId, val=informTime(headID) )
     * - initialize numberOfMinutes and loop until queue isEmpty()
     * - poll Pair/Entry from que
     * - update numberOfMinutes (max(currentMinutes, entry.getValue()/tome)
     * - Add every employee from adjList to adjList (key=supervisedEmployeeId, value=time_to_reach_amanger * time_to_reach_employye_
     * - when the loop exits, return time
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        //0. create adjList
        Map<Integer, List<Integer>> adjList_of_managers = new HashMap<>();
        for (int empId = 0; empId < manager.length; empId++) {
            if(empId == headID){
                continue;
            }

            int managerId = manager[empId];
            List<Integer> existingEmployees = adjList_of_managers.getOrDefault(managerId, new ArrayList<>());
            existingEmployees.add(empId);
            adjList_of_managers.put(managerId, existingEmployees);
        }

        //1. create BFS queue and seed with ceo
        Queue<Map.Entry<Integer, Integer>> que =new ArrayDeque<>();
        Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(headID, informTime[headID]);
        que.add(entry);

        int maxInformTime = 0;
        while (!que.isEmpty()){
            // poll entry from queue
            Map.Entry<Integer, Integer> empId_and_time = que.poll();

            int managerId = empId_and_time.getKey();
            int employeeInformTime = empId_and_time.getValue();;

            // update minuets
            maxInformTime = Math.max(maxInformTime, employeeInformTime);

            // poll from adjList and populate queue
            if(!adjList_of_managers.containsKey(managerId)){
                continue;
            }

            for(int directReportId : adjList_of_managers.get(managerId)){
                Map.Entry<Integer, Integer> entry1 = new AbstractMap.SimpleEntry<>(directReportId, employeeInformTime + informTime[directReportId]);
                que.add(entry1);
            }
        }

        return maxInformTime;
    }
}
