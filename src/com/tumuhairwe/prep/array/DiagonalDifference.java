package com.tumuhairwe.prep.array;

import java.util.List;

/**
 * HackerRank:
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 *
 * For example, the square matrix  is shown below:
 *
 * 1 2 3
 * 4 5 6
 * 9 8 9
 * The left-to-right diagonal = . The right to left diagonal = . Their absolute difference is .
 *
 * Function description
 *
 * Complete the  function in the editor below.
 *
 * diagonalDifference takes the following parameter:
 *
 * int arr[n][m]: an array of integers
 * Return
 *
 * int: the absolute diagonal difference
 *
 * ref: https://www.youtube.com/watch?v=ef0ts1cVWYc
 * ref: https://www.hackerrank.com/challenges/diagonal-difference/problem?isFullScreen=true
 * ref: https://github.com/Anmol53/Hackerrank-Problem-Solving/blob/master/Warmup/Diagonal%20Difference/Diagonal%20Difference.java
 */
public class DiagonalDifference {

    static int diagonalDiffence_2(int[][] arr){
        int left_to_right =0;
        int right_to_left = 0;

        int rows = arr.length;
        int cols = arr[0].length;

        int i = 0;
        int j = 0;
        int k = 0;
        int l = arr.length - 1;

        while (i < rows && j < cols && k < rows && i >=0){
            left_to_right += arr[i][j];
            right_to_left += arr[k][l];

            i++;
            j++;
            k++;
            l--;
        }

        return Math.abs(left_to_right - right_to_left);
    }
    static int diagonalDifference(List<List<Integer>> arr){
        int left_to_right = 0;
        int right_to_left = 0;
        int[][] two_d_array = arr.stream()
                .map(u -> u.stream().mapToInt(i -> i ).toArray())
                .toArray(int[][]::new);

        for (int i = 0; i < two_d_array.length; i++) {
            for (int j = 0; j < two_d_array[0].length; j++) {
                if(i == j){
                    left_to_right += two_d_array[i][j];
                }
                if (i + j == two_d_array.length - 1) {
                    right_to_left += two_d_array[i][j];
                }
            }
        }

        return (left_to_right > right_to_left) ? left_to_right-right_to_left: right_to_left-left_to_right;
    }
}
