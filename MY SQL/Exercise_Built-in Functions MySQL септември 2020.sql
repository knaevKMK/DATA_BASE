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

