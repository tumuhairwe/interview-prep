package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 2266 (medium)
 * Alice is texting Bob using her phone. The mapping is similar to letter-combinations-of-a-phone
 * In order to add a letter, Alice has to press the key of the corresponding digit i times, where i is the position
 * of the letter key
 * - For example, to add the letter 's', Alice has to press '7' four times. Similarly, to add the letter 'k', Alice has to press '5' twice.
 * - Note that the digits '0' and '1' do not map to any letters, so Alice does not use them.
 *
 * However, due to an error in transmission, Bob did not receive Alice's text message but received a String of pressed
 * keys instead
 * For example, when Alice sent the message "bob", Bob received the string "2266622".
 * Given a string pressedKeys representing the string received by Bob, return the total number of possible text messages Alice could have sent.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 * ref: https://leetcode.com/problems/count-number-of-texts/description/
 * ref: https://leetcode.com/problems/count-number-of-texts/solutions/2017753/simple-o-n-single-pass-solution/
 *
 */
public class CountTexts {

    public static void main(String[] args) {
        String s1 = "222222222222222222222222222222222222";
        String s2 = "22233";
        System.out.println(countText(s1));

        System.out.println(countText(s2));
    }
    /**
     * Solution summary
     * - For every number other than 7 and 9, we consider at most 3 consecutive digits whereas 7 and 9 we consider at most 4
     * - Create and init an empty dp array
     * - loop thru every char in pressedKeys ...
     * - for every i,  we can add dp[i-1], dp[i-2], dp[i-3] to dp[i]
     * - if i == 7 || i == 9, we then add dp[i-4] also
     *
     * SC: O(n)
     * TC: O(n)
     */
    public static int countText(String pressedKeys){
        double MOD = Math.pow(10, 9) + 7;
        //Set<Character> setOfFourLengthChars = Set.of('7', '9');
        int length = pressedKeys.length();

        //0. create & seed arr
        double[] memo = new double[length + 1];
        memo[0] = 1;

        for (int i = 1; i <= length; i++) {
            memo[i] = memo[i-1] % MOD;

            if(i-2 >= 0 && pressedKeys.charAt(i-1) == pressedKeys.charAt(i-2)){
                memo[i] = (memo[i] + memo[i-2]) % MOD;

                if(i-3 >= 0 && pressedKeys.charAt(i-1) == pressedKeys.charAt(i-3)){
                    memo[i] = (memo[i] + memo[i-3]) % MOD;
                }

                if((pressedKeys.charAt(i-1) == '7' || pressedKeys.charAt(i-1) == '9')
                        && i-4 >= 0
                        && pressedKeys.charAt(i-1) == pressedKeys.charAt(i-4)){
                    memo[i] = (memo[i] + memo[i-4]) % MOD;
                }
            }
        }

        return (int) memo[length];
    }
}
