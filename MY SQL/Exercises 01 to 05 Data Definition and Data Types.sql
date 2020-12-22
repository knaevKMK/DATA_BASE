/* EX_1  Create new database minions*/
CREATE DATABASE Minions;
/* or */
CREATE SCHEMA Minions;
DROP SCHEMA Minions;

/* EX_2  Create new table  71/100*/
USE Minions;
CREATE TABLE `minions` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45)  NULL,
`age` VARCHAR (45)  NULL
 ); 
 CREATE TABLE `towns` (
`town_id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
`name` VARCHAR (45) NULL
);
 ALtER table  towns CHANGE   `town_id` `id` INT;
 ALTER TABLE minions add column town_id INT NOT NULL ;
 Alter table minions add constraint fk_town_id foreign key (town_id) references towns (id) ON DELETE NO ACTION on update cascade;


/* EX_3  insert data table 100/100*/ 
INSERT INTO towns (id, `name`)
 VALUES (1, 'Sofia'),
 (2, 'Plovdiv'),
 (3, 'Varna') ;
INSERT INTO minions (id, name, age, town_id) 
VALUES (1, 'Kevin', 22, 1),
(2,'Bob',15,3),
(3,'Steward', NULL, 2);

 /* EX_4  clear data table 100/100*/
 TRUNCATE TABLE minions;
 
  /* EX_5  delete table 100/100*/
  DROP TABLE minions;
  DROP TABLE towns;
  /*or 100/100*/
  DROP TABLES minions, towns;
