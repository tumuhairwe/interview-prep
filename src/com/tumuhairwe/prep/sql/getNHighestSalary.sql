/**
  LeetCode 177 (medium)
  ref: https://leetcode.com/problems/nth-highest-salary/description/
 */
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N=N-1   # MySql uses zero-based index
    return (
        /*
        - SELECT the salary in DESCdning order (ORDER BY salary DESC)
        - Use offset n-1 to return the number of roww
        - use LIMIT x to limit to only x results
        - Use DISTINCT to only select distinct values in the salary column
         */
        SELECT DISTINCT salary
        FROM Empployee
        ORDER BY salary DESC
        LIMIT 1
        OFFSET N
    );
END