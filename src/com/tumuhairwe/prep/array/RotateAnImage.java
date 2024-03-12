package com.tumuhairwe.prep.array;

/**
 * Given a n by n 2D Array matrix ... you must rotate it IN-PLACE
 * i.e. rotate the image by 90 degrees clockwise
 *
 * ref: https://leetcode.com/problems/rotate-image/description/
 * solution: https://www.youtube.com/watch?v=SA867FvqHrM&list=TLPQMDMwMzIwMjSDZX0JjrUR-g&index=7
 *
 * TimeConstant time: O(M x n) == where m = rows and n = columns
 * Linear wrt to rtoating a 2 D array
 */
public class RotateAnImage {
    public static void main(String[] args) {
        // n by n == same number of rows and columns
        int [][] matrix = {new int[]{1,2,3}, new int[]{4,5,6}, new int[]{7,8,9}};

        System.out.println("BEFORE");
        printMatrixRows(matrix);


        // swap the columns
        // a) find middle
        // b) swap the positions of the rows
        // c) swap the positions of the rows
        //   - have 2 pointers
        //   - have them move towards the middle
        rotatePerfectSquare(matrix);
        System.out.println("AFTER");

        rotatePerfectSquareColumns(matrix);

    }

    private static void printMatrixRows(int [][] matrix) {
        int N = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < N/2; j++) {
                System.out.println(i + " ");
            }
            System.out.println("");
        }
    }
    private static void printMatrixColumns(int [][] matrix) {
        // only
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(i + " ");
            }
            System.out.println("");
        }
    }

    // e.g.
    // Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    //Output: [[7,4,1],[8,5,2],[9,6,3]]
    static void rotatePerfectSquare(int[][] matrix){
        int N = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < N; j++) {
                // this will do a diagonal swap
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void rotatePerfectSquareColumns(int[][] matrix) {
        int N = matrix.length;;
        int middle = N / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < middle; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][N -1 -j];
                matrix[i][N -1 -j] = temp;
            }
        }
    }
}
