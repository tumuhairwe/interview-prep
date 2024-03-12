package com.tumuhairwe.prep.array;

import java.util.List;
import java.util.Stack;

/**
 * Givena 2D array .. where each index == roomNumber and value == roomKey,
 *
 * Determine if ew can visit every room of if we have a room without a key
 * ref: https://leetcode.com/problems/keys-and-rooms/description/
 */
public class KeysAndRooms {
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
}
