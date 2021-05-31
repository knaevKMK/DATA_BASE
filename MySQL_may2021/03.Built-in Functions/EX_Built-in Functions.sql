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