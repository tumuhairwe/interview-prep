package com.tumuhairwe.prep.array;

/**
 * LeetCode 860 (easy)
 *
 * At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at a time
 * (in the order specified by bills). Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer so that the net transaction is that the customer pays $5.
 *
 * Note that you do not have any change in hand at first.
 *
 * Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide every
 * customer with the correct change, or false otherwise.
 *
 * ref: https://leetcode.com/problems/lemonade-change/
 * ref: https://www.youtube.com/watch?v=_hnQY74pnCA
 */
public class LemonadeChange {

    public static void main(String[] args) {
        int[] bills = {5,5,5,10,20};
        System.out.println("Should be true " + lemonadeChange(bills));

        bills = new int[]{5,5,10,10,20};
        System.out.println("Should be false " + lemonadeChange(bills));
    }
    public static boolean lemonadeChange(int[] bills){
        int tens = 0;
        int fives = 0;

        for(Integer bill : bills){
            if(bill == 5){
                fives++;        // no change back
            }
            else if(bill == 10){// paying with a 10
                fives--;        // cashRegister += 5
                tens++;         // change = 5 back
            }
            else if(tens > 0){  // they're paying with 20 ... do we have any 10s?
                tens--;
                fives--;
            }
            else{   //  else we don't have 10s to give ... must return 3 fives
                fives -= 3;
            }

            // if we run out of 5s at any time ... return false
            if(fives < 0){
                return false;
            }
        }

        return true;
    }
}
