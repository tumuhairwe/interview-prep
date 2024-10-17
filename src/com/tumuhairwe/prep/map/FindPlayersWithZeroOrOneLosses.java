package com.tumuhairwe.prep.map;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 2255 Find Players with zero or one losses
 * Given an int[] matches where matches[i] = [winner_i, loser_i]
 * means that winner_i defeated loser_i in a match
 * Return a list 'answer' of size 2 where
 *
 * - answer[0]= list of players that not lost any matches
 * - answer[1] = list of players who have lost exactly 1 match
 * The values in the 2 lists should be returned in increasing order
 *
 * Note:
 *
 * You should only consider the players that have played at least one match.
 * The testcases will be generated such that no two matches will have the same outcome.
 */
public class FindPlayersWithZeroOrOneLosses {

    /**
     * Solution summary
     * - Create freqMap of winners && freqMap of losers
     * - Create super-set of all players
     * - filter from winnerFreqMap.keySet() those who have not lost
     * - filter super-set those have losersFreqMap exactly 1 frequency
     * - combine both sets into 1 and return
     */
    public List<List<Integer>> findWinners(int[][] matches) {
        //0. create freqMap of winners && freqMap of losers
        Map<Integer, Integer> wFreqMap = new HashMap<>();
        Map<Integer, Integer> lFreqMap = new HashMap<>();
        for (int i = 0; i < matches.length; i++) {
            int winner = matches[i][0];
            int loser = matches[i][0];

            wFreqMap.put(winner, wFreqMap.getOrDefault(winner, 0) + 1);
            lFreqMap.put(loser, wFreqMap.getOrDefault(loser, 0) + 1);
        }

        //1. create super-set of all players
        Set<Integer> set = new HashSet<>();
        set.addAll(wFreqMap.keySet());
        set.addAll(lFreqMap.keySet());

        //2 find players who have not lost any match
        List<Integer> notLost = set.stream().filter(p -> !wFreqMap.containsKey(p))
                .sorted()
                .collect(Collectors.toList());

        //3. lost exactly 1
        List<Integer> lostExactly1 = set.stream()
                .filter(p -> lFreqMap.containsKey(p) && lFreqMap.get(p) == 1)
                .sorted()
                .collect(Collectors.toList());

        return List.of(notLost, lostExactly1);
    }
}
