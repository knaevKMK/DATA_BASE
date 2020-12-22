/*Problem 2.	Find All Information About Departments*/
SELECT * 
FROM department;

/*Problem 3 Problem 3.	Find all Department Names */
SELECT name 
FROM department;

/*Problem 4 Find salary of Each Employee*/
SELECT first_name, last_name, salary 
FROM employees;

/* Problem 5 Problem 5.	Find Full Name of Each Employee*/
SELECT first_name, middle_name, last_name
FROM employees;

/* Problem 6 Problem 6.	Find Email Address of Each Employee */
SELECT (first_name + `.` + last_name +`softuni.bg`) AS Full_email_address
FROM employees;

/* Problem 7 Find All Different Employee’s Salaries */
SELECT DISTINCT salary
FROM employees e;

/* Problem 8 	Find all Information About Employees */
SELECT *
FROM employees
WHERE job_title = `Sales Representative`;

/* Problem 9 Problem 9.	Find Names of All Employees by salary in Range*/
SELECT e.first_name, e.last_name, e.job_title 
FROM employees e
WHERE salary BETWEEN 20000 AND 30000;

/* Problem 10.	Find Names of All Employees by salary in Range*/

SELECT (e.first_name +` `+ e.middle_name +` `+ e.last_name) AS `full_name`
FROM	employees e
WHERE salary IN (25000, 14000, 12500, 23600);

/*Problem 11.	 Find All Employees Without Manager*/
SELECT first_name, last_name
FROM employees e
WHERE e.manager_id IS NULL;


