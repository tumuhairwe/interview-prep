package com.tumuhairwe.prep.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 2405 (medium)
 *
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
 *
 * Return the minimum number of substrings in such a partition.
 *
 * Note that each character should belong to exactly one substring in a partition.
 */
public class OptimalPartitionOfString {

    public static void main(String[] args) {
        String s = "abacaba";
        System.out.println("Should be 4 -> " + partitionString(s));
        s = "ssssss";
        System.out.println("Should be 6 -> " + partitionString(s));
    }
    public static int partitionString(String s){
        Set<Character> setOfUniqueChars = new HashSet<>();
        int numPartitions = 0;
        for (char c : s.toCharArray()){
            if(setOfUniqueChars.contains(c)){
                numPartitions++;
                setOfUniqueChars.clear();
            }

            setOfUniqueChars.add(c);
        }

        numPartitions++;
        return numPartitions;
    }
}
