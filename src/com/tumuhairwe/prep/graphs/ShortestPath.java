package com.tumuhairwe.prep.graphs;
import java.util.*;

/**
 * Given a grid of 0s and 1s, and
 *      - a starting cell (startingColumn and startingRow)
 *      - a target cell (terminalRow and terminalColumn)
 *
 * Find the length of the shortest path that from starting cell to target cell that walks along the 1 values only
 *
 * Constraints
 * - Each location in the path including the start and the end must be a 1
 * - Each subsequent location in the path must be 4 directionally adjacent to the previous location
 * - It is guaranteed that the staring cell and target cell are different
 * - If the task is impossible return -1
 *
 * - Create a Queue of cells (cell = int row & int col)
 * - determine if cell is starting point
 * ref: https://www.pramp.com/challenge/Y56aZmaj9Ptmd9wV9xvL
 */
class ShortestPath {

    static int LAND = 1;

    public static void main(String[] args) {
        /*
        (The lines below represent this grid:)
            1111
            0001
            1111
         */
        int[][] grid = new int[][]{
                {1, 1, 1, 1}, {0, 0, 0, 1}, {1, 1, 1, 1}
        };
        int startingRow = 0, startingColumn = 0, terminalRow = 2, terminalColumn = 0;
        int steps = depthFirstSearch(grid, startingRow, startingColumn, terminalRow, terminalColumn);
        System.out.println("The length of the shortest path is " + steps);

        /*
        (The lines below represent this grid:)
            1111
            0001
            1011
         */
        grid = new int[][]{
                {1, 1, 1, 1}, {0, 0, 0, 1}, {1, 0, 1, 1}
        };
        steps = depthFirstSearch(grid, startingRow, startingColumn, terminalRow, terminalColumn);
        System.out.println("The length of the shortest path is " + steps);
    }

    int shortestCellPath(int[][] grid, int startingRow, int startingColumn, int terminalRow, int terminalColumn) {
        // --> bfs = enqueue -> check neighbors
        // dfs = stack
        // ArrayDeque == resizable-array impl of queue
        //  - is not threat-safe
        //  - and does NOT allow null elements
        //  - more effecitent that LinkedList
        //  - useful if insertion/deletion in middle of list is frequent
        Queue<Cell> que = new ArrayDeque<>();   // not thread-safe. Use Collections.synchronizedCollection();

        // LinkedList = list impl of queue
        //  - is thread-safe
        //  - and allows null elements
        //  - less efficient if that ArrayDeque
        //  - good choice if frequent insertion/removal operations occur at top/tail of list and random access is not important
        Queue<Cell> que2 = new LinkedList<>();
        //Collection<Cell> q = Collections.synchronizedCollection(new ArrayDeque<>());

        //que.add(new Cell(startingRow,startingColumn));

        Set<Cell> visited = new HashSet<>();

//        while(!que.isEmpty()){
//            r, c, depth = queue.pop()
//            if r == terminalRow and c == terminalColumn: return depth
//            for (nr, nc) in ((r-1, c), (r+1, c), (r, c-1), (r, c+1)):
//            if 0 <= nr < R and 0 <= nc < C and grid[nr][nc] == 1 and (nr, nc) not in seen:
//            queue.append((nr, nc, depth + 1))
//            seen.add((nr, nc))
//        }

        Cell seed = new Cell(startingRow,startingColumn);
        que.add(seed);
        while (!que.isEmpty()){
            // BFS condition: for each neighbor,
            // if its visitable/is-allowed && is-not-visited yet ...
            //      - add to queue
            //      - add to visited set

            // need to do boundary checks
            int[][] offsets = {
                    {0, 1}, {1, 0},
                    {0, -1}, {-1, 0}
            };
            for (int[] offset : offsets){
                int rowOffset = offset[0];
                int colOffset = offset[1];

                Cell current = new Cell(startingRow + rowOffset, startingColumn + colOffset);
                boolean isAllowed = grid[current.row][current.col] == LAND;
                if(!visited.contains(current) && isAllowed){
                    que.add(current);
                    visited.add(current);   // !que.contains(current) &&
                }
            }
        }

        return que.size();
    }

    static Set<Cell> visited = new HashSet<>();
    static Queue<Cell> validPath = new ArrayDeque<>();
    static int NOT_FOUND = -1;
    static int depthFirstSearch(int[][] grid, int row, int col, int terminalRow, int terminalColumn){
        boolean isAllowed = grid[row][col] == 0;

        // 0. check base case (have we reached the end?)
        if(row == terminalRow && col == terminalColumn){
            return validPath.size();
        }

        // 1. checking boundaries
        boolean isRowOutOfBounds = row <0 || row >= grid.length;
        boolean isColOutOfBounds = col < 0 || col >= grid[0].length;
        if(isRowOutOfBounds || isColOutOfBounds || !isAllowed){
            return NOT_FOUND;   // will return false if entire grid is traversed and word is not found
        }

        // 2. check neighbors
        int[][] offsets = {
                {0, 1}, {1, 0},
                {0, -1}, {-1, 0}
        };

        // 2a. explore the 4 neighboring cells to see if the next characters of the word can be found
        int result = NOT_FOUND;
        for (int[] offset : offsets){
            int rowOffset = offset[0];
            int colOffset = offset[1];

            Cell current = new Cell(row + rowOffset,col + colOffset);
            if(!visited.contains(current)){
                visited.add(current);

                // 3. repeat from the neighboring cell (index + 1) until the entire grid is traversed of the word is found
                result = depthFirstSearch(grid,current.row, current.col, terminalRow, terminalColumn);

                if(result != NOT_FOUND){
                    validPath.add(current);
                    break;  // we found the solution, return
                }
            }
        }
        return validPath.size();
    }

    record Cell(int row, int col){};
}

