package com.tumuhairwe.prep.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 36. Valid Sudoku (medium)
 *
 * ref: https://leetcode.com/problems/valid-sudoku/description/
 * ref: https://leetcode.com/problems/valid-sudoku/solutions/15472/short-simple-java-using-strings/
 */
public class IsValidSudoku {

    boolean isValidSudoku(char[][] grid){
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '.'){
                    continue;
                }
                char number = grid[i][j];
                if(!seen.add(number + " in row " + i) ||
                   !seen.add(number + " in col " + j) ||
                        !seen.add(number + " in block " + i/3 + "-" + j/3)){
                    return false;
                }
            }
        }

        return true;
    }
}
