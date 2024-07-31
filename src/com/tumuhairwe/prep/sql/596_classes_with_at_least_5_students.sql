SELECT class
FROM leetcode.courses_596
GROUP BY class
HAVING count(student) >= 5