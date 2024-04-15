LeetCode practice covering 
- Matrices
- Arrays (merging sorted arrays)
- String manipulation (longest substring, find repeated DNS sequence)
- Sliding window
- Array
- Graphs
- Tries

General guidelines
- If question is asking top/maximum/minimum/closest K elements among N elements, 
  - use a <b>Heap</b> 
- If the given input is a sorted array or list, use either a binary search or 2-pointers strategy
- If we need to try all combinations (or permutations) of the input, use either backtracking or BFS (bread-first search)
- Most questions related to Tree or Graphs can be solved either thru a BFS or DFS
- Every recursive solution can be converted to an iterative solution using a stack
- For a problem involving arrays, if there exists a solution in O(n ^ 2) time, and O(1) space, there must exist 2 other solutions
-- Using a HashMap or a Set for O(n) time and O(n) space
-- Using sorting for O(n log n) time and O(1) space
- If a problem is asking for optimization (e.g. maximization or minimization), use Dynamic Programming
- If we need to find some common substring among a set of string, use a HashMap or Trie
- If we need to search/manipulate a bunch of strings, a Trie will be the best data structure
- If the problem is related to a LinkedList, and we can't use extra space, then use the Fast and Slow pointer approach