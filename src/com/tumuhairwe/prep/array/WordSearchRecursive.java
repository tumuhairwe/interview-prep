package com.tumuhairwe.prep.array;

/**
 * Like Iterative WordSearch
 * Summary: Given a 2D matrix, and a word determine if the word can be constructed
 * from letters of sequentially adjacent cells
 *
 * Constraint:
 * - char[][] board and String word consist of lower-case letters only
 * - Search is not case-sensitive
 *
 * LeetCode 79
 * ref: https://leetcode.com/problems/word-search/
 * ref: https://www.youtube.com/watch?v=m9TrOL1ETxI&t=111s
 */
public class WordSearchRecursive {

    public static boolean wordSearch(char[][] grid, String word){
        int n = grid.length;;
        int m = grid[0].length;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(depthFirstSearch(row, col, word, 0, grid)){
                    return true;
                }
            }
        }

        return false;
    }

    // TC: O( c X 3_to_the_power_l)
    //      where c = the number of cells
    //            l = the length of the word we are searching for
    // SC : O ( l ) = where l is the length of the word to be searched in the grid.
    //                  This is the maximum depth of the recursive call tree at any point during the search
    private static boolean depthFirstSearch(int row, int col, String word, int index, char[][] grid) {
        // 0.base case
        if(word.length() == index){
            return true;
        }

        // 1.boundary checking
        boolean isRowOutOfBounds = row <0 || row >= grid.length;
        boolean isColOutOfBounds = col < 0 || col >= grid[0].length;
        boolean isDifferentCharacter = grid[row][col] != word.charAt(index);
        if(isRowOutOfBounds || isColOutOfBounds || isDifferentCharacter){
            return false;   // will return false if entire grid is traversed and word is not found
        }

        // boolean result = false;
        char temp = grid[row][col];
        grid[row][col] = '*';

        int[][] offsets = {
                {0, 1}, {1, 0},
                {0, -1}, {-1, 0}
        };

        // 2. explore the 4 neighboring cells to see if the next characters of the word can be found
        boolean result = false;
        for (int[] offset : offsets){
            int rowOffset = offset[0];
            int colOffset = offset[1];

            // 3. repeat from the neighboring cell (index + 1) until the entire grid is traversed of the word is found
            result = depthFirstSearch(row + rowOffset, col + colOffset, word, index+1, grid);
            if(result){
                break;  // we found the solution, return
            }
        }

        // put back temp
        grid[row][col] = temp;
        return result;
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.print("\t\t[");
            for (int j = 0; j < grid[0].length; j++) {
                if (j < grid[0].length - 1)
                    System.out.print("'" + grid[i][j] + "', ");
                else
                    System.out.print("'" + grid[i][j] + "'");
            }
            System.out.println("]");
        }
        System.out.println("\n");
    }

    public static void main( String args[] ) {
        char[][][] grids = {
                {{'E', 'D', 'X', 'I', 'W'},
                        {'P', 'U', 'F', 'M', 'Q'},
                        {'I', 'C', 'Q', 'R', 'F'},
                        {'M', 'A', 'L', 'C', 'A'},
                        {'J', 'T', 'I', 'V', 'E'}},

                {{'E', 'D', 'X', 'I', 'W'},
                        {'P', 'A', 'F', 'M', 'Q'},
                        {'I', 'C', 'A', 'S', 'F'},
                        {'M', 'A', 'L', 'C', 'A'},
                        {'J', 'T', 'I', 'V', 'E'}},

                {{'h', 'e', 'c', 'm', 'l'},
                        {'w', 'l', 'i', 'e', 'u'},
                        {'a', 'r', 'r', 's', 'n'},
                        {'s', 'i', 'i', 'o', 'r'}},

                {{'C', 'Q', 'N', 'A'},
                        {'P', 'S', 'E', 'I'},
                        {'Z', 'A', 'P', 'E'},
                        {'J', 'V', 'T', 'K'}},

                {{'O', 'Y', 'O', 'I'},
                        {'B', 'Y', 'N', 'M'},
                        {'K', 'D', 'A', 'R'},
                        {'C', 'I', 'M', 'I'},
                        {'Z', 'I', 'T', 'O'}}
        };
        String[] words = {"EDUCATIVE", "PACANS", "warrior", "SAVE", "DYNAMIC"};
        for(int i=0;i<words.length;i++){
            System.out.print(i+1);
            System.out.println(".\tGrid = ");
            printGrid(grids[i]);
            System.out.println("\tWord = "+ words[i]);

            Boolean result = wordSearch(grids[i], words[i]);
            if(result == true){
                System.out.println("\n\tSearch result = Found Word");
            }
            else{
                System.out.println("\n\tSearch result = Word could not be found");
            }
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
