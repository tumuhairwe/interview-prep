package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 408 (easy)
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 *
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 * ref: https://leetcode.com/problems/valid-word-abbreviation/description/
 * ref: https://www.youtube.com/watch?v=pzCXi5HvKsQ
 */
public class ValidWordAbbreviation {

    public static void main(String[] args) {
        String word = "internationalization";
        String abbr = "i12iz4n";

        System.out.println(validWordAbbreviation(word, abbr));
        System.out.println(validWordAbbreviation("apple", "a2e"));
    }
    public static boolean validWordAbbreviation(String word, String abbr){
        int p1 = 0;
        int p2 = 0;

        ///0. base case: abbr is longer than word -> false
        if(abbr.length() > word.length()){
            return false;
        }

        //1. iterate with 2 pointers
        while (p1 < word.length() && p2 < abbr.length()){
            //2. if chars match
            if(word.charAt(p1) == abbr.charAt(p2)){
                p1++;
                p2++;
                continue;
            }

            //3. if leading 0 or char !numeric -> false
            if(!Character.isDigit(abbr.charAt(p2)) || abbr.charAt(p2) == '0'){
                return false;
            }

            //4. get number -> add to p1
            int num = 0;
            while (Character.isDigit(abbr.charAt(p2)) && p2<abbr.length()){
                int i = abbr.charAt(p2) - '0';
                num = num * 10 + i;
                p2++;
            }

            //5. num to p1
            p1 += num;
        }

        return p1 == word.length() && p2 == abbr.length();
    }
}
