package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * Given an array of CPU tasks, each represented by A ot Z, and a cooling time N.
 * Each cycle or interval allows the completion of one task ... tasks can be completed in any order
 * Constraint: Identical tasks must be separated by at least N intervals due to cooling
 *
 * Return the MINIMUM_NUMBER_OF_INTERVALS required to complete all tasks.
 *
 * LeetCode 621 (medium)
 * ref: https://leetcode.com/problems/task-scheduler/description/
 * ref: https://www.prepbytes.com/blog/heap/difference-between-max-heap-and-min-heap/
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
        int SECOND_LAST_LETTER_OF_ALPHABET = 24;

        int[] taskFrequency = new int[SIZE_OF_ALPHABET];

        // 0. create a int[] of frequency of chars
        for (char c : tasks){
            taskFrequency[c - 'A']++;    // if c = B, b  - 'A' =  index_1, the next B will ++ that index to 2
        }
        // goal is to have a char_frequency int[] of all the count of each char, and then sort it

        // 1.sort to get frequency of tasks & get most frequency
        // O (log(n) === but size is 26 its fairly trivial
        Arrays.sort(taskFrequency);  // goal is to have the most frequent task is at the end of the array

        // 2. find the max/most frequent character
        int max_val = taskFrequency[LAST_LETTER_OF_ALPHABET];
        max_val = max_val - 1; // since we don't  have to wait for the last one
        int idle_slots = max_val * n;

        for (int i = SECOND_LAST_LETTER_OF_ALPHABET; i> idle_slots; i--) {
            // fill up the idle slots by filling them up with tasks
            idle_slots -= Math.min(taskFrequency[i], max_val);   // go backward giving priority to the most frequent tasks
        }

        // if idle_slot == 0 -> we filled up all the idle_slots
        // no matter what, we need to complete every task ..
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }

    public static int leastInterval_map(char[] tasks, int coolDownInterval){
        // create charFrequency map of tasks
        Map<Character, Integer> map = new HashMap<>();
        for(char c : tasks){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // create pq (that will put max number at top of heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b- a);
        maxHeap.addAll(map.values());   // put all values/tasks from map into heap

        // max heap is empty == we're done processing the tasks
        int cycles = 0;
        while (!maxHeap.isEmpty()){

            List<Integer> registryOfCurrentlyRunningProcesses = new ArrayList<>();
            for (int i = 0; i < coolDownInterval + 1; i++) {
                if(!maxHeap.isEmpty()){
                    registryOfCurrentlyRunningProcesses.add(maxHeap.remove());
                }
            }

            for (int i : registryOfCurrentlyRunningProcesses){
                if(--i > 0){
                    maxHeap.add(i);
                }
            }

            cycles += maxHeap.isEmpty() ? registryOfCurrentlyRunningProcesses.size() : coolDownInterval + 1;
        }

        return cycles;
    }

    class Pair<K, V>{
        K key;
        V val;
        public Pair(K k, V v){
            this.key = k;
            this.val = v;
        }

        public K getKey() {
            return key;
        }
    }
    public int leastInterval_pq(char[] tasks, int n) {
        // 0. base case
        if(n == 0){
            return tasks.length;    // there's 0 wait interval so we can do all task
        }

        // 1. Create maxHeap to track most frequently occuring tasks
        Comparator<Integer> reversedComp = (a, b) -> b - a;
        PriorityQueue<Integer> maxHeap = new PriorityQueue(reversedComp);
        Queue<Pair<Integer, Integer>> que = new LinkedList<>();

        // 2. create task-frequency array
        int[] taskFrequency = new int[26];
        for(char c : tasks){
            taskFrequency[c - 'A']++;
        }

        // 3. put all non-zero frequencies in pq
        for(int tFreq : taskFrequency){
            if(tFreq > 0){
                maxHeap.add(tFreq);
            }
        }

        // 4. initialize time var
        int time = 0;

        // 5. while pq has entries
        while(!maxHeap.isEmpty() || !que.isEmpty()){
            time++;

            // 6. pull taskFrequency, decrement by 1, add to queue (as pair(key=tFreq, ))
            if(!maxHeap.isEmpty()){
                int tFreq = maxHeap.poll();
                tFreq--;

                if(tFreq > 0){
                    que.add(new Pair(tFreq, time + n)); // pair keep tFreq + timeTaken + idleTime
                }

                if(!que.isEmpty() && que.peek().getKey() == time){
                    int tFreqCount = que.poll().getKey();
                    maxHeap.add(tFreqCount);
                }
            }
        }

        return time;
    }
}
