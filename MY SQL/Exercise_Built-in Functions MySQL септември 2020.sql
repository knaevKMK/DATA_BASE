# EX_1 
SELECT first_name, last_name FROM employees
WHERE first_name LIKE 'Sa%'
# WHERE LEFT (fist_name, 2) = 'Sa'
ORDER BY employee_id;

# EX_2
SELECT first_name, last_name FROM employees
WHERE last_name LIKE '%ei%'
ORDER BY employee_id;

# EX_3
SELECT fist_name FROM employees
WHERE deprtment_id IN (3,10) 
	AND YEAR (hire_year) BETWEEN 1995 AND 2005
ORDER BY employee_id;


#EX_4
SELECT first_name, last_name FROM employees
WHERE job_title NOT LIKE '%engineer%'
ORDER BY employee_id;

#EX_5
SELECT `name` FROM towns
# WHERE CHAR_LENGTH(`name`) =5 OR CHAR_LENGTH(`name`)=6 
#WHERE CHAR_LENGTH(`name`) BETWEEN 5 AND 6 
WHERE CHAR_LENGTH(`name`) IN (5,6)
ORDER BY `name` ASC;

#EX_6
SELECT town_id, `name` FROM towns
#WHERE LEFT(`name`,1) IN ('M%','K%', 'B%','R%')
WHERE SUBSTRING(`name`,1,1) IN ('M%','K%', 'B%','R%')
ORDER BY `name` ASC;

#EX_7
SELECT town_id, `name` FROM towns
WHERE LEFT(`name`,1) NOT IN ('R', 'B', 'D')
ORDER BY `name` ASC;

#EX_8
CREATE VIEW `v_employees_hired_after_2000` AS
SELECT first_name, last_name FROM employees
WHERE YEAR(hire_year) > 2000;

SELECT  * FROM v_employees_hired_after_2000;

#EX_9
SELECT first_name, last_name FROM employees
WHERE CHAR_LENGTH(last_name, 5);

#EX_10
SELECT country_name, iso_code FROM countries
WHERE country_name LIKE '%A%A%A%'
ORDER BY iso_code ASC;

#EX_11
SELECT p.peak_name, r.river_name, 
(LOWER(concat(p.peak_name, SUBSTRING(r.river_name,2)))) AS mix 
FROM peak p , river r
WHERE RIGHT (p.peak_name,1) = LEFT (r.river_name,1)
ORDER BY mix ASC;

#EX_12
SELECT `name`, `start` FROM diablo
WHERE YEAR(`start`) IN (2011,2012)
ORDER BY `start`, `name`
LIMIT 50;

#EX_13
SELECT user_name, SUBSTRING(email, LOCATE('@', email)+1) AS `Email Provider`
FROM `user`
ORDER BY `Email Provider`, `user_name`;

#EX_14
SELECT user_name, ip_address FROM `user`
WHERE ip_address LIKE '&quot;___.1%.%.___&quot;.'
ORDER BY user_name ASC;

#EX_15
SELECT `name`, 
 (
 CASE
 WHEN HOUR(`start`) BETWEEN 0 AND 11 THEN 'Morning'
 WHEN HOUR(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
 ELSE 'Evening'
 END
 ) AS `Duration`,
  (
 CASE
 WHEN `duration` BETWEEN 0 AND 3 THEN 'Extra Short'
 WHEN `duration` BETWEEN 4 AND 6 THEN 'Short'
 WHEN `duration` BETWEEN 7 AND 10 THEN 'Long'
 ELSE 'Extra Long'
 END
 ) AS `Duration`
FROM games;

#EX_16

