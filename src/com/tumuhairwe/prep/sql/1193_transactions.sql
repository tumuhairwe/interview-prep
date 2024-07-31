/**
  Table: Transactions

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| country       | varchar |
| state         | enum    |
| amount        | int     |
| trans_date    | date    |
+---------------+---------+
id is the primary key of this table.
The table has information about incoming transactions.
The state column is an enum of type ["approved", "declined"].


Write an SQL query to find for each month and country, the number of transactions and their total amount, the number of approved transactions and their total amount.

Return the result table in any order.
*/

SELECT
    TO_CHAR(trans_date, 'YYYY-MM') as month,
    country,
    count(*) AS trans_count,
    count(state) FILTER(WHERE state ='approved') AS approved_count,
    sum(amount) AS trans_total_amount,
    COALESCE(sum(amount) FILTER ( WHERE state = 'approved' ), 0) AS approved_total_amount
FROM leetcode.transactions_1193
GROUP BY month, country