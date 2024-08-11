package com.tumuhairwe.prep.arrays;

/**
 * LeetCode 1010 (medium)
 * Given a list of songs where the i-th song has a duration of time[i]
 *
 * return the number of pairs of songs for which their total duration in seconds is divisible by 60.
 * Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 */
public class PairsOfSongsWithDurationDivisibleBy60 {

    public static void main(String[] args) {
        int[] arr = {418,204,77,278,239,457,284,263,372,279,476,416,360,18};
        System.out.println("1 ->" + numPairsDivisibleBy60(arr));

        arr = new int[] {60, 60, 60};
        System.out.println("3 ->" + numPairsDivisibleBy60(arr));

        arr = new int[]{30,20,150,100,40};
        System.out.println("3 ->" + numPairsDivisibleBy60(arr));
    }
    public static int numPairsDivisibleBy60(int[] time) {
        //0. init array to keep track of remainders. index 0 == divisible by 0
        int[] remainders = new int[60];

        int count = 0;
        for(int t : time){
            int remainder = t % 60;

            if(remainder == 0){
                count += remainders[0];
            }
            else {
                // number has a complement that is divisible e.g. [40, 60]
                int complementRemainder = 60 - remainder;
                count += remainders[complementRemainder];
            }

            // update remainders array
            remainders[remainder]++;
        }

        return count;
    }
}
