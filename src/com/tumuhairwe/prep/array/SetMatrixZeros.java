package com.tumuhairwe.prep.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 73 (Medium)
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0073-set-matrix-zeroes.java
 * ref: https://leetcode.com/problems/set-matrix-zeroes/
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
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        Set<Integer> colsToFill = new HashSet<>();
        Set<Integer> rowsToFill = new HashSet<>();
        int[][] copyOfMatrix = Arrays.copyOf(matrix, matrix.length );

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                if(matrix[i][j] == 0){
                    // fill the entire row
                    //Arrays.fill(matrix[i], 0);
                    if(!rowsToFill.contains(i)){
                        rowsToFill.add(i);
                    }
                    if(!colsToFill.contains(j)){
                        colsToFill.add(j);
                    }
                }
            }
        }

        // fill the copy
        for(int row_index = 0; row_index < matrix.length; row_index++){
            for(int col_index = 0; col_index < matrix[row_index].length; col_index++){
                if(rowsToFill.contains(row_index) || colsToFill.contains(col_index)){
                    //matrix[row_index][col_index] = 0;
                    copyOfMatrix[row_index][col_index] = 0;
                }
            }
        }

        // fill the non-zero fields with 1s
//        for (int i = 0; i < copyOfMatrix.length; i++) {
//            for (int j = 0; j < copyOfMatrix[0].length; j++) {
//                if(copyOfMatrix[i][j] != 0){
//                    copyOfMatrix[i][j] = 1;
//                }
//            }
//        }
    }
}
