CREATE SCHEMA softuni;
/* EX_1 Create table students*/

 CREATE TABLE softuni.students(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
first_name VARCHAR(50) NULL,
last_name VARCHAR (50) NULL,
age INT NULL,
grade DOUBLE NULL 
);

/* EX_2 INSERT DATA*/
INSERT INTO softuni.students (first_name, last_name, age, grade)
VALUES ('Guy', 'Gilbert', 15, 4.5),
('Kevin', 'Brown', 17,5.4),
('Roberto',	'Tamburello',	19,	6),
('Linda',	'Smith',	18,	5),
('John',	'Stones',	16,	4.25),
('Nicole',	'Nelson',	17,	5.50);

/* EX_3 get all data*/
SELECT * FROM softuni.students;

/* EX_4 get select data*/
SELECT last_name, age, grade FROM softuni.students;

/* EX_5 first 5 rec*/
SELECT * FROM softuni.students LIMIT 5;
/* or */
SELECT * FROM softuni.students WHERE id BETWEEN 1 and 5;

/* EX_6 first 5 rec*/
SELECT last_name, grade FROM softuni.students LIMIT 5; 

/* EX_7 clear cells*/
TRUNCATE TABLE softuni.students;

/* EX_8 drop table*/
DROP TABLE students;

/* EX_9 drop schema*/
DROP SCHEMA softuni;