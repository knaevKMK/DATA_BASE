# EX_1
SELECT * FROM Departments ORDER BY department_id;

# EX_2
SELECT department_name AS name FROM Departments ORDER BY department_id;

#EX_3
SELECT first_name, last_name, salary FROM Employeess ORDER BY id;

#EX_4
SELECT fisrt_name, middle_name, last_name FROM Employees ORDER BY id;

#EX_5
SELECT (first_name + '.' + last_name + '@softuni.bg') AS full_email_address
FROM Employees;

#EX_6
SELECT DISTINCT salary AS Salary FROM Employees ORDER BY id;

#EX_7
SELECT
id,
first_name AS `First Name`,
last_name AS`Last Name`,
middle_name AS`Middle Name`,
job_title AS `Job Title`,
department_id AS `Depart ID`,
manager_id AS `Mngr ID`,
hire_date AS `Hire Date`,
salary,
address_id
 FROM Employees 
 WHERE job_title = 'Sales Representative' 
 ORDER BY id;
 
 #EX_8
 SELECT first_name,last_name,job_title 
 FROM Employees 
 WHERE salary BETWEEN 20000 AND 30000
 ORDER BY id;
 
 #EX_9
 SELECT (first_name + ' ' + middle_name + ' ' + last_name) AS `Full Name`
 FROM Employees
 WHERE salary = 25000 OR salary=14000 OR salary = 12500 OR salary=23600;
 #(25000,14000,12500,23600);
 
 #EX_10
 SELECT first_name, last_name FROM Employees
 WHERE manager_id IS NULL;
 
 #EX_11
 SELECT first_name, last_name, salsry
 FROM Employees
 WHERE salary > 50000
 ORDER BY salary DESC;
 
 #EX_12
SELECT first_name, last_name FROM Employees
ORDER BY salary DESC
LIMIT 5,1;

# EX_13
SELECT first_name, last_name FROM Employees
WHERE NOT deprtment_id =4;

# EX_14
SELECT * FROM Employees
ORDER BY salary DESC, first_name ASC, last_name DESC, middle_name ASC, id;

# EX_15
CREATE VIEW v_employees_job_salaries AS
SELECT first_name, last_name, salary
FROM  Employees;

# EX_16
CREATE VIEW v_employees_job_titles AS
SELECT (first_name + ' ' + middle_name + ' ' + last_name) AS `full_name`,
job_title,
CASE middle_name WHEN NULL THEN ''
END
FROM Employees;

# EX_17
SELECT DISTINCT job_title FROM Employees
ORDER BY job_title ASC;

# EX_18
SELECT * FROM Projects
ORDER BY start_date ASC, `name` ASC, id ASC
LIMIT 10,1;

# EX_19
SELECT first_name, last_name, hire_date
FROM Employees
ORDER BY hire_date DESC
LIMIT 7,1;

# EX_20
UPDATE Employees
SET salary = salary*1.12
# the Engineering, Tool Design, Marketing orInformation Services department
WHERE depatment_id= 1 OR department_id=2 OR department_id=3 OR department_id=4;#(1,2,3,4);

# EX_21
SELECT peak_name FROM `Mountain Peak` ORDER BY peak_name ASC;

# EX_22
SELECT country_name, population FROM Countries
WHERE country= 'Europe'
ORDER BY population DESC, country_name ASC
LIMIT 30,1;

# EX_23 
SELECT country_name, country_code, currency,
CASE currency WHEN 'EUR' THEN 'Euro'
	 ELSE 'Not Euro'
	 END
 FROM Countries
 ORDER BY country_name ASC;
 
 USE Diablo;
 # EX_23
 SELECT `name` FROM Characters;
 
 