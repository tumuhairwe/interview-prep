package com.tumuhairwe.prep.array;

/**
 * Summary: Given a matrix of m x n (rows x columns) ... determine if matrix is Toeplitz
 * i.e "every diagonal from left to bottom has the same elements"
 * ref: https://leetcode.com/problems/toeplitz-matrix/
 */
public class Toeplitz {
    public static void main(String[] args) {
        int[][] array = {{5,9,4,1},
                {2,5,9,4},
                {3,2,5,9}};

        boolean isToeplitz = isToeplitz(array);
        System.out.println(isToeplitz);
    }

    private static boolean isToeplitz(int[][] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                if(array[i-1][j-1] - array[i][j] != 0){
                    return false;
                }
            }
        }

        return true;
    }
}
