package com.tumuhairwe.prep.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2284 (medium)
 *
 * You have a chat log of n messages. You are given two string arrays messages and senders where messages[i] is a
 * message sent by senders[i].
 *
 * A message is list of words that are separated by a single space with no leading or trailing spaces.
 * The word count of a sender is the total number of words sent by the sender. Note that a sender may send more than one message.
 *
 * Return the sender with the largest word count. If there is more than one sender with the largest word count,
 * return the one with the lexicographically largest name.
 */
public class SenderWithLargestCount {

    /**
     * TC: O(n)
     * SC: O(n)
     * @param message
     * @param senders
     * @return
     */
    public String largestWordCount(String[] message, String[] senders){
        Map<String, Integer> map = new HashMap<>();
        String name = "";
        int maxFreq = 0;

        for (int i = 0; i < message.length; i++) {
            String[] msgs = message[i].split("\\s");
            String sender = senders[i];

            // populate freq
            map.put(sender, map.getOrDefault(sender, 0) + msgs.length);

            if(map.get(sender) > maxFreq){
                maxFreq = map.get(sender);
                name = sender;
            }
            else if(map.get(sender) == maxFreq && name.compareTo(sender) < 0){
                name = sender;
            }
        }

        return name;
    }
}
