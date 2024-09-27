package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 1267 (medium)
 * You are given a map of a server center as a (m * n) integer matrix ("grid")
 * where 1 means that on that cell,there is a server and 0, means there's no server
 * 2 servers are said to communicate if they're on the same row or on the same column
 *
 * Return the number of servers that communicate with any other server
 */
public class ServersThatCommunicate {
    /**
     * Solution summary
     * - init arrays to track count_of_servers_in_rows and count_of_servers_in_cols
     * - traverse 2D array & populate both arrays (increment each counter by 1 for reach cell that is a server
     * - traverse 2D array again & for-each cell that is a server && (serverInRow > 1 || serverInCol > 1), increment connectedCount
     * - return connected count
     *
     *
     * TC: O(m * n) to traverse 2d []
     * SC: (n) to store both arrays
     */
    public int countServer(int[][] grid){
        int IS_SERVER = 1;
        int[] serversInRows = new int[grid.length];
        int[] serversInCols = new int[grid[0].length];

        //1. populate both arrays
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == IS_SERVER){
                    serversInRows[i]++;
                    serversInCols[j]++;
                }
            }
        }
        
        //2.
        int connectedCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == IS_SERVER && (serversInRows[i] > 1 && serversInCols[j] > 1)){
                    connectedCount++;
                }
            }
        }

        return connectedCount;
    }
}
