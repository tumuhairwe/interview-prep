package com.tumuhairwe.prep.array;

import java.util.Random;

public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},{10,11,16,20}, {23,30,34,60}
        };
        int target = 3;
        boolean found = searchMatrix(matrix, target);
        System.out.println("Was found - " + found);

        matrix = new int[][]{
                {1, 3}
        };
        target = 3;
        found = searchMatrix(matrix, target);
        System.out.println("Was found - " + found);
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0){
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = (rows * cols) - 1;   // matrix[0].length - 1
        while(left <= right){
            int mid = left + (right - left) / 2;
            int rowNumber = mid / cols;
            int colNumber = mid % cols;

            if(target == matrix[rowNumber][colNumber]){
                return true;
            }
            else if(matrix[rowNumber][colNumber] > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return false;   // we never found the target in the matrix
    }
}
