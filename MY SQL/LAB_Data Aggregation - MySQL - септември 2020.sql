#EX_1
SELECT e.department_id, 
count (emplyee_id)  AS `Number of employees`
FROM employees AS e
GROUP BY e.department_id
ORDER BY e.department_id;

#EX_2
SELECT e.department_id,
ROUND(avg(salary),2) AS `Average Salary`
FROM employees AS e
GROUP BY e.department_id
ORDER BY e.department_id;

#EX_3
SELECT e.department_id,
ROUND(MIN(salary),2) AS `Min Salary`
FROM employees AS e
HAVING `Min Salary`>800
ORDER BY e.department-id;

#EX_4
SELECT COUNT(*) AS `Count`
FROM products
WHERE category_id=2 AND price>8;

#EX_5
SELECT category_id,
ROUND(AVG(price),2) AS `Average Price`,
ROUND(MIN(price),2) AS `Cheapest Product`,
ROUND(MAX(price),2) AS `Most Expensive Product`
FROM products
ORDER BY category_id;
