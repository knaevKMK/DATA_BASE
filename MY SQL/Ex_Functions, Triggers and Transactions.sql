#EX_1

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT first_name, last_name FROM employees AS e
    WHERE salaray >35000
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above_35000;
DROP PROCEDURE usp_get_employees_salary_above_35000;

#EX_2
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above (salary_limit DECIMAL(19,4))
BEGIN
	SELECT e.first_name, e.last_name FROM employees AS e
    WHERE e.salary >= salary_limit
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above (48100);
DROP PROCEDURE usp_get_employees_salary_above;

#EX_3
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with (town_start VARCHAR(1))
BEGIN
 SELECT t.name FROM towns AS t
 WHERE t.name LIKE concat(  town_start,'%');
END $$
DELIMITER ;

CALL usp_get_towns_starting_with ('b');
DROP PROCEDURE usp_get_towns_starting_with;

#EX_4

