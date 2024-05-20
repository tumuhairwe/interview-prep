package com.tumuhairwe.prep.window;

import java.util.*;

/**
 * Given 2 strings S and T, return the minimum window substring of S such that every character in T
 * (including duplicates) is included ind the same window.
 * If there's no such string, return ""
 *
 * LeetCode 76 (Hard)
 * Must be solved in O(n) Complexity
 * ref: https://www.pramp.com/question/wqNo9joKG6IJm67B6z34
 * ref: https://leetcode.com/problems/minimum-window-substring/description/
 * ref: https://www.youtube.com/watch?v=wlecqVnu5W8
 *
 * Solution: (Sliding window)
 * - Create int[] of size 128 (128 == num of ASCII chars)
 * - populate int[] based on char-frequency of each char in full_string
 * - Use sliding window to incrementally traverse full_string.toCharArray() and decrement each character's frequency
 * - Keep a counter (initialized to searchString.toCharArray().length ) and increment it every time the frequency array hits Zero for a character
 */
public class SmallestSubstringOfAllCharts {

    public static void main(String[] args) {
        // boolean isDisjoint = Collections.disjoint(Set.of(1, 2, 3), Set.of(9, 4, 5));;
        //System.out.println("isDisjoint " + isDisjoint);
        //NavigableMap<Integer, String> m = Collections.emptyNavigableMap();
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // 1. use a counter Map of { key: character, value: numberOfOccurences } of the substring
        //      set of chars to search for
        // 2. as you find the character, decrement the counter by 1
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String minSubstring = getMinimumCharacter(s, t);
        System.out.println("String " + s + ", sub: " + t + ", min:" + minSubstring);
    }

    static String  getMinimumCharacter(String fullString, String str){
        // a) define integer array of all ASCII characters i.e. int array of size 128
        int[] arr = new int[128];
        char[] fs_arr = fullString.toCharArray();    // fullString_arr
        char[] ts_arr = str.toCharArray();    // substring_arr

        // b) increment the counter for each character in str by 1
        for (char ch : ts_arr){
            //int index = ch - 'a';
            arr[ch]++;
        }
        // c) define the pointers
        int leftPointer = 0;
        int rightPointer = 0;

        // d) define the minLength
        int minimumLength = Integer.MAX_VALUE;

        // e) define the string/answer
        String answer = "";
        int counter = 0;

        // f) find the minimum window
        while (rightPointer < fs_arr.length){

            // EXPAND the window
            char currentChar  = fs_arr[rightPointer];
            if(--arr[currentChar] >= 0){
                counter++;
            }

            // SHRINK/MOVE the window if you satisfy the condition
            while (counter == ts_arr.length){
                int currentWindow = rightPointer - leftPointer + 1;
                if(currentWindow < minimumLength){
                    minimumLength = currentWindow;
                    answer = str.substring(leftPointer, rightPointer + 1);
                }

                char leftChar = fs_arr[leftPointer];
                if(++arr[leftChar] > 0){
                    counter--;
                }
                leftPointer++;
            }
            rightPointer++;
        }

        // return answer
        return answer;
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // 0. populate frequency map
        for(int i=0; i<t.length(); i++){
            Character c = t.charAt(i);
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // 1. declare pointers + count
        int leftPointer = 0, rightPointer = 0;
        int count = frequencyMap.size();
        int length = Double.valueOf(Double.POSITIVE_INFINITY).intValue();
        String minWindow = "";

        // 2. iterate over the long string
        while(rightPointer < s.length()){
            char letter = s.charAt(rightPointer);
            if(frequencyMap.containsKey(letter)){
                frequencyMap.put(letter, frequencyMap.get(letter) - 1);
                if(frequencyMap.get(letter) == 0){
                    count--;
                }
            }

            // move right pointer
            rightPointer++;

            while(count == 0){
                if(rightPointer - leftPointer < length){
                    length = rightPointer - leftPointer;
                    minWindow = s.substring(leftPointer, rightPointer);
                }

                char leftLetter = s.charAt(leftPointer);
                if(frequencyMap.containsKey(leftLetter)){
                    frequencyMap.put(leftLetter, frequencyMap.get(leftLetter) + 1);
                    if(frequencyMap.get(leftLetter) > 0){
                        count++;
                    }
                }

                leftPointer++;
            }
        }

        return minWindow;
    }
    // wrong
    static String getShortestUniqueSubstring(String str, char[] arr) {
        List<Character> arrCharacters  = new ArrayList<>();;
        List<Character> foundCharacters  = new ArrayList<>();

        for(char c : arr){
            arrCharacters.add(c);
        }

        int windowStart = -1;
        int windowEnd = -1;

        String toReturn = "";

        for(int i=0;
            i<str.length() && foundCharacters.size() < arr.length;
            i++){
            char c = str.charAt(i);

            if(!arrCharacters.contains(c) /*  && windowStart == -1 */){
                windowStart = i;
            }

            if(foundCharacters.contains(c)){
                foundCharacters.add(c);
                windowEnd++;
            }

            if((windowStart  - windowEnd) == arr.length){
                break;
            }
        }

        if(foundCharacters.size() < arr.length){
            toReturn = "";
        }
        else{
            for(Character c : foundCharacters){
                toReturn = toReturn + c;
            }
        }

        return toReturn;
    }
}