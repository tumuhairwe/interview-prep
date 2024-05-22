package com.tumuhairwe.prep.pramo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a 2D array (matrix) inputMatrix of integers, create a function spiralCopy that copies inputMatrix’s values into a 1D array in a spiral order, clockwise. Your function then should return that array. Analyze the time and space complexities of your solution.
 *
 * Example:
 *
 * input:  inputMatrix  = [ [1,    2,   3,  4,    5],
 *                          [6,    7,   8,  9,   10],
 *                          [11,  12,  13,  14,  15],
 *                          [16,  17,  18,  19,  20] ]
 *
 * output: [1, 2, 3, 4, 5, 10, 15, 20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12]
 * See the illustration below to understand better what a clockwise spiral order looks like.
 */
public class MatrixSpiralCopy {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<Integer> result = spiralCopy(matrix);

        matrix = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        result = spiralCopy(matrix);
        System.out.println(result);
    }
    static List<Integer> spiralCopy(int[][] matrix) {
        // 0. define vars
        List<Integer> output = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[0].length - 1;

        while (left <= right && top <= bottom){
            // 1. process top row: go left-to-right -> get every value in top row
            for (int i = left; i <= right; i++) {
                output.add(matrix[top][i]);
            }
            top++;

            // 2. process right column: go top to bottom -> get every value in the left most column
            for (int i = top; i <= bottom; i++) {
                output.add(matrix[i][right]);;
            }
            right--;

            // 3. set break condition
            if(!(left <= right) && !(bottom < top)){
                break;
            }

            // 4. process bottom row: get every i in the bottom process
            for (int i = right; i >= left; i--) {
                output.add(matrix[bottom][i]);
            }
            bottom--;

            // 5 process left column: get every i in the left most column
            for (int i = bottom; i >= top; i--) {
                output.add(matrix[i][left]);
            }
            left++;
        }
        return output;
    }
}
