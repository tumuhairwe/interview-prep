package com.tumuhairwe.prep.strings;

import java.util.Arrays;

/**
 * LeetCode 443 (medium)
 *
 * Given an array of characters chars, compress it using the following algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of the array.
 *
 * You must write an algorithm that uses only constant extra space.
 *
 * ref: https://leetcode.com/problems/string-compression/description/?envType=company&envId=crowdstrike&favoriteSlug=crowdstrike-three-months
 */
public class StringCompression {

    public static void main(String[] args) {
        char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        int length = compress(chars);
        System.out.println("Should be '4' " + length);
        System.out.println("Should startWith 'ab12' " + Arrays.toString(chars));
    }
    public static int compress(char[] chars){
        // base case
        if(chars.length == 0){
            return 0;
        }

        //0. create 2 pointers, initialize to 0
        int idx = 0, charsIdx = 0;
        while (idx < chars.length){
            int groupLength = 1;
            // 1. increment counter as log as chars are the same we  haven't reached the end
            while (idx + groupLength < chars.length
                    && chars[idx + groupLength] == chars[idx]){
                groupLength++;
            }

            //2. write char to char[]
            chars[charsIdx++] = chars[idx];

            //3. transcribe the number into char[]
            if(groupLength > 1){
                char[] lengthOfChars = Integer.toString(groupLength).toCharArray();
                for(char c : lengthOfChars){
                    chars[charsIdx++] = c;
                }
            }

            //4. move ids forward by groupLength
            idx += groupLength;
        }

        return charsIdx;
    }
}
