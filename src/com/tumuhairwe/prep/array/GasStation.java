package com.tumuhairwe.prep.array;

/**
 * Given n gas stations along a circular route, where the amount of gas at the i-th station is gas[i]
 * You have a car with an unlimited gas tank & it cost  cost[i] of gas to travel from the i-th stations ot the next
 * (i+1) station.
 * You begin the journey with an empty tank at one of the gas stations
 *
 * Given 2 integer arrays gas & cost, return the starting gas station's index if you can travel around
 * the circuit once in the clockwise direction... otherwise, return -1.
 *
 *  If there exists a solution, it is guaranteed to be unique
 *
 * ref: https://www.youtube.com/watch?v=lJwbPZGo05A
 * ref: https://leetcode.com/problems/gas-station/description/
 */
public class GasStation {
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5}, cost = {3,4,5,1,2};
        int result = canCompleteCircuit(gas, cost);
        System.out.println("Output should be 3 " + result);

        int[] gas2 = {2,3,4,}, cost2 = {3,4,3};
        result =-1;
    }
    public static int canCompleteCircuit(int[] gas, int[] cost){
        int totalGas =0, totalCost = 0, result = 0, deficiet = 0;

        // determine if we have enough gas for the whole trip
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        // exit if we don't
        if(totalGas < totalCost){
            return -1;
        }

        // determine starting index
        for (int i = 0; i < gas.length; i++) {
            deficiet += gas[i] - cost[i];
            if(deficiet < 0){
                deficiet = 0;
                result = i+1;   // set to next position
            }
        }

        return result;
    }
}
