package com.tumuhairwe.prep.map;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 920
 * Your music player contains N different songs
 * You want ot listen to goal songs (not necessarily different) during you trip
 * To avoid boredom, create a playlist so that
 *  - Every song plays at least once
 *  - A song can only be played again if K other songs  have been played
 *
 *  Given, N, goal and K, return the number of possible playlists that you can create.
 *  Since that answer can be very lard, return it module (10^9 + 7)
 */
public class NumberOfMusicPlaylists {

    public static void main(String[] args) {
        int n = 3, goal = 3, k = 1;
    }

    static Map<Pair, Integer> map;
    static int mod = 1000000007;

    public static int numPlaylists(int n, int goal, int k){
        map = new HashMap<>();
        return (int) findPlaylists(0, goal, n, k);
    }

    private static long findPlaylists(int old_songs, int goal, int n, int k) {
        // 0. base case 1
        if(goal == 0 && old_songs == n){
            return 1;
        }

        // 0. base case 2
        if(goal == 0 || old_songs > n){
            return 0;
        }

        // 2. return pre-calculated/dynamic-programming cached value
        if(map.containsKey(new Pair(old_songs, goal))){
            return map.get(new Pair(old_songs, goal));
        }

        // 3. choosing a new song
        long result = findPlaylists(old_songs, goal - 1, n, k);
        long res = ((n - old_songs) * (result % mod)) % mod;

        //4. choose an old song
        if(old_songs > k){
            res = (res + ((old_songs - k) * (findPlaylists(old_songs, goal - 1, n, k)) % mod) % mod) % mod;
        }

        map.put(new Pair(old_songs, goal), (int)res);

        return res;
    }

    record Pair(int old_songs, int goal){}
}
