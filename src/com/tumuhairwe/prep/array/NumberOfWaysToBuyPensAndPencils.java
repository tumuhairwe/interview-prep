package com.tumuhairwe.prep.array;

/**
 * LeetCode 2240 (medium)
 *
 * Number of way to buys pens and pencils
 *
 * You are given an integer total indicating the amount of money you have. You are given 2 integers
 * pencilCost and penCost indicating the price of a pen and pencil respectively.
 * YOu can spend part or all of you money to buy multiple quantities (or none) of each kind.
 *
 * Return the number of distinct ways you can buy some number of pens and pencils
 *
 * ref: https://leetcode.com/problems/number-of-ways-to-buy-pens-and-pencils/description/
 */
public class NumberOfWaysToBuyPensAndPencils {

    public long waysToBuyPensPencils(int pensTotal, int penCost, int pencilCost){
        int numberOfWays = 1;
        int numberOfPensThatCanBeBought = pensTotal / penCost;
        for (int pens = 0; pens < numberOfPensThatCanBeBought; pens++) {
            numberOfWays += (pensTotal / pencilCost + 1);
            pensTotal -= penCost;
        }

        return numberOfWays;
    }

    // ref: // https://leetcode.com/problems/number-of-ways-to-buy-pens-and-pencils/solutions/4644127/pens-and-pencil-simple-math/
    //public long waysToBuyPensPencils_impl2(int pensTotal, int penCost, int pencilCost){
        // if ((penCost >= pensTotal) && (pencilCost >= pensTotal)) {
        //     return numberOfWays;
        // }

        // while (pensTotal > -1) {
        //     numberOfWays += pensTotal / pencilCost;
        //     pensTotal -= penCost;
        //     if (pensTotal > -1) numberOfWays++;
        // }

        // return numberOfWays;
    //}
}
