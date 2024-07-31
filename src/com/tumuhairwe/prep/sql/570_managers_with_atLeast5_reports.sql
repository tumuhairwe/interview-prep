/**
  Table: Employee

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| department  | varchar |
| managerId   | int     |
+-------------+---------+
id is the primary key (column with unique values) for this table.
Each row of this table indicates the name of an employee, their department, and the id of their manager.
If managerId is null, then the employee does not have a manager.
No employee will be the manager of themself.
 */

SELECT mgr.name
FROM leetcode.employee_570 mgr
JOIN leetcode.employee_570 rep ON rep."managerId" = mgr.id
GROUP BY mgr.name
HAVING COUNT(*) >=5