USE hotel;


#EX_1
SELECT id, first_name, last_name, job_title FROM employees ORDER BY id;

#EX-2
SELECT id, concat(first_name," " , last_name) as full_name, job_title,salary 
FROM employees 
WHERE salary >1000
ORDER BY id;

#EX_3

UPDATE employees 
SET 
    salary = salary + 100
WHERE job_title='Manager';

USE hotel;

SELECT 
    salary
FROM
    employees;
    
    
SET SQL_SAFE_UPDATES = 0;

#EX_4
SELECT * FROM employees 
WHERE job_title='Manager'
ORDER BY salary DESC 
LIMIT 1;

#EX_5
SELECT * FROM employees
WHERE department_id=4 AND salary >=1000
ORDER BY id;

#EX_6
DELETE FROM employees 
WHERE
    department_id IN (1,2);

SELECT 
    *
FROM
    employees
ORDER BY id;
