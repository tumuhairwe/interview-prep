package com.tumuhairwe.prep.strings;

/**
 * LeetCode 344. Reverse String
 *
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * ref: https://leetcode.com/problems/reverse-string/description/
 */
public class ReverseAString {
    public void reverseString(char[] s){
        //0. base case
        if(s.length ==0){
            return;
        }

        int p1 = 0;
        int p2 = s.length - 1;

        while (p1 < p2){
            boolean isOddNumbered = s.length % 2 == 1;
            if(isOddNumbered && (p1 - p2 == 1)){
                return;
            }

            swap(p1, p2, s);
            p1++;
            p2--;
        }
    }

    void swap(int i, int j, char[] arr){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
