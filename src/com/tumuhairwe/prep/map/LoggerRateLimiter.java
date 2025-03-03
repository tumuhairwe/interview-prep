package com.tumuhairwe.prep.map;

import java.util.*;

/**
 * LeetCode 359 (easy)
 * ref: https://leetcode.com/problems/logger-rate-limiter/
 *
 * Design a logger system that receives a stream of messages along with their timestamps. E
 * ach unique message should only be printed at most every 10 seconds
 * (i.e. a message printed at timestamp t will prevent other identical messages from being printed
 * until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 */
/**
 Google Requirement -- June 26 2024 -- interview (different from leetCode)

 We have a lot of servers our program is keeping track of. The schema for a server name is {type}-{number}. For example: api-1, api-2, etc.
 Sometimes we need to decommission a server. The next time we allocate a server of that type, we want to fill any gaps in server number left behind by any decommissioned servers first. Gaps should be filled using the smallest missing server number first.

 To that end, please implement a Tracker that implements the following:

 Tracker -
 init([list<string> existing_inventory])
 allocate(string type) -> string
 deallocate(string name) -> None(or Void)

 Example input and output:
 init([api-1, db-1])
 allocate(api) -> api-2
 allocate(db) -> db-2
 allocate(api) -> api-3

 allocate(xyz) -> api-3

 deallocate(api-2)
 deallocate(api-1)
 allocate(api) -> api-1

 Server
 - type
 - number
 */
public class LoggerRateLimiter {

    private Map<String, TreeSet<Integer>> cache;
    private Map<String, Integer> printedMessages;  // key=message, val=age_in_timestamp
    public LoggerRateLimiter(List<String> serverNames){
        //0. do validation
        Optional<String> invalidServerName = serverNames.stream()
                .filter(name -> !isValid(name))
                .findFirst();
        if(invalidServerName.isPresent()){
            throw new RuntimeException("1 of the server names is invalid");
        }
        this.cache = new HashMap<>();
        initializeCache(serverNames);
    }

    boolean isValid(String serverName){
        boolean isNotEmpty = serverName != null && serverName.length() > 3;

        if(isNotEmpty && serverName.split("-").length == 2) {
            //String name = serverName.split("-")[0];
            String number = serverName.split("-")[1];
            try {
                Integer.parseInt(number);
            } catch (Exception e) {
                return false;   // one of sever number must be numeric
            }
            return true;
        }
        return false;
    }

    void initializeCache(List<String> serverNames){
        for (String serverName : serverNames) {
            String name = serverName.split("-")[0];
            String number = serverName.split("-")[1];

            if(!cache.containsKey(name)){
                cache.put(name, new TreeSet<>());
            }

            cache.get(name).add(Integer.parseInt(number));
        }
    }

    // e.g. name = "serverA"

    /**
     * Solution summary
     * - Check if serverName exists e.g. "serverA" in cache
     * - if it doesn't exist, put empty TreeSet() in cache as its value
     * - get lowestMissingNumber for that serverName
     *      - this will get all existing server-numbers and attempt to find a discontinuation of sequence
     *      - set lowestMissingNumber as sequenceBreakingNumber + 1
     * - if lowestMissingNumber still = DEFAULT_VALUE, set it to the last()/biggest() number in that cache-key's name + 1
     * - return serverName as prefix + lowestMissingNumber
     */
    String allocate(String prefix){
        if(!cache.containsKey(prefix)){
            cache.put(prefix, new TreeSet<>());
        }

        // find the lowest missing number
        int lowestMissingNumber = Integer.MIN_VALUE;
        int[] nums = cache.get(prefix).stream().mapToInt(a -> a).toArray();
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i - 1] + 1){ // there's a gap in the sequence
                lowestMissingNumber = nums[i];
                break;
            }
        }

        // if there was no break in sequence... set to last + 1
        if(lowestMissingNumber == Integer.MIN_VALUE){
            lowestMissingNumber = cache.get(prefix).last() + 1;
        }

        // allocate
        cache.get(prefix).add(lowestMissingNumber);
        return prefix + "-" + lowestMissingNumber;
    }

    /**
     * Solution summary
     * - break name into prefix and number
     * - if cache contains prefix, ... remove number from its values
     * - if not, throw RuntimeException
     */
    void deallocate(String serverName){
        String prefix = serverName.split("-")[0];
        String number = serverName.split("-")[1];
        if (!cache.containsKey(prefix)){
            throw new RuntimeException("Can not de allocated " + serverName);
        }

        cache.get(prefix).remove(number);
    }

    /**
     * Solution summary
     * - Initialize a map (key::String, value::Integer)
     * - onShouldPrint():
     *      - if map.containsKey(message) and message age + 10 secs > timestamp, return true
     *      - insert message in map and return true
     */
    public boolean shouldPrintMessage(int timestamp, String message){
        if(cache.containsKey(message) && printedMessages.get(message) + 10 > timestamp){
            return false;
        }

        printedMessages.put(message, timestamp);
        return true;
    }
}
