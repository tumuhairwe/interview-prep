package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCpode 72 (medium)
 * Given 2 strings, word1 and word2, return the minimum numbe of operations required to convert
 * word1 to word2
 *
 * You are permitted 2 operations on a word
 * - Insert a character
 * - Delete a character
 * - Replace a character
 */
public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }
    public static int minDistance(String word1, String word2) {
        return minDistanceRecursive(word1, word2, word1.length(), word2.length());
    }

    private static int minDistanceRecursive(String word1, String word2, int word1Index, int word2Index) {
        //0. base case: if we've reached the end of either, return the length of the other
        if(word1Index == 0){
            return word2Index;
        }
        else if(word2Index == 0){
            return word1Index;
        }

        //1. if the chars are the same, recurse-with index - 1
        if(word1.charAt(word1Index - 1) == word2.charAt(word2Index - 1)){
            return minDistanceRecursive(word1, word2, word1Index - 1, word2Index - 1);
        }
        else{
            //2. calculate the min of insert/delete and replace
            int insertOperationCost = minDistanceRecursive(word1, word2, word1Index, word2Index - 1);
            int deleteOperationCost = minDistanceRecursive(word1, word2, word1Index - 1, word2Index);
            int replaceOperationCost = minDistanceRecursive(word1, word2, word1Index - 1, word2Index - 1);

            int minOfInsertOrDelete = Math.min(insertOperationCost, deleteOperationCost);

            //return the lesser of the 3 operations
            return Math.min(minOfInsertOrDelete, replaceOperationCost) + 1;
        }
    }
}
