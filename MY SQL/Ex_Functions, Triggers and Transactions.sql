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

#EX_8
USE bank;

DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name ()
BEGIN
	SELECT CONCAT( first_name, ' ', last_name) AS full_name
    FROM  account_holders AS ah
    ORDER BY full_name, id;
END $$
DELIMITER ;

CALL usp_get_holders_full_name ();
DROP PROCEDURE usp_get_holders_full_name ;

#EX_9
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(line DOUBLE)
BEGIN
	SELECT first_name, last_name
    FROM account_holders AS ah
    JOIN accounts AS a ON ah.id=a.account_holder_id
    WHERE a.balance > line
    ORDER BY first_name, last_name, ah.id;
END $$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);
DROP PROCEDURE usp_get_holders_with_balance_higher_than;

#EX_10
DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(
initial_sum DECIMAL (19,4) , interest_rate DECIMAL (19,4), years INT)
RETURNS DECIMAL (19,4)
DETERMINISTIC
BEGIN
   RETURN initial_sum * POW((1 +interest_rate),years);
END $$
DELIMITER ;
    
SELECT ufn_calculate_future_value ( 1000, 0.1, 5);
DROP FUNCTION ufn_calculate_future_value;

#EX_11 use function from EX_10

DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account (account_id INT, interest_rate DECIMAL(19,4))
 BEGIN
	SELECT 
		a.id AS account_id, first_name, last_name, 
        a.balance AS current_balance, 
        (SELECT ufn_calculate_future_value(current_balance, interest_rate ,5))
		AS balance_in_5_years
	FROM accounts AS a
    JOIN account_holders AS ah ON a.account_holder_id=ah.id
    WHERE a.id=1;
 END $$
DELIMITER ;

CALL usp_calculate_future_value_for_account (1, 0.1);
DROP PROCEDURE usp_calculate_future_value_for_account;

#EX_12

DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL (19,4))
BEGIN
	IF money_amount >0 THEN START TRANSACTION;
    
		UPDATE accounts AS a
		SET a.balance= a.balance + money_amount
		WHERE a.id=account_id;
		
		IF (SELECT a.balance FROM accounts AS a
			WHERE a.id=account_id) <0 
			THEN ROLLBACK;
		ELSE COMMIT;
		END IF;
      END IF;
END $$
DELIMITER ;



CALL usp_deposit_money(1,110);

SELECT A.ID AS account_id, a.account_holder_id, a.balance
FROM accounts AS a
WHERE a.id=1;

DROP PROCEDURE usp_deposit_money;

#EX_13
DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL (19,4))
BEGIN
	IF money_amount <= (SELECT a.balance FROM accounts AS a WHERE  a.id=account_id)
    AND money_amount >0
		THEN START TRANSACTION;
        UPDATE accounts AS a2
        SET a2.balance= a2.balance - money_amount
        WHERE a2.id=account_id;
        
        IF money_amount > (SELECT a3.balance FROM accounts AS a3 WHERE  a3.id=account_id 
    AND money_amount <0)
        THEN ROLLBACK;
        ELSE COMMIT;
        END IF;
	END IF;
END $$
DELIMITER ;

CALL usp_withdraw_money(1,100);
DROP PROCEDURE usp_withdraw_money;
SELECT * FROM accounts WHERE id=1;

#EX_14
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT , money_amount DECIMAL(19,4))
BEGIN
	IF money_amount>0  
		AND from_account_id <> to_account_id
        AND (SELECT a.id FROM accounts AS a WHERE id= from_account_id) IS NOT NULL 
        AND (SELECT a.id FROM accounts AS a WHERE id= to_account_id) IS NOT NULL 
		AND (SELECT balance FROM accounts WHERE id= from_account_id) >= money_amount
    THEN   START TRANSACTION;
		UPDATE accounts AS a
        SET a.balance= a.balance + money_amount
        WHERE a.id= to_account_id;
        
		UPDATE accounts AS a
        SET a.balance= a.balance - money_amount
        WHERE a.id= from_account_id;
		IF (SELECT a.balance FROM accounts AS a WHERE id= from_account_id) < money_amount
			THEN ROLLBACK;
		ELSE COMMIT;
		END IF; 
	END IF;
END $$ 
DELIMITER ;

CALL usp_transfer_money (1, 2, 20);
CALL usp_transfer_money (2, 1, 20);

SELECT * FROM accounts WHERE id=1 OR id=2;
DROP PROCEDURE usp_transfer_money;

#EX_15
CREATE TABLE `balance_logs` (
log_id INT PRIMARY KEY AUTO_INCREMENT, 
account_id INT NOT NULL, 
old_sum DECIMAL (19,4) NOT NULL , 
new_sum DECIMAL (19,4) NOT NULL
);

DELIMITER $$
CREATE TRIGGER tr_balance_logs
AFTER UPDATE ON accounts
FOR EACH ROW
BEGIN
	IF OLD.balance <> NEW.balance THEN INSERT INTO `balance_logs` 
		(account_id, old_sum, new_sum)
		VALUES (OLD.id, OLD.balance, NEW.balance);
	END IF;
END $$
DELIMITER ;

SELECT * FROM balance_logs;
DROP TRIGGER tr_balance_logs;
DROP TABLE balance_logs;
CALL usp_deposit_money(1,110);

#EX_16

CREATE TABLE notification_emails(
id INT PRIMARY KEY AUTO_INCREMENT, 
recipient INT, 
`subject` TEXT, 
body TEXT 
);

DELIMITER $$
CREATE TRIGGER tr_notification_email
AFTER UPDATE ON accounts
FOR EACH ROW
BEGIN
		INSERT INTO notification_emails (recipient, `subject`, body) VALUES
        (NEW.id,  
        CONCAT('Balance change for account: ', NEW.id), 
			CONCAT('On ', DATE_FORMAT(NOW(), '%b %d %Y at %r') , ' your balance was changed from ',
             ROUND(NEW.balance),' to ', ROUND(NEW.balance),'.'));
END $$
DELIMITER ;


DROP TRIGGER tr_notification_email;

SELECT * FROM notification_emails;

CALL usp_transfer_money (1, 2, 20);
CALL usp_transfer_money (2, 1, 20);