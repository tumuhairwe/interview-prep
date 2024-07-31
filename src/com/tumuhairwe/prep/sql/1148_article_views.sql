/**
  able: Views

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| article_id    | int     |
| author_id     | int     |
| viewer_id     | int     |
| view_date     | date    |
+---------------+---------+
There is no primary key (column with unique values) for this table, the table may have duplicate rows.
Each row of this table indicates that some viewer viewed an article (written by some author) on some date.
Note that equal author_id and viewer_id indicate the same person.


Write a solution to find all the authors that viewed at least one of their own articles.

Return the result table sorted by id in ascending order.
 */
SELECT DISTINCT v1.author_id AS id
FROM leetcode."Views" v1
WHERE v1.viewer_id IN (SELECT v2.viewer_id FROM leetcode."Views" v2 WHERE v2.viewer_id = v1.author_id)
ORDER BY author_id
