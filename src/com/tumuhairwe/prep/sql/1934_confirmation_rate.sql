/**
  ref: https://leetcode.com/problems/confirmation-rate/description/

  Table: Signups

+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
+----------------+----------+
user_id is the column of unique values for this table.
Each row contains information about the signup time for the user with ID user_id.


Table: Confirmations

+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
| action         | ENUM     |
+----------------+----------+
(user_id, time_stamp) is the primary key (combination of columns with unique values) for this table.
user_id is a foreign key (reference column) to the Signups table.
action is an ENUM (category) of the type ('confirmed', 'timeout')

Each row of this table indicates that the user with ID user_id requested a confirmation message at time_stamp and that
  confirmation message was either confirmed ('confirmed') or expired without confirming ('timeout').
*/

SELECT s.user_id,
       ROUND(
                count(c.action) FILTER (WHERE c.action = 'confirmed')::numeric
            / count(*)::numeric
           , 2) AS confirmation_rate
FROM leetcode.signups_1934 s
LEFT JOIN leetcode.confirmation_1934 c ON c.user_id = s.user_id
GROUP BY s.user_id