package com.tumuhairwe.prep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums (sorted in non-decreasing order) i.e. increasing order
 * Determine if its possible to split nums into 1 or more subsequences such that both
 * of the following conditions are true
 * - each is a consecutive increasing sequence such that both of the following conditions are true
 * - all have a length of 3 or more
 */
public class SplitArrayIntoConsecutiveSequences {
    public boolean isPossible(int[] nums) {
        //0. declare vars
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> appendFreqMap = new HashMap<>();

        //1. traverse freqMap
        for (Integer num : freqMap.keySet()){

            //2. if n+1 doesn't exist -> skip
            if(freqMap.get(num) == 0 || !freqMap.containsKey(num)){
               continue;
            }
            //3 if frequency of N > 0, decrement from freqMap, increment in appendFreqMap
            else if(freqMap.getOrDefault(num +1, 0) > 1){
                // decrement n, increment n + 1
                appendFreqMap.put(num, appendFreqMap.get(num) - 1);
                appendFreqMap.put(num + 1, freqMap.getOrDefault(num + 1, 0) + 1);
            }
            //4 else if n+1 exists in freqMap
            else if(freqMap.getOrDefault(num + 1, 0) > 0
                    && freqMap.getOrDefault(num + 2, 0) > 1){
                // else decrement num+1 & num+2 (from freqMap, increment num+1
                freqMap.put(num + 1, freqMap.get(num + 1) - 1);
                freqMap.put(num + 2, freqMap.get(num + 2) - 1);
                appendFreqMap.put(num + 1, freqMap.get(num + 1) + 2);
            }
            else {
                //5 exit when sequence is broken
                return false;
            }
            freqMap.put(num, freqMap.get(num) - 1);
        }

        //3. return true
        return true;
    }
}
