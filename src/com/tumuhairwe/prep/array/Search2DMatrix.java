package com.tumuhairwe.prep.array;

/**
 * LeetCode 74 Medium
 * Given a 2D array, and a target, search the array for that target number
 * assume that
 * - Each row is sorted in non-decreasing order.
 * -The first integer of each row is greater than the last integer of the previous row.
 *
 * Given an integer target, return true if target is in matrix or false otherwise.
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * ref: https://www.youtube.com/watch?v=DB_cfI4wGbE
 */
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

    /**
     * Solution Summary (binary searching a 2D array)
     * - Initialize 2 pointers (vertical & horizontal) to row = matrix.length and cols=matrix[0].length
     * - Iterate the 2D array until left <= right
     *  - Define mid (like binary search)
     *  - set rowNum = mid / cols
     *  - set colNum = mid % cols
     *  - define value = matrix[rowNum][colNum]
     *  - when target == value -> return true
     *  - else if target > value -> (right + mid - 1), else if value < target -> (left = mid + 1)
     */
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

            int value = matrix[rowNumber][colNumber];
            if(target == value){
                return true;
            }
            else if(value > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return false;   // we never found the target in the matrix
    }
}
