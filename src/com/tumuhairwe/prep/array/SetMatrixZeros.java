package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * LeetCode 73 (Medium)
 * Given an m x n integer matrix m, if an element is 0, set its entire row and column to 0's.
 *
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0073-set-matrix-zeroes.java
 * ref: https://leetcode.com/problems/set-matrix-zeroes/
 * https://gist.github.com/sdpatil/686ec4308dc7814b34f711b48fac1afd
 */
public class SetMatrixZeros {
    public static void main(String[] args) {
        int[][] m = new int[][]{
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };

        int[][] x = new int[][]{
                {1,1,1},
                {1,0,1},
                {1,0,1,}
        };

        setZeroes(x);
        Arrays.stream(x).forEach(e -> System.out.println(Arrays.toString(e)));
    }

    /**
     * Solution summary
     * - Read from the input ... but Make changes to the copy -- to avoid read the changed copy
     * - 1st 2d Loop: as you iterate, put save coordinates int 2 boolean[] (for both zeroRows and zeroCols)
     * - 2nd sd loop: as you iterate, if rowSet.contains(row) || colSet.contains(col) ... mark cell 0
     * - return copy_of_matrix
     */
    public static void setZeroes(int[][] matrix) {
        //0. declare vars
        boolean[] zeroRows = new boolean[matrix.length];
        boolean[] zeroCols = new boolean[matrix[0].length];

        Arrays.fill(zeroRows, false);
        Arrays.fill(zeroCols, false);

        //1. mark rows & cols to be zeroed out
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }

        //2. zero out those rows & cols
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(zeroRows[i] == true || zeroCols[j] == true){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
