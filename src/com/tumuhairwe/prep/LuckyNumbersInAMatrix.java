package com.tumuhairwe.prep;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1380 (easy)
 *
 * Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
 *
 * A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
 *
 * ref: https://www.youtube.com/watch?v=ceuQgACqr78
 * ref: https://leetcode.com/problems/lucky-numbers-in-a-matrix/
 */
public class LuckyNumbersInAMatrix {

    /**
     * Solution summary
     * - Find the max in every row
     *      - for each row, init min to 0th element
     *      - loop over entire col and update min
     *      - add min to collection
     * - Find the max in every col
     *      - for each column, init max to 0th element
     *      - loop over entire row and update max
     *      - add max to collection
     * - Find intersection
     *      - x exist in both minInEachRow and maxInEachCol
     * - return intersection
     */
    public List<Integer> luckyNumbers (int[][] matrix) {
        //0. process rows
        List<Integer> minInEachRow = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int currentMax = matrix[i][0];
            for (int j = 1; j < matrix[0].length; j++) {
                currentMax = Math.min(currentMax, matrix[i][j]);
                minInEachRow.add(currentMax);
            }
        }

        //1. process cols
        List<Integer> maxInEachCol = new ArrayList<>();
        for (int i = 0; i < matrix[0].length; i++) {
            int currentMin = matrix[0][i];
            for (int j = 1; j < matrix.length; j++) {
                currentMin = Math.max(currentMin, matrix[j][i]);
            }
        }

        //2. collect intersection
        List<Integer> intersection = new ArrayList<>();
        for (int i = 0; i < minInEachRow.size(); i++) {
            if(maxInEachCol.contains(maxInEachCol.get(i))){
                intersection.add(maxInEachCol.get(i));
            }
        }
        return  intersection;
    }
}
