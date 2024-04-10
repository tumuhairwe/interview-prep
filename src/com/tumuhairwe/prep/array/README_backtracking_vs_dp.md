Dynamic Programming and Backtracking are both problem-solving techniques that solve problems
by performing an exhaustive search.

ref: DP vs Backtracking -> https://www.javatpoint.com/dynamic-programming-vs-backtracking

ref: Backtracking -> https://guides.codepath.com/compsci/Backtracking

However, they differ in
- 
- Approach: DP uses the principle of optimality and breaks down the problems into smaller sub-problems. Backtracking uses a brute force apprach and tries all possible solutions
- Focus: DP focuses on overlapping sub-problems. Backtracking focuses on ALL or SOME solutions
- Search: DP is mmore like BFS (building up one layer at a time). Backtracking is more like DFS--building up one solution first
- Space: DP optimally usually takes more space that backtracking
- Execution: Backtracking usually takes more time to execute than DP (because it has to search the whole problem space)
- Optimization: DP is used to solve optimization problems. Backtracking is not useful for solving optimization problems.

When to use
- 
Use backtracking if you're asked to return a collection of all answers ... or you are concerned with the actual solution rather than the most optimum value of some parameter

Use DP if you can memoize by checking for repeating states or you can use a greedy propery