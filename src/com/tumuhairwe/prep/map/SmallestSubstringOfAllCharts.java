package com.tumuhairwe.prep.map;

import java.util.*;

/**
 * Given 2 strings S and T, return the minimum window substring of S such that every character in T
 * (including duplicates) is included ind the same window.
 * If there's no such string, return ""
 *
 * LeetCode 76 (Hard)
 * Must be solved in O(n) Complexity
 * ref: https://www.pramp.com/question/wqNo9joKG6IJm67B6z34
 *
 * Solution: (Sliding window)
 * - Create int[]128 (128 == num of ASCII chars)
 * - populate int[] based on char-frequency of each char in full_string
 * - Use sliding window to incrementally traverse full_string.toCharArray() and decrement each character's frequency
 * - Keep a counter (initialized to searchString.toCharArray().length ) and increment it every time the frequency array hits Zero for a character
 */
public class SmallestSubstringOfAllCharts {

    public static void main(String[] args) {
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
        for (char cur : ts_arr){
            arr[cur]++;
        }
        // c) define the pointers
        int leftPointer = 0;
        int rightPointer = 0;

        // d) define the minLength
        int minimumLength = Integer.MAX_VALUE;

        // e) define the string/answqer
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