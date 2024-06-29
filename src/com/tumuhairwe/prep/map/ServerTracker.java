package com.tumuhairwe.prep.map;

import java.util.*;

/**
 * We have a lot of servers our program is keeping track of. The schema for a server name is {type}-{number}. For example: api-1, api-2, etc.
 *   Sometimes we need to decommission a server. The next time we allocate a server of that type, we want to fill any gaps in server number left behind by any decommissioned servers first. Gaps should be filled using the smallest missing server number first.
 *
 * To that end, please implement a Tracker that implements the following:
 *
 * Tracker -
 * init([list<string> existing_inventory])
 * allocate(string type) -> string
 * deallocate(string name) -> None(or Void)
 *
 * Example input and output:
 * init([api-1, db-1])
 * allocate(api) -> api-2
 * allocate(db) -> db-2
 * allocate(api) -> api-3
 *
 * allocate(xyz) -> api-3
 *
 * deallocate(api-2)
 * deallocate(api-1)
 * allocate(api) -> api-1
 *
 */
public class ServerTracker {
    private Map<String, TreeSet<Integer>> cache;

    public ServerTracker(List<String> serverNames){
        this.cache = new HashMap<>();

        //0. do input validation
        Optional<String> o = serverNames.stream().filter(name -> !isValid(name)).findFirst();
        if(o.isPresent()){
            throw new RuntimeException("There are some invalid names: " + o.get());
        }

        //1.
        initializeCache(serverNames);
    }

    private boolean isValid(String name) {
        boolean isNotEmpty = name != null && name.length() > 3;
        boolean hasTokens = name.split("-").length == 2;
        if(isNotEmpty && hasTokens){
            String n = name.split("-")[1];
            for (int i = 0; i < n.length(); i++) {
                if(!Character.isDigit(n.toCharArray()[i])){
                    return false;
                }
            }
            return true;
        }

        return false;
    }
    private void initializeCache(List<String> serverNames) {
        //. process valid name
        for (String serverName : serverNames){
            String name = serverName.split("-")[0];
            String num = serverName.split("-")[1];

            if (!cache.containsKey(name)){
                cache.put(name, new TreeSet<>());
            }
            cache.get(name).add(Integer.parseInt(num));
        }
    }

    String allocate(String serverName){
        if (!cache.containsKey(serverName)){
            cache.put(serverName, new TreeSet<>());
        }

        // find the lowest missing number in sequence e.g. 1,2,3,5,6 -> should break because 4 is missing
        int lowestMissingNumber = Integer.MIN_VALUE;
        int[] nums = cache.get(serverName).stream().mapToInt(Integer::intValue).toArray();
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i -1] + 1){  // 1, 2, 3, 4, 6 -> should break because 5 is missing
                lowestMissingNumber = nums[i];
                break;
            }
        }

        if(lowestMissingNumber == Integer.MIN_VALUE){   // if all was sequential (no breakage) ... 1,2,3,4,5,6
            lowestMissingNumber = cache.get(serverName).last() + 1; // 7
        }

        cache.get(serverName).add(lowestMissingNumber); // server-7
        return serverName + "-"+ lowestMissingNumber;
    }

    void deallocate(String serverName){
        String name = serverName.split("-")[0];
        Integer num = Integer.parseInt(serverName.split("-")[1]);

        if(cache.get(name).contains(num)){
            cache.get(name).remove(num);
        }
        else throw new RuntimeException("Can not deallocate " + serverName);
    }
}
