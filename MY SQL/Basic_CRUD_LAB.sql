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
SELECT first_name, middle_name, alst_name
FROM employees;

/* Problem 6 Problem 6.	Find Email Address of Each Employee */
SELECT (first_name + `.` + last_name +`softuni.bg`) AS Full_email_address
FROM employees;

/* Problem 7 Find All Different Employee’s Salaries */
SELECT DISTINCT salary
FROM employees e;

/* Problem 8 Problem 8.	Find all Information About Employees */
SELECT *
FROM employees
