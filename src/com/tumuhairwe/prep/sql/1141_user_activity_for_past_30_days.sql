-- https://leetcode.com/problems/user-activity-for-the-past-30-days-i/?envType=study-plan-v2&envId=top-sql-50
-- 1. SELECT the columns to display
-- 2. define the condition of the dates using a WHERE clause
-- 3. GROUP  the results by date
select activity_date as day,
        count(DISTINCT  user_id) as active_users
from leetcode.activity_1141
WHERE activity_date > '2019-06-28'
  AND activity_date <= '2019-07-27'
GROUP BY activity_date;
