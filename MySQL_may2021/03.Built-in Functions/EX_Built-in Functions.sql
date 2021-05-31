#EX_1

SELECT first_name, last_name FROM employees
WHERE first_name LIKE 'Sa%'
ORDER BY employee_id;

#EX_2

SELECT first_name, last_name FROM employees
WHERE last_name LIKE '%ei%'
ORDER BY employee_id;

#EX_3

SELECT first_name FROM employees
WHERE department_id IN (3,10)
AND YEAR (hire_date) BETWEEN 1995 AND 2005
ORDER BY employee_id;

#EX_4

SELECT first_name, last_name FROM employees
WHERE job_title NOT LIKE ''
ORDER BY employee_id;

#EX_5
SELECT `name` FROM towns
WHERE char_length(`name`) BETWEEN 5 AND 6
ORDER BY `name`;

#EX_6

SELECT * FROM towns
WHERE LEFT(`name`,1)  IN ('M', 'K', 'B','E')
ORDER BY `name`;

#EX_7

SELECT town_id, `name` FROM towns
WHERE LEFT(`name`,1) NOT IN ('R', 'B', 'D')
ORDER BY `name` ASC;


#EX_8

CREATE VIEW v_employees_hired_after_2000 
AS SELECT first_name, last_name FROM employees
WHERE YEAR (hire_date) > 2000;

SELECT * FROM v_employees_hired_after_2000;

#EX_9

SELECT first_name, last_name FROM employees
WHERE char_length(last_name)=5;

#EX_10

SELECT country_name, iso_code FROM countries
WHERE country_name LIKE '%A%A%A%'
ORDER BY iso_code;

#EX_11
SELECT p.peak_name, river_name, (LOWER(concat(p.peak_name,r.river_name))) AS mix 
FROM peaks p, rivers r
WHERE right(p.peak_name,1) = left(r.river_name,1)
ORDER BY mix ;

#EX_12

SELECT `name`, `start` FROM games
WHERE YEAR(`start`) BETWEEN 2011 AND 2012
ORDER BY start
LIMIT 50;


#EX_13
SELECT user_name, substring(email, LOCATE ('@','email')+1) AS 'Email Provider' 
FROM users
ORDER BY 'Email Provider', user_name;

#EX_14
SELECT user_name, ip_address FROM users
WHERE ip_address LIKE '___.1%.%.___'
ORDER BY user_name ASC;

#EX_15

SELECT `name` AS game, 
(CASE
WHEN hour(`start`) BETWEEN 0 AND 12 THEN 'Morning'
WHEN hour (`start`) BETWEEN 12 AND 18 THEN 'Afternoon'
ELSE 'Evening'
END
) AS `Part of the Day`,
(CASE
WHEN duration <=3 THEN 'Extra Short'
WHEN duration BETWEEN 4 AND 6 THEN 'Short'
WHEN duration BETWEEN 7 AND 10 THEN 'Long'
ELSE 'Extra Long'
END
) AS `Duration`
 FROM games;
 
 #EX_16
 
 SELECT product_name,order_date,
 ADDDATE(order_date, interval 3 DAY)AS pay_due,
 ADDDATE(order_date, INTERVAL 1 MONTH) AS deliver_due FROM orders;