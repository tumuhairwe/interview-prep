package com.tumuhairwe.prep.arrays;

import java.util.Arrays;

/**
 * LeetCode 2410 (medium)
 *
 * You are given a 0-indexed int[] players where players[i] is the ability of the ith player
 * You are given a 0-indexed int[] trainers where trainers[j] is the capacity of the jth trainer
 *
 * The ith player can match the jth trainer if the player's ability is <= the trainer's capacity.
 * In addition,t teh ith player can be matched with at most 1 trainer and hth trainer can be matched with at most 1 player
 * ref: https://leetcode.com/problems/maximum-matching-of-players-with-trainers/description/
 */
public class MaximumMatchingOfPlayersWithTrainers {

    /**
     * TC: O(n log_n)
     * SC: O(1)
     */
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        //0. sort both arrays
        Arrays.sort(players);
        Arrays.sort(trainers);

        //1. init 2 pointers
        int pIdx = players.length - 1;
        int tIdx = trainers.length - 1;
        int matchings = 0;

        //2. traverse arrays from the end
        while (pIdx>= 0 && tIdx >= 0){
            if(trainers[tIdx] >= players[pIdx]){
                matchings++;
                tIdx--;
                pIdx--;
            }
            else if(players[tIdx] > trainers[pIdx]){
                pIdx--;
            }
        }
        return matchings;
    }
}
