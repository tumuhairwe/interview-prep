package com.tumuhairwe.prep.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 3218 (medium)
 * There is a m by n cake that needs to be cust into 1 x 1 pieces.
 * You are given integers m and n & 2 arrays
 * - horizontalCut (size = m -1) .. where h_cut[i] represents the cost to cut along the horizontal line i
 * - verticalCut (size = n - 1) .. where v_cut[i] represents the cost to cust along th vertical line
 *
 * In on operation, you can choose any piece of cake that is not yet a 1 x 1 square and perform the following cuts
 * 1. cut along the horizontal line i (at a cost of h_cut[i])
 * 2. cut along the vertical line i (at a cost of h_cut[i])
 *
 * After the cut, the piece of cake is divided into 2 distinct pieces
 * The cost of a cut depends only on a the initial cost of the line and does not change
 * Return the minimum total cost to cut the entire cake into 1 x 1 pieces
 */
public class MinimumCostForCuttingCakeI {

    /**
     * Solution summary
     * - create a pq of int[] that sorts by cost
     * - seed pq with horizontalCuts (int[]{ cost, 'H' }
     * - seed pq with verticalCuts (int[]{ cost, 'V' }
     * - init vars (tatalCost_toReturn = 0, numHorizontalPieces = 1, numVerticalPieces = 1)
     * - drain/empty pq & update totalCost (both H and V pieces)
     * - return totalCost
     */
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        //0 create pq that sorts by cost
        Comparator<int[]> comp = (int[] x, int[] y) -> x[0] * y[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(comp);

        //1. seed pq
        for (int hCut : horizontalCut){
            pq.add(new int[]{ hCut, 'H'});
        }
        for (int vCut : verticalCut){
            pq.add(new int[]{ vCut, 'V'});
        }

        //init vars
        long totalCost = 0;
        int countHPieces = 1;
        int countVPieces = 1;

        //drain pq
        while (!pq.isEmpty()){
            int[] piece = pq.poll();
            int cost = piece[0];

            if(piece[1] == 'H'){
                countHPieces++;
                totalCost += (long) cost * countVPieces;
            }
            else if(piece[1] == 'V'){
                countVPieces++;
                totalCost += (long) cost * countHPieces;
            }
        }

        return (int) totalCost;
    }
}
