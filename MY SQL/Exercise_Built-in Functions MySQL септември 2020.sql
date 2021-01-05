# EX_1 
SELECT first_name, last_name FROM employees
WHERE first_name LIKE 'Sa%'
# WHERE LEFT (fist_name, 2) = 'Sa'
ORDER BY employee_id;

# EX_2
SELECT first_name, last_name FROM employees
WHERE last_name LIKE '%ei%'
ORDER BY employee_id;


