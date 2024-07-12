package com.tumuhairwe.prep.strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 271 medium (encode and decode strings)
 * Design an algorithm to encode and decode a list of string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * ref: https://leetcode.com/problems/encode-and-decode-strings/editorial/?envType=problem-list-v2&envId=plakya4j
 */
public class Codec {
    char pi = '\u03c0';
    /**
     * Solution summary
     * - map: use StringBuilder to append a delimiter to each String
     * - join: Collect all the strings (str + delimiter) to a single string
     */
    public String encode(List<String> strs){
        char delimiter = pi;
        return strs.stream()
                .map(str -> new StringBuilder(str).append(delimiter).toString())
                .collect(Collectors.joining());
    }

    /**
     * Solution summary
     * - split the encoded string at each occurrence of the delimiter
     * - convert the array to list and return it
     */
    public List<String> decode(String str){
        String delimiter = "" + pi;
        //0. split the encoded string at each occurrence of the delimiter
        // use -1 as the limit param to ensure trailing empty strings are included
        String[] decodedStrings = str.split("", -1);

        //1. convert the array to list and return it
        //NB: we remove the last element because its an empty string resulting from the final character
        return Arrays.asList(decodedStrings).subList(0, decodedStrings.length -1);
    }
}
