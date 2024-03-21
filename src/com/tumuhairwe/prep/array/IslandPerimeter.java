package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * loop thru the grid ... for each cell that is a 1 .. increment totalPerimeter  += 4;
 *
 * Solution Summary:
 *  - loop thru 2D array
 *  - if value == covered/occupied (i.e. both sides are perimeter),
 *      - totalPerimeter = totalPerimeter + 4
 *      - if an occupied Neighbor above (grid[i-1][j] == 1, subtract 2 from total perimeter
 *      - if an occupied Neighbor behind (grid[i][j-1] == 1, subtract 2 from total perimeter
 * LeetCode 463
 *
 *  1 = land
 *  0 = water
 *
 * ref: https://www.youtube.com/watch?v=FkjFlNtTzc8
 * ref: https://leetcode.com/problems/island-perimeter/description/
 */
class IslandPerimeter {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int perimeter = isLandPerimeter(grid);
        System.out.println("Given greed " + Arrays.toString(grid));
        System.out.println("Perimeter =" + perimeter);

        grid = new int[][]{{1}};
        perimeter = isLandPerimeter(grid);
        System.out.println("Given greed " + Arrays.toString(grid));
        System.out.println("Perimeter =" + perimeter);

        grid = new int[][]{{1, 0}};
        perimeter = isLandPerimeter(grid);

        System.out.println("Given greed " + Arrays.toString(grid));
        System.out.println("Perimeter =" + perimeter);
    }
    public static int isLandPerimeter(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int WATER = 0;
        int LAND = 1;
        // big O = O(M x N) -- where M & N are the length and width of te 2D array
        int totalPerimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == LAND){
                    totalPerimeter += 4;

                    if(i > 0 && grid[i-1][j] == LAND){
                        totalPerimeter -= 2;
                    }

                    if(j > 0 && grid[i][j-1] == LAND){
                        totalPerimeter -= 2;
                    }
                }
            }
        }

        return totalPerimeter;
    }
}
