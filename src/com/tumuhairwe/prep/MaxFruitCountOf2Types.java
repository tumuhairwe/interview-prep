package com.tumuhairwe.prep;

import java.util.HashMap;
import java.util.Map;

public class MaxFruitCountOf2Types {
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }
    public static int findLength(char[] arr){
        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> fruitFrequencyMap = new HashMap<>();
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            fruitFrequencyMap.put(arr[windowEnd], fruitFrequencyMap.getOrDefault(arr[windowEnd], 0)+1);

            // shrink the sliding window until size = 2
            while (fruitFrequencyMap.size() > 2){
                fruitFrequencyMap.put(arr[windowStart], fruitFrequencyMap.get(arr[windowStart]) -1);
                if(fruitFrequencyMap.get(arr[windowStart]) == 0){
                    fruitFrequencyMap.remove(arr[windowStart]);
                }

                windowStart++;  // shrink the window
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
            }
        }
        //Char
        Character a = Character.valueOf('a');
        return maxLength;
    }
}
