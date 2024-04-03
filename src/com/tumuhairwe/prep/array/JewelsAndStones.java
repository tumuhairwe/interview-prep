package com.tumuhairwe.prep.array;

import java.util.Comparator;
import java.util.Map;

/**
 * Given a string Jewels representing stones that are jewels
 * and stones representing stones ... the task is to find out how many
 * stones we have are letters
 * Letters are case-sensitive i.e. a != A
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
        int num_jewels = 0;

        for (int i = 0; i < stones.length(); i++) {
            if(jewels.indexOf(stones.charAt(i)) != -1){
                num_jewels++;
                //Map.Entry.comparingByValue(Comparator.reverseOrder());
            }
        }
        return num_jewels;
    }
}
