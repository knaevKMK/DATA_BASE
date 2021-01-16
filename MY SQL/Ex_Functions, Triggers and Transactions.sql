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
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town (	town_name VARCHAR(50))
BEGIN
	SELECT e.first_name, e.last_name FROM employees AS e
    JOIN addresses AS a ON e.address_id=a.address_id
    JOIN towns As t ON a.town_id=t.town_id    
	WHERE t.`name`= town_name
    ORDER BY e.first_name, e.last_name, e.employee_id; 
END $$
DELIMITER ;

CALL usp_get_employees_from_town('Sofia');
DROP PROCEDURE usp_get_employees_from_town;

#EX_5

# if-else
DELIMITER $$
CREATE FUNCTION  ufn_get_salary_level(salary DOUBLE)
RETURNS VARCHAR (7)
DETERMINISTIC
	BEGIN
		DECLARE `level` VARCHAR(7);
        
		IF salary < 30000 THEN SET `level`:='Low';
		ELSEIF salary < 50000 THEN SET  `level`:='Average';
		ELSE  SET `level`:= 'High';
		END IF;
        
		RETURN `level`;
	END $$
DELIMITER ;

SELECT  ufn_get_salary_level(13500.00) ;
SELECT  ufn_get_salary_level(43300.00);
SELECT  ufn_get_salary_level(125500.00);

DROP FUNCTION ufn_get_salary_level;

 # cse 
DELIMITER $$
CREATE FUNCTION  ufn_get_salary_level(salary DOUBLE)
RETURNS VARCHAR (7)
DETERMINISTIC
	BEGIN
		DECLARE `level` VARCHAR(7);
        CASE
		WHEN salary < 30000 THEN SET `level`:='Low';
		WHEN salary < 50000 THEN SET  `level`:='Average';
		ELSE  SET `level`:= 'High';
		END CASE;
	
		RETURN `level`;
	END $$
DELIMITER ;

#EX_6
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level (`level` VARCHAR (10))
	BEGIN
		SELECT e.first_name, e.last_name 
        FROM employees AS e
        WHERE 
        CASE
			 WHEN`level`= 'Low' THEN e.salary<30000
			 WHEN `level`= 'Average' THEN e.salary<=50000 AND e.salary >30000
			 ELSE e.salary>50000
			 END
        ORDER BY e.first_name DESC, e.last_name DESC;
	END $$
DELIMITER ;

CALL usp_get_employees_by_salary_level('High');
DROP PROCEDURE usp_get_employees_by_salary_level;


DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level (`level` VARCHAR (10))
	BEGIN
		SELECT e.first_name, e.last_name 
        FROM employees AS e
        WHERE ufn_get_salary_level(e.salary)=`level`
        ORDER BY e.first_name DESC, e.last_name DESC;
	END $$
DELIMITER ;

#EX_7
#function
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))  
RETURNS BOOLEAN
DETERMINISTIC
RETURN word REGEXP (concat('^[', set_of_letters, ']+$'));

SELECT ufn_is_word_comprised('a','ba');
SELECT ufn_is_word_comprised('oistmiahf', 'Sofia') AS result;
SELECT ufn_is_word_comprised('oistmiahf', 'halves');
SELECT ufn_is_word_comprised('bobr', 'Rob');
SELECT ufn_is_word_comprised('pppp', 'Guy');
DROP FUNCTION ufn_is_word_comprised;

# procedure
DELIMITER $$
CREATE PROCEDURE ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))  
BEGIN
   SELECT set_of_letters,word, (word REGEXP  concat('^[', set_of_letters, ']+$')) AS reslt;
END $$
DELIMITER ;

CALL ufn_is_word_comprised('a','ba');
CALL ufn_is_word_comprised('oistmiahf', 'Sofia');
CALL ufn_is_word_comprised('oistmiahf', 'halves');
CALL ufn_is_word_comprised('bobr', 'Rob');
CALL ufn_is_word_comprised('pppp', 'Guy');
DROP PROCEDURE ufn_is_word_comprised;

