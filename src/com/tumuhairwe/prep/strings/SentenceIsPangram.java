package com.tumuhairwe.prep.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1832 (easy)
 * A pangram is a sentence where every letter of the english alphabet appears at least once
 * Given a string <sentence> containinly only lowercase englisj letters, return true if sentence is a pangram
 *  or false otherwise
 *
 *  ref: https://leetcode.com/problems/check-if-the-sentence-is-pangram/description/
 */
public class SentenceIsPangram {

    /**
     * Solution summary
     * - Treat string as characters and put them into a set
     * - return true is size of set = 26 (number of characters in alphabet)
     */
    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();
        for(Character ch : sentence.toCharArray()){
            set.add(ch);
        }

        return set.size() == 26;
    }
}
