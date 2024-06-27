package com.tumuhairwe.prep.map;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 359 (easy)
 *
 * Design a logger system that receives a stream of messages along with their timestamps. E
 * ach unique message should only be printed at most every 10 seconds
 * (i.e. a message printed at timestamp t will prevent other identical messages from being printed
 * until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 */
public class LoggerRateLimiter {

    private Map<String, Integer> map;
    public LoggerRateLimiter(){
        this.map = new HashMap<>();
    }

    /**
     * Solution summary
     * - Initialize a map (key::String, value::Integer)
     * - onShouldPrint():
     *      - if map.containsKey(message) and message age + 10 secs > timestamp, return true
     *      - insert message in map and return true
     */
    public boolean shouldPrintMessage(int timestamp, String message){
        if(map.containsKey(message) && map.get(message) + 10 > timestamp){
            return false;
        }

        map.put(message, timestamp);
        return true;
    }
}
