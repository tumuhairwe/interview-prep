package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * Given an array of CPU tasks, each represented by A ot Z, and a cooling time N.
 * Each cycle or interval allows the completion of one task ... tasks can be completed in any order
 * Constraint: Identical tasks must be separated by at least N intervals due to cooling
 *
 * Return the MINIMUM_NUMBER_OF_INTERVALS required to complete all tasks.
 *
 * LeetCode 621 (medium)
 * ref: https://leetcode.com/problems/task-scheduler/description/
 */
public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'}; int n = 2;
        int result = leastInterval(tasks, n);
        System.out.println("There are " + result + " minimum number of intervals required to complete all tasks");
    }

    public static int leastInterval(char[] tasks, int n){
        int SIZE_OF_ALPHABET = 26;
        int LAST_LETTER_OF_ALPHABET = 25;
        int SECOND_LETTER_OF_ALPHABET = 24;

        int[] char_map = new int[SIZE_OF_ALPHABET];

        // 0. create a int[] of frequency of chars
        for (char c : tasks){
            char_map[c - 'A']++;    // if c = B, b  - 'A' =  index_1, the next B will ++ that index to 2
        }
        // goal is to have a char_frequency int[] of all the count of each char, and then sort it

        // 1.sort to get frequency of tasks & get most frequency
        // O (log(n) === but size is 26 its fairly trivial
        Arrays.sort(char_map);  // goal is to have the most frequent task is at the end of the array

        // 2. find the max/most frequent character
        int max_val = char_map[LAST_LETTER_OF_ALPHABET];
        max_val = max_val - 1; // since we don't  have to wait for the last one
        int idle_slots = max_val * n;

        for (int i = SECOND_LETTER_OF_ALPHABET; i> idle_slots; i--) {
            // fill up the idle slots by filling them up with tasks
            idle_slots -= Math.min(char_map[i], max_val);   // go backward giving priority to the most frequent tasks
        }

        // if idle_slot == 0 -> we filled up all the idle_slots
        // no matter what, we need to complete every task ..
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }
}
