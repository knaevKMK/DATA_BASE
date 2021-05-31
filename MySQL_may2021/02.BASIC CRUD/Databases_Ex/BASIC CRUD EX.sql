-- 1. Find All Information About Departments 
SELECT * FROM departments ORDER BY department_id;

-- 2. Find all Department Names 
SELECT `name` FROM departments ORDER BY department_id;

-- 3. Find salary of Each Employee 
SELECT first_name,last_name,salary FROM employees ORDER BY employee_id;

-- 4. Find Full Name of Each Employee 
SELECT first_name,middle_name,last_name FROM employees;

-- 5. Find Email Address of Each Employee 
SELECT CONCAT(first_name, ".", last_name,"@softuni.bg") as full_email_address FROM employees;

-- 6. Find All Different Employee's Salaries 
SELECT DISTINCT salary as Salary FROM employees ORDER BY employee_id;

-- 7. Find all Information About Employees 
SELECT *FROM employees WHERE job_title= 'Sales Representative' ORDER BY employee_id;

-- 8. Find Names of All Employees by salary in Range 
SELECT first_name, last_name, job_title AS 'JobTitle' FROM employees
WHERE salary> 20000 AND salary< 30000
ORDER BY employee_id;

-- 9.  Find Names of All Employees 
SELECT CONCAT(first_name," ", middle_name, " ", last_name) AS 'Full Name' FROM employees
WHERE salary =25000 OR
salary=14000 OR
salary=12500 OR
salary=23600
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

-- 13. Find All Employees Except Marketing 
SELECT first_name, last_name FROM employees 
WHERE department_id != 4;

-- 14. Sort Employees Table
SELECT 
employee_id AS id,
first_name AS 'First Name',
last_name AS 'Last Name',
middle_name AS 'Middle Name',
job_title,
department_id AS 'Dept ID',
manager_id AS 'Mngr ID',
hire_date AS 'Hire Date',
salary,
address_id
 FROM employees
ORDER BY salary DESC, first_name ASC,last_name DESC, middle_name ASC;

-- 15. Create View Employees with Salaries
CREATE VIEW v 
AS SELECT first_name, last_name,salary FROM employees;

SELECT * FROM v;

-- 16. Create View Employees with Job Titles 
CREATE VIEW v_employees_job_title AS
    SELECT 
        CONCAT(first_name,
                ' ',
				IFNULL(middle_name, '' ),
                ' ',
                last_name) AS full_name,
        job_title
    FROM
        employees;

SELECT * FROM v_employees_job_title ;

-- 17.  Distinct Job Titles 
SELECT DISTINCT job_title FROM employees ORDER BY job_title ASC;

-- 18. Find First 10 Started Projects 
SELECT * FROM projects
ORDER BY start_date ASC, `name`
LIMIT 10;

-- 19. Last 7 Hired Employees
SELECT first_name, last_name, hire_date FROM employees
ORDER BY hire_date DESC
LIMIT 7;

-- 20. Increase Salaries

UPDATE employees
SET salary = salary*1.12
WHERE department_id	IN(1,2,3,4);
--  ('Engineering', 'Tool Design', 'Marketing ', 'Information ');

SELECT salary FROM employees;

-- 21. All Mountain Peaks
SELECT peak_name FROM peaks ORDER BY peak_name ASC;

-- 22. Biggest Countries by Population
SELECT 
    country_name, population
FROM
    countries
WHERE
    continent_code = 'EU'
ORDER BY population DESC, country_name
LIMIT 30;

-- 23.  Countries and Currency (Euro / Not Euro) 
SELECT 
    country_name,
    country_code,
    CASE
        WHEN currency_code = 'EUR' THEN 'Euro'
        ELSE 'Not Euro'
    END AS currency
FROM
    countries
ORDER BY country_name;

-- 24.  All Diablo Characters 
SELECT `name`	FROM characters
ORDER BY `name`;