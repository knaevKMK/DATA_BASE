
/* EX_1 Create table students*/
 CREATE TABLE students(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
first_name VARCHAR(50) NULL,
last_name VARCHAR (50) NULL,
age INT NULL,
grade DOUBLE NULL 
);

/* EX_2 */
INSERT INTO students (first_name, last_name, age, grade)
VALUES ('Guy', 'Gilbert', 15, 4.5);
INSERT INTO students (first_name, last_name, age, grade)
VALUES ('Kevin', 'Brown', 17,5.4);
INSERT INTO students (first_name, last_name, age, grade)
VALUES ('Roberto',	'Tamburello',	19,	6);
INSERT INTO students (first_name, last_name, age, grade)
VALUES ('Linda',	'Smith',	18,	5);
INSERT INTO students (first_name, last_name, age, grade)
VALUES ('John',	'Stones',	16,	4.25);
INSERT INTO students (first_name, last_name, age, grade)
VALUES ('Nicole',	'Nelson',	17,	5.50);