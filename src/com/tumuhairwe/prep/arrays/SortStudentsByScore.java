package com.tumuhairwe.prep.arrays;

import java.util.*;

/**
 * LeetCode 2545 (medium)
 *
 * There is a class with m students and n exams. You are given a 0-indexed m x n integer matrix score,
 * where each row represents one student and score[i][j] denotes the score the ith student got in the jth exam.
 * The matrix score contains distinct integers only.
 *
 * You are also given an integer k.
 * Sort the students (i.e., the rows of the matrix) by their scores in the kth (0-indexed) exam from the highest to the lowest.
 *
 * Return the matrix after sorting it.
 */
public class SortStudentsByScore {

    public static void main(String[] args) {
        int[][] arr = {
                {10,6,9,1} , {7,5,11,2} , {4,8,3,15}
        };
        Arrays.stream(sortTheStudents(arr, 2)).forEach(ar -> System.out.println(Arrays.toString(ar)));
    }
    /**
     * Solution summary
     * - Use TreeMap to store scores sorted (key= scores_of_k_exam)
     */
    public static int[][] sortTheStudents(int[][] scores, int k) {
        //0. use key map to sort by key ()
        Map<Integer,int[]> map = new TreeMap<>();  // scores_of_k_exam, all_other_scores
        for (int studentId = 0; studentId < scores.length; studentId++) {
            map.put(scores[studentId][k], scores[studentId]);
        }

        //1. collect the values and reverse the order (req: descending order)
        // List<int[]> list = map.values().stream().collect(Collectors.toList());
        List<int[]> list = new ArrayList<>(map.values());
        Collections.reverse(list);

        // return as 2D array
        return list.toArray(new int[0][0]);
    }
}
