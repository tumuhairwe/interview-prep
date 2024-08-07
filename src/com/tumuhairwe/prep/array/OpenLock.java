package com.tumuhairwe.prep.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots (0 to 9).
 * The wheels can rotate freely and wrap around e.g. '9' to '0' and '0' to '9'.
 * Each move consists of turning one wheel slot.
 *
 * The lock initially starts at '0000
 */
public class OpenLock {
    //TC: O(1000)
    //SC: O(1000)

    /**
     * Solution summary (BFS)
     * - Create visited set of deadEnds ... (if startingPoint is a deadEnd, return)
     * - Create deque + seed with startingPoint
     * - Create queue and seed with Pair(key=combo, val=stepCount)
     * - while !que.isEmpty() ->
     *      - pull from que
     *      - if combo/key is visited, skip
     *      - if combo/key == target, return stepCount
     *      - get all possible combo permutations from currentState
     *      - for reach possibleCombo, -> add to que if unvisited
     */
    public int openLock(String[] deadends, String target){
        //0. declare vars
        String startingPoint = "0000";
        Set<String> visit = Arrays.stream(deadends).collect(Collectors.toSet());

        if(visit.contains(startingPoint)){
            return -1;
        }

        //do BFS (entry.key=lockState i.e. permutation of combo, entry.val=num_of_moves)
        Deque<Map.Entry<String, Integer>> que = new ArrayDeque<>();
        que.offer(new AbstractMap.SimpleEntry<>(startingPoint, 0));
        while (!que.isEmpty()){
            //0. pull from queue
            Map.Entry<String, Integer> curr = que.poll();

            //1. if we've reached the target return numMoves
            if(curr.getKey().equals(target)){
                return curr.getValue();
            }

            //2. if already visited .. skip
            if(visit.contains(curr.getKey())){
                continue;
            }

            //3. get permutations of combo based on currentState
            Set<String> comboStates = getPossibleCombinations(curr.getKey());
            for (String combo : comboStates){
                if(!visit.contains(combo)){
                    visit.add(combo);
                    que.offer(new AbstractMap.SimpleEntry<>(combo, curr.getValue() + 1));
                }
            }
        }

        return -1;
    }

    public Set<String> getPossibleCombinations(String lockState){
        Set<String> comboSet = new HashSet<>();
        for (int i = 0; i < lockState.length(); i++) {
            char ch = lockState.charAt(i);
            String pre = lockState.substring(0, i);
            String post = lockState.substring(i+1);

            //mid char -> toInt-> +1 -> mod 10
            String mid = String.valueOf((Integer.parseInt(String.valueOf(ch)) + 1) % 10);
            comboSet.add(pre + mid + post);

            //mid char -> toInt-> -1 -> mod 10
            mid = String.valueOf(((Integer.parseInt(String.valueOf(ch)) - 1 ) + 10) % 10 );
            comboSet.add(pre + mid + post);
        }

        return comboSet;
    }
}
