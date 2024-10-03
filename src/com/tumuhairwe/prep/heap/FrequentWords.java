package com.tumuhairwe.prep.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string-array/sentence, words, and an integer w, return the k
 *  most frequent strings.
 *
 *  LeetCode 347 (medium)
 *  ref: https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * Sort the frequencies from highest to lowest and then return the top k
 *  frequent words. Words with the same frequency should be sorted by their lexicographical order.
 *
 *  Solution:
 *  1. Create charFrequency map (key=word, value = numberOfTimes_it_occurs
 *  2. Create pq of Map.Entry (key=word,value=frequency_count)
 *  3. Iterate over freqMap and add entries into pq while maintaining pq.size == W
 */
public class FrequentWords {

    public static void main(String[] args) {
        int k = 4;
        String[] arr = new String[]{ "the", "day", "is", "sunny", "the", "the","the", "sunny", "is", "is"};
        List<String> results = topKFrequent(arr, k);
        System.out.println("The most " + k + "frequent words: " + results);
    }
    /**
     * Solution summary
     * - create freqMap of nums
     * - create pq of Map.Entry<> that will sort by value
     * - add entries from freqMap to pq. Poll off everything when size exceeds K
     * - collect K elements remaining in pq into a List
     * - map list to [] and return
     *
     * TC = in O(n + klogn) == where n = size or words array, k = number-of-top-k
     * SC = O(n) space
     */
    public int[] topKFrequent(int[] nums, int k) {
        //0. generate frequency map of nums[]
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        //1. create pq of Map.Entry<> that will sort by value
        Comparator<Map.Entry<Integer, Integer>> comp = Comparator.comparingInt(Map.Entry::getValue);
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(comp);

        //2. add entries from freqMap to pq .. poll off everything when size exceeds K
        for(Map.Entry<Integer, Integer> e : freqMap.entrySet()){
            pq.add(e);

            if(pq.size() > k){
                pq.poll();
            }
        }

        // collect K elements remaining in pq into a List
        List<Integer> topK = pq.stream()
                //.filter(e -> e.getValue() <= k)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        // return list
        return topK.stream().mapToInt(Integer::intValue).toArray();
    }

    // TC = in O(n + klogn) == where n = size or words array, k = number-of-top-k
    // SC= O(n) space
    public static List<String> topKFrequent(String[] words, int K) {
        Map<String, Integer> freqMap = new HashMap<>();
        for(int i=0; i<words.length; i++){
            int existingCount = freqMap.getOrDefault(words[i], 0);
            freqMap.put(words[i], existingCount+1);
        }

        //Comparator<Map.Entry<String, Integer>> comp = (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> e1.getValue() - e2.getValue();
        Comparator<Map.Entry<String, Integer>> comp = Comparator.comparingInt(Map.Entry::getValue);

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue(comp);
        freqMap.entrySet()
                .stream()
                .forEach(entry -> {
                    if(pq.size() <= K){
                        pq.add(entry);
                    }
                    else{
                        if(pq.peek().getValue() > entry.getValue()){
                            pq.poll();
                            pq.add(entry);
                        }
                    }
                });

        //int[] arr = results.stream().mapToInt(e -> e).toArray();
        return pq.stream().map(e -> e.getKey()).collect(Collectors.toList());
    }

    /**
     * Bucket sort impl (TC: O (n)
     * Solution summary
     * - init vars
     * - create freqMap of nums
     * - populate buckets based on frequency (transform freqMap into List[])
     * - loop backwards && add non-null entries to result
     * - return result
     */
    public List<Integer> topKFrequent_bucketSort(int[] nums, int k){
        //0. init vars
        List<Integer>[] buckets = new List[nums.length + 1];
        Map<Integer, Integer> freqMap = new HashMap<>();

        //1. create freqMap of nums
        for(int i=0; i<nums.length; i++){
            int existingCount = freqMap.getOrDefault(nums[i], 0);
            freqMap.put(nums[i], existingCount + 1);
        }

        //1. populate buckets based on frequency (transform freqMap into List[])
        for (int key : freqMap.keySet()){
            int frequency = freqMap.get(key);
            if(buckets[key] == null){
                buckets[key] = new ArrayList<>();
            }
            buckets[key].add(key);
        }

        //2. loop backwards && add non-null entries to result
        List<Integer> result = new ArrayList<>();
        for (int pos = buckets.length - 1; pos >= 0 && result.size() < k; pos--){
            if(buckets[pos] != null){
                result.addAll(buckets[pos]);
            }
        }

        return result;
    }
}
