# EX_1 
SELECT id,first_name,last_name, job_title 
FROM employees;

# EX_2
SELECT 
id, 
(first_name +" " + last_name) ASfull_name, 
job_title, 
salary
FROM employees
WHERE salary >1000.00;

# EX_3
UPDATE employees 
SET  salary=salary + 100
WHERE job_title = 'Manager';

# EX_4
SELECT * 
 FROM employees 
 ORDER BY salary DESC 
 LIMIT 1;
 
 # EX_5
 SELECT * FROM employees
 WHERE department_id=4 AND salary >=1000
 ORDER BY id;
 
 # EX_6
DELETE FROM employees 
WHERE department_id=1 
AND department_id=2;

