package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 953 (easy)
 *
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
 * the given words are sorted lexicographically in this alien language.
 *
 */
public class VerifyAlienDictionary {

    private static int[] char_map; // map of key=character, val=orderIndex
    public boolean isAlienSorted(String[] words, String order) {
        char_map = new int[26];

        //0. create order_index
        for (int i = 0; i < order.length(); i++) {
            char_map[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            String w1 = words[i -1];
            String w2 = words[i];

            if(compare(w1, w2) > 0){
                return false;
            }
        }

        return true;
    }

    static int compare(String w1, String w2){
        int i=0;
        int j=0;
        int compareValue = 0;

        while (i < w1.length() && j < w2.length() && compareValue == 0){
            int w1Order = char_map[w1.charAt(i) - 'a'];
            int w2Order = char_map[w2.charAt(j) - 'a'];
            compareValue = w1Order - w2Order;

            i++;
            j++;
        }

        if(compareValue == 0){
            // if they're not the same length, w1 should be shorter than w2
            return w1.length() - w2.length();
        }
        else {
            // we broke out of while loop because compareValue was not  0
            return compareValue;
        }
    }

    /**
     * Solution Summary
     *  - Create an order index of the existing order (where ky=char, val=index)
     *  - loop over words[] (2 adjacent words at a time)
     *      - if word1 ends b4 w2, return
     */
    public boolean isAlienSorted_orderIndex(String[] words, String order) {
        // 0. create definitive order index
        Map<Character, Integer> orderIndex = new HashMap<>();
        for(int i=0; i<order.length(); i++){
            orderIndex.put(order.toCharArray()[i], i);
        }

        //1. compare adjacent words in [] to see if they're sorted
        for (int i = 1; i < words.length; i++) {
            String w1 = words[i -1];
            String w2 = words[i];

            for (int j = 0; j < w1.length(); j++) {
                if(j == w2.length()){
                    return false;   // if word-1 ends b4 word2
                }
                else if(w1.charAt(j) != w2.charAt(j)){

                    // if the characters are not the same, compare their order
                    int w1Order = orderIndex.get(w1.charAt(j));
                    int w2Order = orderIndex.get(w2.charAt(j));
                    if(w1Order > w2Order){
                        return false;
                    }
                    else{
                        break;  // this mean characters are not the same but 1 legitimately occurs before the other
                    }
                }
            }
        }

        return true;
    }
}
