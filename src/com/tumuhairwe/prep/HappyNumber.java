package com.tumuhairwe.prep;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 202 (easy)
 *
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 * ref: https://leetcode.com/problems/happy-number/description/
 * ref: https://www.youtube.com/watch?v=ljz85bxOYJ0
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println("1 is " + (isHappy(1) ? "happy " : "not happy") );
        System.out.println("4 is " + (isHappy(4) ? "happy " : "not happy") );
        System.out.println("13 is " + (isHappy(13) ? "happy " : "not happy") );
        System.out.println("19 is " + (isHappy(19) ? "happy " : "not happy") );
    }
    // Set base impl: SC: O(n) -- where n
    public static boolean isHappy(int n){
        if(n == 1 || n == -1){
            return false;
        }

        Set<Integer> visited = new HashSet<>();
        while (!visited.contains(n)){
            visited.add(n);
            n = sumOfSquares(n);

            if(n == 1){
                return true;
            }
        }
        return false;
    }

    public static boolean isHappy_tortoise_and_hare(int n) {
        // base case
        if(n == 1 || n == -1){
            return true;
        }

        // fast & slow pointers
        int slow = n;
        int fast = n;

        do{
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        }while(fast != slow);

        return slow == 1;
    }

    private static int sumOfSquares(int num){
        int square = 0;
        while (num != 0){
            int remainder = num % 10;
            square = remainder * remainder;
            num = num/10;
        }
        return square;
    }
}
