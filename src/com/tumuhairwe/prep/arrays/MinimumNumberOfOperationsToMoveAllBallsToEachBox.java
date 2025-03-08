package com.tumuhairwe.prep.arrays;

/**
 * LeetCode 1769 (medium) (similar to LeetCode 238)
 * Given n boxes & binary string of length n, where boxes[i] = '0' if the i-th box is empty and '1' if it contains a ball
 * In 1 operation, you can move 1 ball from a box to an adjacent box
 *
 * In 1 operation, you can move 1 ball from a box to an adjacent box. Box i is the adjacent box if Math.abs(i - j) == 1
 * Note that after doing so, there may be more than 1 in some boxes.
 *
 * Return an array answer of size N, where answer[i] = minimum_number_of_operations needed to move all the bolls to the i-th box
 * ref: https://www.youtube.com/watch?v=ZmH3gHiIqfI
 * ref: https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/description/
 * ref: https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/solutions/1075474/c-java-o-n-ltr-rtl/
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public static void main(String[] args) {

    }
    public int[] minOperations(String boxes){
        //0. create vars
        int ballsToLeft = 0, movesToLeft = 0;
        int ballsToRight = 0, movesToRight = 0;

        int n = boxes.length();
        int[] answer = new int[n];

        //1. iterate [] left-to-right & calculate movesToLeft & ballsToLeft
        for (int i = 0; i < n; i++) {
            answer[i] += movesToLeft;
            ballsToLeft += Integer.parseInt(String.valueOf(boxes.charAt(i))); // either 1 or 0;
            movesToLeft += ballsToLeft;
        }

        //2. iterate[] right-to-left & movesToRight & ballsToRight
        for (int i = n-1; i >=0; i--) {
            answer[i] += movesToRight;
            ballsToRight += Integer.parseInt(String.valueOf(boxes.charAt(i))); // either 1 or 0;
            movesToRight =+ ballsToRight;
        }

        return answer;
    }
}
