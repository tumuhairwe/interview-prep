# LeetCode 2356
# ref: https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/description/

# Given (subject_id, dept_id) is the primary key (combinations of columns with unique values) of this table.
# Each row in this table indicates that the teacher with teacher_id teaches the subject subject_id in the department dept_id.

# Write a solution to calculate the number of unique subjects each teacher teaches in the university.

SELECT teacher, count(DISTINCT subject_id) AS cnt
FROM teacher
GROUP BY teacher_id