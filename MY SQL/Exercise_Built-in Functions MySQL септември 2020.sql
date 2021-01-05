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