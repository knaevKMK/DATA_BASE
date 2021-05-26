-- 1. Find All Information About Departments 
SELECT * FROM departments ORDER BY department_id;

-- 2. Find all Department Names 
SELECT `name` FROM departments ORDER BY department_id;

-- 3. Find salary of Each Employee 
SELECT first_name,last_name,salary FROM employees ORDER BY employee_id;

-- 4. Find Full Name of Each Employee 
SELECT first_name,middle_name,last_name FROM employees ORDER BY employee_id;

-- 5. Find Email Address of Each Employee 
SELECT CONCAT(first_name, ".", last_name,"@softuni.bg") as full_email_address FROM employees;

-- 6. Find All Different Employee's Salaries 
SELECT DISTINCT salary as Salary FROM employees ORDER BY employee_id;

-- 7. Find all Information About Employees 
SELECT *FROM employees WHERE job_title= 'Sales Representative' ORDER BY employee_id;

-- 8. Find Names of All Employees by salary in Range 
SELECT first_name, last_name, job_title AS 'JobTitle' FROM employees
WHERE salary BETWEEN 20000 AND 30000
ORDER BY employee_id;

-- 9.  Find Names of All Employees 
SELECT CONCAT(first_name," ", middle_name, " ", last_name) AS FullName FROM employees
WHERE salary IN (25000,14000,12500,23600)
ORDER BY employee_id;

-- 10. Find All Employees Without Manager 
SELECT first_name, last_name FROM employees
WHERE manager_id is NULL;

-- 11. Find All Employees with salary More Than 50000 
SELECT first_name, last_name, salary FROM employees 
WHERE salary>50000 ORDER BY salary DESC;

-- 12. Find 5 Best Paid Employees 
SELECT first_name, last_name FROM employees
ORDER BY salary DESC LIMIT 5;