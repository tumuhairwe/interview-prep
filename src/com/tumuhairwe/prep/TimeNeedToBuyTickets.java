package com.tumuhairwe.prep;

/**
 * LeetCode 2073
 * There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
 *
 * You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].
 *
 * Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.
 *
 * Return the time taken for the person at position k (0-indexed) to finish buying tickets.
 *
 * ref: https://leetcode.com/problems/time-needed-to-buy-tickets/description/
 * ref: https://www.youtube.com/watch?v=cVmS9N6kf2Y
 */
public class TimeNeedToBuyTickets {

    public static void main(String[] args) {
        int[] arr = {2,3,2};
        System.out.println(timeRequiredToBuy(arr, 2));

        int[] arr2 = {5,1,1, 1};
        System.out.println(timeRequiredToBuy(arr2, 0));
    }

    /**
     * Solution summary
     * - initialize timeTake  & index to 0;
     * - while ticket[k] > 0
     *      - decrement ticket[idx]
     *      - increment timeTaken
     *
     *      - move idx forward
     *      - if idx == length-of-tickets ... reset to beginning of line (i.e. idx =0)
     * - when while loop exits, return timeTaken
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int timeRequiredToBuy(int[] tickets, int k){
        int timeTaken = 0;
        int idx = 0;    // start @ front of line

        while (tickets[k] > 0){
            if(tickets[idx] > 0){
                tickets[idx]--;
                timeTaken++;
            }

            idx++;
            if(idx == tickets.length){
                idx = 0;    // go back to beginning of line
            }
        }

        return timeTaken;
    }
}
