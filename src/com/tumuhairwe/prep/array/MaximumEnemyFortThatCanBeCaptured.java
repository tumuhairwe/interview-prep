package com.tumuhairwe.prep.array;

/**
 * LeetCode 2511 (easy) Maximum Enemy Forts That Can Be Captured
 *
 *  You are given a 0-indexed integer array forts of length n representing the positions of several forts. forts[i] can be -1, 0, or 1 where:
 *
 * -1 represents there is no fort at the ith position.
 * 0 indicates there is an enemy fort at the ith position.
 * 1 indicates the fort at the ith the position is under your command.
 * Now you have decided to move your army from one of your forts at position i to an empty position j such that:
 *
 * 0 <= i, j <= n - 1
 * The army travels over enemy forts only. Formally, for all k where min(i,j) < k < max(i,j), forts[k] == 0.
 * While moving the army, all the enemy forts that come in the way are captured.
 *
 * Return the maximum number of enemy forts that can be captured. In case it is impossible to move your army, or you do not have any fort under your command, return 0.
 *
 * ref: https://leetcode.com/problems/maximum-enemy-forts-that-can-be-captured/description/
 * ref: https://www.youtube.com/watch?v=fsFM-KE-vaY
 */
public class MaximumEnemyFortThatCanBeCaptured {

    public static void main(String[] args) {
        int[] arr = {1,0,0,-1,0,0,0,0,1};
        System.out.println("Should be 4: " + captureFort(arr));

        arr = new int[]{0,0,1,-1};
        System.out.println("Should be 0: " + captureFort(arr));
    }

    // -1 represents there is no fort at the ith position.
    // 0 indicates there is an enemy fort at the ith position.
    // 1 indicates the fort at the ith the position is under your command.

    /**
     * Goal: Find longest continuous ZERO between 1 and -1
     * - iterate thru the forts[] ...
     *      - if ENEMY_FORT , -> increment counter
     *      - if its MY_COMMAND || NO_FORT && has been previously set ->
     *          calc maxCapturedForts &
     *          reset current to 0
     *          set prev = fort
     *      - if its MY_COMMAND || NO_FORT && has NOT been previously set ->
     *          reset counter to 0
     *      - else reset counter to 0 and set previousState to fortState
     */
    public static int captureFort(int[] forts){
        int ENEMY_FORT = 0;
        int NO_FORT = -1;
        int MY_COMMAND = 1;

        int UNSET = -2;
        int previousState = UNSET;
        int currentCapturedForts = 0;
        int maxCapturedForts = 0;

        for (int fortState : forts){
            if((fortState == MY_COMMAND || fortState == NO_FORT) && previousState == fortState){
                currentCapturedForts = 0;   // should run only the 1st time
            }
            else if((fortState == MY_COMMAND || fortState == NO_FORT) && previousState != UNSET){
                maxCapturedForts = Math.max(maxCapturedForts, currentCapturedForts);
                currentCapturedForts = 0;
                previousState = fortState;
            }
            else if(fortState == ENEMY_FORT){
                currentCapturedForts++;
            }
            else{
                previousState = fortState;
                currentCapturedForts = 0;
            }
        }

        return maxCapturedForts;
    }
}
