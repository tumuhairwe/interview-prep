package com.tumuhairwe.prep.linesweep;

import java.util.*;

/**
 * LeetCode 1366 (medium)
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams
 * participating in the competition
 * - The ordering of teams is decided by who received the most position-one votes.
 * If 2 or more teams are in the 1st position, we consider the 2nd position to resolve this conflict
 * - If they tie again, we continue this process until all the ties are resolved.
 * We rank them alphabetically based on their team letter
 *
 * Given an array of strings "votes", which is the votes fo all voters in the ranking system. Sort all teams
 * according to the team ranking system described above
 *
 * Return a string of all teams sorted by the ranking system
 * ref: https://leetcode.com/problems/rank-teams-by-votes/
 */
public class RankTeams {
    public String rankTeams(String[] votes) {
        //0. init vars
        Map<Character, int[]> teamVotes = new HashMap<>();
        int numCandidates = votes[0].length();

        //1. populate voteTally freqMap: (key=candidate_id, value=array_of_votes_by_position)
        for (String vote : votes){
            for (int teamIdx = 0; teamIdx < numCandidates; teamIdx++) {
                char team = vote.charAt(teamIdx);
                teamVotes.putIfAbsent(team, new int[numCandidates]);
                teamVotes.get(team)[teamIdx]++;
            }
        }

        //2. create custom comparator that sorts by votes
        Comparator<Character> orderByVoteCount = (a, b) -> {
            // compare by each teams's votes at each position
            for (int candIdx = 0; candIdx < numCandidates; candIdx++) {
                if (teamVotes.get(b)[candIdx] != teamVotes.get(b)[candIdx]){
                    int votesOfA = teamVotes.get(a)[candIdx];
                    int votesOfB = teamVotes.get(b)[candIdx];
                    return Integer.compare(votesOfB, votesOfA);
                }
            }

            // if all positions have equal votes, compare lexicographically
            return a - b;
        };

        // 3. sort candidates (teamVotes key) by custom comparator & return as string
        StringBuilder sb = new StringBuilder();
        teamVotes.keySet()
                .stream()
                .sorted(orderByVoteCount)
                .forEach(ch -> sb.append(ch));

//        List<Character> candidates = new ArrayList<>(teamVotes.keySet());
//        Collections.sort(candidates, orderByVoteCount);
//        for (Character cand : candidates){
//            sb.append(cand);
//        }

        return sb.toString();
    }
}
