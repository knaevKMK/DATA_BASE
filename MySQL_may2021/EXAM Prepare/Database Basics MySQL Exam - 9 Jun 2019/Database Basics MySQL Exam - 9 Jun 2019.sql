CREATE SCHEMA IF NOT EXISTS ruk_database;
USE ruk_database;


CREAtE TABLE clients(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
full_name VARCHAR(50) NOT NULL,
age INT (11) NOT NULL
);

CREATE TABLE branches(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name`VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE employees(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(10,2) NOT NULL,
started_on DATE NOT NULL,
branch_id INT(11) NOT NULL,
CONSTRAINT fk_employees_branches
FOREIGN KEY (branch_id) REFERENCES branches(id)
);

CREATE TABLE employees_clients (
    employee_id INT(11),
    client_id INT(11),
  
    CONSTRAINT fk_employees_clients_emplyees FOREIGN KEY (employee_id) REFERENCES employees(id),
    CONSTRAINT fk_employees_clients_clients FOREIGN KEY (client_id) REFERENCES clients(id)
);
CREATE TABLE bank_accounts(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
account_number VARCHAR(10) NOT NULL,
balance DECIMAL (10,2) NOT NULL,
client_id INT(11) UNIQUE NOT NULL,
CONSTRAINT fk_bank_accounts_clients FOREIGN KEY (client_id) 
REFERENCES clients(id)
);

CREATE TABLE cards(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
card_number VARCHAR(19) NOT NULL,
card_status VARCHAR(7) NOT NULL,
bank_account_id INT(11) NOT NULL,
CONSTRAINT fk_cards_bank_accounts
FOREIGN KEY (bank_account_id)
REFERENCES bank_accounts(id)
);


#EX_2
-- You will have to insert records of data into the cards table, based on the clients table. 
-- For clients with id between 191 and 200 (inclusive), insert data in the cards table with the following values:
-- •	card_number – set it to full name of the client, but reversed!
-- •	card_status – set it to "Active".
-- •	bank_account_id –set it to client's id value. 
INSERT INTO cards (card_number, card_status,bank_account_id) 
SELECT 
REVERSE(cl.full_name),
'Active' AS card_status,
 cl.id
 FROM clients AS cl
WHERE cl.id BETWEEN 191 AND 200;

SELECT * FROM cards WHERE bank_account_id BETWEEN 191 AND 200;
DELETE cards FROM cards WHERE bank_account_id BETWEEN 191 AND 200;

-- 03.	Update
-- Update all clients which have the same id as the employee they are appointed to. Set their employee_id with the employee with the lowest count of clients.
-- If there are 2 such employees with equal count of clients, take the one with the lowest id.

-- UPDATE  employees_clients AS ec
-- SET ec.employee_id =(

-- )
-- WHERE ec.emplyee_id=ec.client_id;

-- 04.	Delete
-- R.U.K. Bank is a sophisticated network. As such, it cannot allow procrastination and lazy behavior. 
-- Delete all employees which do not have any clients. 
DELETE e FROM employees AS e
JOIN employees_clients AS ec ON e.id = ec.employee_id
WHERE ec.client_id IS NULL;

#EX_5 
SELECT id, full_name FROM clients ORDER BY id;

#EX_6
SELECT e.id, CONCAT(e.first_name, ' ', e.last_name) AS full_name,
CONCAT('$',e.salary) AS salary,
e.started_on
FROM employees AS e
WHERE e.salary>=100000 AND YEAR (e.started_on) >=2018
ORDER BY e.salary DESC, e.id ASC;

#EX_7
SELECT c.id, CONCAT(c.card_number, ' : ', cl.full_name ) AS card_token
FROM cards AS c
JOIN bank_accounts AS ba ON c.bank_account_id = ba.id
JOIN clients AS cl ON ba.client_id = cl.id
ORDER BY c.id DESC;

#EX_8
SELECT 
	CONCAT(e.first_name, ' ', e.last_name) AS `name`,
    e.started_on,
    COUNT(ec.client_id) AS count_of_clients
FROM employees_clients AS ec
JOIN employees AS e ON ec.employee_id = e.id
GROUP BY ec.employee_id
ORDER BY count_of_clients DESC, ec.employee_id
LIMIT 5;

#EX_9

SELECT b.`name`, COUNT(ca.id) AS count_of_cards
FROM branches AS b
JOIN employees AS e ON b.id=e.branch_id
JOIN employees_clients AS ec ON e.id = ec.employee_id
JOIN clients AS cl ON ec.client_id =cl.id
JOIN bank_accounts AS ba ON cl.id=ba.client_id
JOIN cards AS ca ON ba.id = ca.bank_account_id
GROUP BY b.`name`
ORDER BY count_of_cards DESC, b.`name`;


#EX_10

DELIMITER $$
CREATE FUNCTION  udf_client_cards_count(`name` VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
RETURN	(
SELECT COUNT(ca.id) FROM cards AS ca
	JOIN bank_accounts AS ba ON ca.bank_account_id=ba.id
	JOIN clients AS cl ON ba.client_id = cl.id
	WHERE cl.full_name= `name`); 
END $$
DELIMITER ;

SELECT c.full_name, udf_client_cards_count('Baxy David') as `cards` FROM clients c
WHERE c.full_name = 'Baxy David';

#EX_11
DELIMITER $$
CREATE PROCEDURE udp_clientinfo (search_name VARCHAR(30))
BEGIN
	SELECT cl.full_name, cl.age, ba.account_number, CONCAT( '$',ba.balance) AS balance
    FROM clients AS cl
    JOIN bank_accounts AS ba ON cl.id=ba.client_id
    WHERE cl.full_name= search_name;
END$$
DELIMITER ;
CALL udp_clientinfo ('Hunter Wesgate');