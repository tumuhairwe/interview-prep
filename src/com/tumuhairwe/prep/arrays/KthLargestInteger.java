package com.tumuhairwe.prep.arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 1985 (medium)
 * Given an array of strings (nums) and an integer K, where each string in nums represents an integer
 * WITHOUT leading zeros,
 * return the string that represents the K-th largest integer in nums
 * NB: duplicate numbers should be counted distinctly e.g. nums=["1", "2", "2"] ...
 * then "2" is the 1st largest
 * "2" is the 2nd largest number
 * and "1" is the 3rd largest number
 */
public class KthLargestInteger {

    /**
     * Solution summary
     * - Create a custom comparator that sorts by string.length 1st & then string.compare
     * - Put all numbers/string in pq (without parsing them)
     * - Remove excess number from top of pq as you add (when size > k)
     * - return top of pq (will be k-th largest number)
     *
     * TC: O(n log k)
     * SC: O(n)
     */
    public String kthLargestNumber(String[] nums, int k) {
        //1. create comp that sorts by length ... and then compare the strings
        Comparator<String> comp = (String a, String b) -> {
            if(a.length() != b.length()){
                return Integer.compare(a.length(), b.length()); // "123" > "12"
            }
            // lexicographical compare
            return a.compareTo(b);
        };

        //2. put all strings into pq of size K
        PriorityQueue<String> pq = new PriorityQueue<>(comp);
        for(String num : nums){
            pq.add(num);

            if(pq.size() > k){
                pq.remove();    // remove top of pq if size exceeds k
            }
        }

        // top of pq == k-th largest
        return pq.remove();
    }
}
