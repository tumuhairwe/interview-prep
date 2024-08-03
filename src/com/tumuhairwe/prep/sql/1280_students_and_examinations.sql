-- Write your PostgreSQL query statement below
SELECT s.student_id, s.student_name, sub.subject_name, COUNT(e.student_id) AS attended_exams
FROM leetcode.students_1280 s
CROSS JOIN leetcode.subjects_1280 sub
LEFT JOIN leetcode.examinations_1280 e ON s.student_id = e.student_id AND sub.subject_name = e.subject_name
GROUP BY s.student_id, student_name, sub.subject_name
ORDER BY s.student_id, sub.subject_name;

ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO verifiq_app;
    --verifiq_app