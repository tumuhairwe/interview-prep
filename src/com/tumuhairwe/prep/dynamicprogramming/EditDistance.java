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
    public static int minDistance(String word1, String word2){
        //0. base case: if either is empty, ... return length of the others
        if(word1.length() == 0){
            return word2.length();
        }
        else if(word2.length() == 0){
            return word1.length();
        }

        //1. init 2D array to store cost of converting substring from word1 to word2
        int[][] costMatrix = new int[word1.length()][word2.length()];

        //2. determine the starting point for filling the costMatrix
        int lastRow = word1.length() - 1;
        int lastCol = word2.length() - 1;
        boolean isMatch = true;
        if(word1.charAt(lastRow) != word2.charAt(lastCol)){
            costMatrix[lastRow][lastCol] = 1;
            isMatch = false;
        }

        //4a: fill lastCost based on whether the chars match or not
        // iterat lastCost backwards
        for (int j = lastCol - 1; j >=0; j--) {
            if(word1.charAt(lastRow) == word2.charAt(j)){
                isMatch = true;
            }

            costMatrix[lastRow][j] = lastCol - j + 1;
            if(isMatch){
                costMatrix[lastRow][j]--;   // set value to 0 (i.e. isMatch -> nothing to be done)
            }
        }

        //5. fill the remainder of 2D array going backwards
        // a) if chars match, take the diagonal value (costMatrix[i+1][j+1]
        // b) if chas don't match, take the minimum of the 3 possible operaions (insert, delete or replace) and add 1;
        for (int i = lastRow - 1; i >= 0; i--) {
            for (int j = lastCol - 1; j >= 0 ; j--) {
                if(word1.charAt(lastRow) == word2.charAt(lastCol)){
                    costMatrix[i][j] = costMatrix[i+1][j+1];
                }
                else {
                    int min = Math.min(costMatrix[i + 1][j], costMatrix[i][j + 1]);
                    min = Math.min(min, costMatrix[i + 1][j + 1]);
                    costMatrix[i][j] = 1 + min;   // min = costOfChange + 1
                }
            }
        }
        return costMatrix[0][0]; //top-left gives the minumum number of operations to convert word1 to word1 (e.g. horse -> ros)
    }
}
