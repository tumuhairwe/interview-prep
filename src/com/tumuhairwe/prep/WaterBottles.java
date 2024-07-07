package com.tumuhairwe.prep;

/**
 * LeetCode 1518 (easy)
 * There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water bottles from the market with one full water bottle.
 *
 * The operation of drinking a full water bottle turns it into an empty bottle.
 *
 * Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.
 *
 * ref: https://leetcode.com/problems/water-bottles/description/
 * ref: https://www.youtube.com/watch?v=V4d6xym5efE
 * ref: https://leetcode.com/problems/water-bottles/solutions/5431087/easy-beats-100-java-python-c/
 */
public class WaterBottles {
    /**
     * Solution summary
     * - start by drinking all the water bottles (i.e. bottlesDrank = numBottles)
     * - while number_of_bottle exceeds the exchange_range
     *      - calc newBottle & remainingBottles for each iteration
     *      - add newBottles to bottlesDrank
     *      - number_of_bottles = newBottle + remainingBottles
     * - return bottlesDrank
     */
    static public int numWaterBottles(int numBottles, int numExchange) {
        // return max number of bottles you can drink
        // exchange range: 3 empty water bottle = 1 full water bottles
        // https://leetcode.com/problems/water-bottles/solutions/5431087/easy-beats-100-java-python-c/

        int totalBottlesDrank = numBottles;
        while (numBottles >= numExchange){
            int newBottles = numBottles / numExchange;
            int remainingBottles = numBottles % numExchange;

            totalBottlesDrank += newBottles;
            numBottles = newBottles + remainingBottles;
        }

        return totalBottlesDrank;
    }
}
