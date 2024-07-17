package com.tumuhairwe.prep.graphs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 1658 (medium)
 *
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 *
 * The bug jumps according to the following rules:
 *
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 *
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i],
 * and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home.
 * If there is no possible sequence of jumps that lands the bug on position x, return -1.
 */
public class JumpsToReachHome {
    class Jump{
        int position;
        boolean forward;
        public Jump(int pos, boolean fwd){
            this.forward = fwd;
            this.position = pos;
        }
    }

    /**
     * Solution summary; (bidirectional BFS)
     * - Create and see visited/forbidden position (with flag for backwards/forward)
     * - seed BFS que with initial starting point (position=0, forward=true);
     * - while !que.isEmpty()
     *      - pull from que
     *      - if curr.position == target ... return numSteps
     *      - calc 1 jump forward position
     *          - if eligible (unforbidden && <= furthestPosition)
     *          - add to que
     *          - add to visited/forbidden set
     *     - calc 1 jump backward position
     *           - if eligible (unforbidden && <= furthestPosition)
     *           - add to que
     *           - add to visited/forbidden set
     *     - increment numSteps
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x){
        int furthestPosition = x + a + b;

        //0. create visited/forbidden positions
        Set<Jump> forbiddenPositions = new HashSet<>();
        for(int badPosition : forbidden){
            forbiddenPositions.add(new Jump(badPosition, true));    // can't jump forward
            forbiddenPositions.add(new Jump(badPosition, false));    // can't jump backward
        }

        //1. start BFS: declare deque
        Queue<Jump> que = new ArrayDeque<>();
        que.offer(new Jump(0, true));
        int numSteps = 0;

        while (!que.isEmpty()){
            int qDepth = que.size();

            for (int i = 0; i < qDepth; i++) {
                Jump curr = que.poll();

                if(curr.position == x){
                    return numSteps;
                }

                //3. calc forward position and add to que
                Jump forward = new Jump(curr.position + a, true);
                if(forward.position <= furthestPosition && !forbiddenPositions.contains(forward)){
                    que.offer(forward);
                    forbiddenPositions.add(forward);
                }

                //4. calc forward position and add to que
                Jump backward = new Jump(curr.position - b, false);
                if(backward.position >= 0 && !forbiddenPositions.contains(backward) && curr.forward){
                    que.offer(backward);
                    forbiddenPositions.add(backward);
                }
            }

            numSteps++;
        }

        return -1;
    }
}
