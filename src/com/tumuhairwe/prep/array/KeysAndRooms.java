package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 841 (medium)
 * Given 2D array .. where each index == roomNumber and value == roomKey,
 *
 * Determine if we can visit every room of if we have a room without a key
 * ref: https://leetcode.com/problems/keys-and-rooms/description/
 * ref: https://www.youtube.com/watch?v=Rz_-Kx0LN-E
 */
public class KeysAndRooms {

    /**
     * Solution Summary
     * - Create an array of rooms.size()
     * - Arrays.fill(array, false)
     * - Keep track of keys
     * - put key-0 onto the stack
     * - loop by iterating over the stack ... pop the current key
     * - Use key popped() from stack to rooms.get(key) ...for each key, if unseen, mak at seen
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms){
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true; // we can automatically visit room 0 (fist room)

        Stack<Integer> keys = new Stack<>();
        keys.add(0);

        while (!keys.isEmpty()){
            int current_key = keys.pop();
            for (int new_key : rooms.get(current_key)){
                if(!seen[new_key]){
                    seen[new_key] = true;
                    keys.add(new_key);
                }
            }
        }
        for(boolean visited: seen){
            if(!visited) return  false;
        }

        return true;
    }

    public boolean canVisitAllRooms_recursiveDfs(List<List<Integer>> rooms){
        Set<Integer> visitedRooms = new HashSet<>();
        recurse(0, visitedRooms, rooms);

        return visitedRooms.size() == rooms.size();
    }
    void recurse(Integer currentRoom, Set<Integer> visitedRooms, List<List<Integer>> rooms){
        if(visitedRooms.contains(currentRoom)){
            return; // avoid infinite loop
        }

        visitedRooms.add(currentRoom);
        for(Integer nextRoom : rooms.get(currentRoom)){
            recurse(nextRoom, visitedRooms, rooms);
        }
    }

    boolean[] roomState;
    // TC: O(R + K) where R = number-of-rooms and K = number-of-keys
    // SC: O(n) where n == number of rooms
    public boolean canVisitAllRooms_dfs(List<List<Integer>> rooms) {
        // 0. create boolean array to track roomState
        roomState = new boolean[rooms.size()];
        Arrays.fill(roomState, false);

        // 1. mark first room as visited
        roomState[0] = true;

        // 2. call DFS on all rooms
        dfs(rooms, 0);

        // 3.if at least 1 room is unVisited == return false
        for(boolean state : roomState){
            if(!state){
                return false;
            }
        }

        // 4. if we get here, all rooms have been visited
        return true;
    }

    public void dfs(List<List<Integer>> allRooms, int roomNumber){

        for(Integer key : allRooms.get(roomNumber)){
            if(roomState[key] == false){
                roomState[key] = true;

                dfs(allRooms, key);
            }
        }
    }
}
