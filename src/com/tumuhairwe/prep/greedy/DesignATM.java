package com.tumuhairwe.prep.greedy;

/**
 * LeetCode 2241 (medium)
 * There's an ATM that stores banknotes fo 5 denominations (20, 50, 100, 200, and 500) dollars.
 * Initially the ATM is empty. The user can use the machine to deposit or withdraw any amount of money
 *
 * When withdrawing, the machine prioritizes using banknotes of LARGE values
 * - e.g. if you want to withdraw $300 & there are 2 $50 notes, 1 $100 note and 1 $200 notes, then it will use the $100 and $200 notes
 * - however if you try to withdraw $600 and there are 3 $200 notes and 1 $500 notes,
 * then the request will be rejected because the machine will try to use the $500 and be unable to use other notes to complete
 * the remaining $100.
 * Note that the machine is NOT allowed to use the $200 note instead of the $500 note
 * Task:
 * Implement a class
 * - ATM() initializes the ATM Object
 *  - void deposit(int[] banknotes) deposits new banknotes in the order $20, $50, $100, $200 and $500
 *  - int[] withdraw(int amount) returns an array of length=3,
 * ref: https://leetcode.com/problems/design-an-atm-machine/
 * ref: https://www.youtube.com/watch?v=edP4gaMO80E
 */
class ATM {

    private int[] demons = new int[]{20, 50, 100, 200, 500};
    private long[] safe;    // use long[] to avoid lossy errors

    public ATM(){
        safe = new long[5];
    }

    public void deposit(int[] bankNodesCount){
        // update safe
        for (int i = 0; i < demons.length; i++) {
            safe[i] += bankNodesCount[i];
        }
    }

    public int[] withdraw(int amount){
        int[] ans = new int[5]; // will store the quantity of each note
        int remainder = amount;

        // calculate amount to deduct. iterate safe backwards
        for(int i=safe.length - 1; i>= 0; i--){
            if(remainder >= demons[i]){
                // use min() bco if amount/demons[i] == 3, but safe only has 1, we just use 1
                int qty = (int) Math.min(safe[i], remainder/demons[i]);
                remainder -= demons[i] * qty;
                ans[i] = qty;
            }
        }

        // handle edge case
         if(remainder != 0){
             return new int[]{-1};
         }

         // update safe
        for (int i = 0; i < demons.length; i++) {
            safe[i] -= ans[i];
        }
        return ans;
    }
}
