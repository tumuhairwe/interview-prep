package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2244 (medium)
 *
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task.
 * In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 *
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 *
 * ref: https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/description/
 */
public class MinimumRoundsToCompleteAllTasks {

    public static void main(String[] args) {
        int[] arr = {2,2,3,3,2,4,4,4,4,4};
        System.out.println("Should be 4: " + minimumRounds(arr));
    }
    public static int minimumRounds(int[] tasks){
        //0. create frequency map of tasks
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            int existingCount = freqMap.getOrDefault(tasks[i], 0);
            freqMap.put(tasks[i], existingCount + 1);
        }

        //1. calculate rounds (if round < 2 , return -1)
        int rounds = 0;
        for (Integer frequencyOfTask : freqMap.values()){
            if(frequencyOfTask < 2){
                return  -1;
            }
            rounds += frequencyOfTask /3;  // integer division will round down
            if(frequencyOfTask % 3 != 0){   // i.e. there was a remainder
                rounds++;
            }
        }

        return rounds;
    }
}
