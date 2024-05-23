package com.tumuhairwe.prep.array;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 771 (easy)
 *
 * Given a string Jewels representing stones that are jewels
 * and stones representing stones ... the task is to find out how many
 * stones we have are letters
 * Letters are case-sensitive i.e. a != A
 *
 * Solution Summary
 * - Collect all chars of jewels to a Set
 * - iterate over char[] of stones string
 * - for each char_in_stone that is in set_of_jewels ... increment counter
 * - return counter;
 *
 * ref: https://leetcode.com/problems/jewels-and-stones/description/
 */
public class JewelsAndStones {
    public static void main(String[] args) {
        String stones = "aAAbbbb";
        String jewels = "aA";

        int numJewels = numJewelsInStones(jewels, stones);
        System.out.println("There are " + numJewels + " jewels in " + stones);
    }
    public static int numJewelsInStones(String jewels, String stones){
        Set<Character> setOfJewels = new HashSet<>();
        for (char c : jewels.toCharArray()){
            setOfJewels.add(c);
        }

        int num_jewels = 0;
        for (char c : stones.toCharArray()){
            if(setOfJewels.contains(Character.valueOf(c))){
                num_jewels++;
            }
        }

        return num_jewels;
    }
}
