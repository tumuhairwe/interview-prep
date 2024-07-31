/**
  1517. Find Users With Valid E-Mails
Solved
Easy
Topics
Companies
SQL Schema
Pandas Schema
Table: Users

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| name          | varchar |
| mail          | varchar |
+---------------+---------+
user_id is the primary key (column with unique values) for this table.
This table contains information of the users signed up in a website. Some e-mails are invalid.


Write a solution to find the users who have valid emails.

A valid e-mail has a prefix name and a domain where:

The prefix name is a string that may contain letters (upper or lower case), digits, underscore '_', period '.', and/or dash '-'. The prefix name must start with a letter.
The domain is '@leetcode.com'.
Return the result table in any order.
 */
SELECT *
FROM leetcode.users_1517
-- Note that we also escaped the `@` character, as it has a special meaning in some regex flavors
-- WHERE users_1517.email ~ '^[a-zA-Z][a-zA-Z0-9_.-]*\\@leetcode\\.com$';
WHERE (position('@' in email) > 1
    or position('_' in email) > 1
    OR position('.' in email) > 1)
AND position('#' in email) = 0