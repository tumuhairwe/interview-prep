/**
  Table: Weather

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id is the column with unique values for this table.
There are no different rows with the same recordDate.
This table contains information about the temperature on a certain day.


Write a solution to find all dates' Id with higher temperatures compared to its previous dates (yesterday).

Return the result table in any order.
 */

-- MySQL
-- SELECT w1.id
-- FROM leetcode.weather_197 w1 JOIN leetcode.weather_197 w2
-- ON w1."recordDate" - 1 = w2."recordDate"
-- WHERE w2.temperature < w2.temperature

SELECT current_day.id
FROM leetcode.weather_197 AS current_day
WHERE EXISTS(
    SELECT 1
    FROM leetcode.weather_197 AS yesterday
    WHERE current_day.temperature > yesterday.temperature
    AND current_day."recordDate" = yesterday."recordDate" + 1
          )