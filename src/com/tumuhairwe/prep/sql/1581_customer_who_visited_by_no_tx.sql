SELECT v.customer_id, count(v.visit_id) as count_no_trans
FROM leetcode.visits_1581 v
WHERE v.visit_id NOT IN (SELECT t.transaction_id
    FROM leetcode.transactions_1581 t)
GROUP BY customer_id