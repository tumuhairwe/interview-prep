LeetCode 2850

ref: https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid/description/
Given a 2D array of 3 by 3 ... with some cells having stones and some having none

Task: Your task is to fill each cell with 1 stone

Assumption: 
You can assume the 2-D array is 3 by 3
You can assume the total number of stones is 9

Constraints:
- You can NOT move stones diagonally (only horizontally ... you can only move a) 
- left-to-right and right-to-left b) up-and-down and down-and-up (i.e. )  

write a function that returns the total number of moves required to distribute all stones equally (1 stone in each cell)
e.g. if (1, 1) has 2 stones but (1, 2) has zero
    - You can move the stone diagonally
e.g. if (0, 2) has 3 stones but (1,1) and (0, 1) has zero -> total number of moves == 2

    - You can not move diagonally from (2,2) to (1,1)
    - Move 1 stone from (2,2) into (2, 1) (horizontally) -- even if this means (2,1) has 2 stones
    - Mmove it again (2, 1) into (1, 1) 
    - (2,1) now has 2 stones left

    - Move 1 stone from (2,1) into 