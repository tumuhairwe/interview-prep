package com.tumuhairwe.prep.array;

import java.util.Arrays;

/**
 * Given a 2D array, representing a map where
 *  - grid[i][j] = 1 represents land and
 *  - grid[i][j] = 0 represents water
 *
 *  Grid cells are connected horizontally or vertically (not diagonally).
 *  The grid is completely surrounded by water and there is exactly 1 island (i.e. 1 or more connected land cells)
 *
 *  The island doesn't have lakes, meaning the water inside isn't connected to the water outside. One cell is swuare with side_length = 1
 *  The grid is rectangular. with and height don't exceed 100.
 *
 *  Exercise: Determine the perimeter of the island
 *
 * loop thru the grid ... for each cell that is a 1 .. increment totalPerimeter  += 4;
 *
 * Solution Summary:
 *  - loop thru 2D array
 *  - if value == covered/occupied (i.e. both sides are perimeter),
 *      - totalPerimeter = totalPerimeter + 4
 *      - if an occupied Neighbor above (grid[i-1][j] == 1, subtract 2 from total perimeter
 *      - if an occupied Neighbor behind (grid[i][j-1] == 1, subtract 2 from total perimeter
 *
 * LeetCode 463 Easy
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
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if(grid[row][col] == LAND){
                    totalPerimeter += 4;    // add all 4 sides (top + bottom) + (left + right)

                    if(row > 0 && grid[row-1][col] == LAND){  // if neighbor on the left == LAND
                        totalPerimeter -= 2;
                    }

                    if(col > 0 && grid[row][col-1] == LAND){ // if neighbor on the BOTTOM
                        totalPerimeter -= 2;
                    }
                }
            }
        }

        return totalPerimeter;
    }
}
