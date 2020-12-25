/*EX_1*/
CREATE DATABASE minions;

/*EX_2*/
CREATE TABLE minions(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NULL,
age INT NULL
);
CREATE TABLE towns(
town_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

/*EX_3*/
ALTER TABLE towns
CHANGE town_id id INT AUTO_INCREMENT;

ALTER TABLE minions
ADD COLUMN town_id INT NOT NULL,
ADD CONSTRAINT fk_towns_id
FOREIGN KEY (town_id)
REFERENCES towns (id);

/*EX_4*/
INSERT INTO towns (`name`) VALUES 
('Sofia'),
('Plovdiv'),
('Varna');

INSERT INTO minions (`name`, age,town_id)
VALUES ('Kevin',22,1),
('Bob',15,3),
('Steward', NULL,2);

/*EX_5*/
TRUNCATE TABLE minions;

/*EX_6*/
DROP TABLES towns, minions;

/*EX_*/

/*EX_*/