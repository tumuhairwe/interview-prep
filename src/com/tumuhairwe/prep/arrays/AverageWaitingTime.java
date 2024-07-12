package com.tumuhairwe.prep.arrays;

/**
 * LeetCode 1701 (medium) Average waiting tie
 * ref: https://leetcode.com/problems/average-waiting-time/description/
 * ref: https://www.youtube.com/watch?v=2fN7uIgCIBA
 */
public class AverageWaitingTime {

    public static void main(String[] args) {
        int[][] testCaseA = {
                {1,2},{2,5},{4,3}
        };
        System.out.println("Should be 5.0: " + averageWaitingTime(testCaseA));
        int[][] testCaseB = {
                {5,2}, {5,4},{10,3},{20,1}
        };
        System.out.println("Should be 3.25: " + averageWaitingTime(testCaseB));
    }
    public static double averageWaitingTime(int[][] customers){
        //0. init vars
        double timeWaiting = customers[0][1];   // set to cooking time of the 1st customer
        int previousFinishTime = customers[0][0] + customers[0][1]; // set to 1st customer's arrivalTime + cookingTime

        //1. iterate [] from 2nd customer
        for (int i = 1; i < customers.length; i++) {
            int arrivalTime = customers[i][0];
            int cookTime = customers[i][1];

            //2. set start cook time and end cook time
            int startCookTime = Math.max(arrivalTime, previousFinishTime);
            int endCookTime = startCookTime + cookTime;

            //3. update previous finish time and time waiting
            previousFinishTime = endCookTime;
            timeWaiting += endCookTime - arrivalTime;
        }

        //1.
        return timeWaiting / customers.length;
    }
}
