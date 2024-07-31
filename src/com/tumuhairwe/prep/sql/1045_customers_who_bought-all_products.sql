/**
  Table: Customer

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| customer_id | int     |
| product_key | int     |
+-------------+---------+
This table may contain duplicates rows.
customer_id is not NULL.
product_key is a foreign key (reference column) to Product table.


Table: Product

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_key | int     |
+-------------+---------+
product_key is the primary key (column with unique values) for this table.


Write a solution to report the customer ids from the Customer table that bought all the products in the Product table.

Return the result table in any order.
 */
SELECT DISTINCT customer_id
FROM leetcode.customer_1045
GROUP BY customer_id
HAVING count(DISTINCT product_key) = (SELECT count(product_key) FROM leetcode.product_1045)