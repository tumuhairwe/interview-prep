package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LeetCode 3295 (medium)
 * You're given a string[] 'message' and a string[] 'bannedWords'
 * An [] of words is considered spam if there are at least 2 words in that message that match exactly
 * any word in bannedWords
 *
 * return true if the array message is spam and false otherwise
 */
public class ReportSpam {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        //0. declar vars
        List<String> msgList = Arrays.asList(message);
        Set<String> bwSet = Arrays.stream(bannedWords).collect(Collectors.toSet());
        int count = 0;

        //1 loop thru msgList
        for (String msg : msgList){
            // 3. check if msg is contained
            if(bwSet.contains(msg)){
                count++;    // 4. increment counter if trye
            }
            if(count >= 2){
                return true;    //5. return true if count exceed threshold
            }
        }

        return false;
    }
}
