CREATE SCHEMA minions;

USE minions;
SELECT * FROM minions;


#EX_1
CREATE TABLE minions (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) ,
age INT
);

CREATE TABLE towns (
town_id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR (50)
);



#EX_2
ALTER TABLE towns
DROP COLUMN town_id,
ADD COLUMN id INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE minions
ADD COLUMN town_id INT NOT NULL,
ADD CONSTRAINT fk_town_id
FOREIGN KEY (town_id)
REFERENCES towns (id);

#EX_3
INSERT INTO towns (id,`name`) VALUES
(1,"Sofia"),
(2,"Plovdiv"),
(3,"Varna");
INSERT INTO minions (id,`name`, age, town_id) VALUES
(1,"Kevin" , 22, 1),
(2,"Bob" ,15, 3),
(3, "Steward",NULL, 2);

#EX_4
TRUNCATE TABLE minions;

#EX_5
DROP TABLES minions, towns;

#EX_6
CREATE TABLE people (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(200)UNICODE NOT NULL,
    picture BLOB NULL,
    height DECIMAL(3 , 2 ) NULL,
    weight DECIMAL(3 , 2 ) NULL,
    gender ENUM('m', 'f') NOT NULL,
    birthdate DATE NOT NULL,
    biography TEXT
);
INSERT INTO people (`name`,gender,birthdate) VALUES
('Pesho',1,'1984-11-11'),
('Tosho',1,'1985-03-21'),
('Kina',2,'1986-12-31'),
('Mina',2,'1987-10-01'),
('Gosho',1,'1988-01-30');

#EX_7

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30)  NOT NULL,
    `password` VARCHAR(26) NOT NULL,
    profile_picture BLOB,
    last_login_time DATETIME,
    is_deleted BOOLEAN NOT NULL
);
INSERT INTO users ( username, `password`, is_deleted) VALUES
('pesh2368','dgd_1_naMamaT',false),
('toh2368','dbd_1_naMamaT',false),
('kinS368','dvd_1_naMamaT',false),
('miKn238','cd_1_naMamaT',false),
('gosh2118','mp3_1_naMamaT',false);

#EX_8
ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users PRIMARY KEY (id,username);

#EX_9
ALTER TABLE users
MODIFY COLUMN last_login_time DATETIME  DEFAULT  CURRENT_TIMESTAMP;

#EX_10
ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users PRIMARY KEY (id),
ADD CONSTRAINT username UNIQUE (username);

#EX_11
CREATE SCHEMA movies;

CREATE TABLE directors(
id INT AUTO_INCREMENT PRIMARY KEY,
director_name VARCHAR(50) NOT NULL,
notes TEXT
);


CREATE TABLE  genres(
id	INT AUTO_INCREMENT PRIMARY KEY,
genre_name VARCHAR(50) NOT NULL,
notes TEXT
);

CREATE TABLE categories(
id INT AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR (50) NOT NULL,
notes TEXT
);

CREATE TABLE movies(
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(100) NOT NULL,
director_id INT  NULL,
copyright_year INT, 
length TIME, 
genre_id INT  NULL , 
category_id INT  NULL, 
rating INT, 
notes TEXT,
CONSTRAINT fk_director_id FOREIGN KEY (director_id) REFERENCES directors(id),
CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) REFERENCES genres(id),
CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO directors (director_name) VALUES
("Stanley Kubrick"),
("Alfred Hitchcock"),
("Akira Kurosawa"),
("Steven Spielberg"),
("Martin Scorsese");

INSERT INTO genres (genre_name) VALUES
("Action"),
("Comedy"),
("Horror"),
("Thriller"),
("Drama");

INSERT INTO categories (category_name) VALUES
("documentary"),
("animation"),
("musical"),
("war"),
("history");

INSERT INTO movies (title, director_id,copyright_year ,genre_id ,category_id ,rating ) VALUES
("It" , 4,1999,3,5,8),
("Goodfellas", 5,1990,1,5,10),
("Psycho", 2,1960,5,3,6),
("Killer’s Kiss", 1,1955,2,4,5),
("Baby Boss", 3,2015,2,2,7);



#EX_12
CREATE SCHEMA car_rental ;

CREATE TABLE categories  (
id INT AUTO_INCREMENT PRIMARY KEY, 
category VARCHAR (50) NOT NULL, 
daily_rate DECIMAL(8,2), 
weekly_rate DECIMAL(8,2), 
monthly_rate DECIMAL(8,2), 
weekend_rate DECIMAL(8,2)
);

CREATE TABLE cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate_number VARCHAR(50) NOT NULL,
    make VARCHAR(50) ,
    model VARCHAR(50),
    car_year INT(4),
    category_id INT (11),
    doors INT(1),
    picture BLOB ,
    car_condition VARCHAR(50),
    available BOOL,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE	employees (
id INT AUTO_INCREMENT PRIMARY KEY, 
first_name VARCHAR(20), 
last_name VARCHAR(20), 
title VARCHAR(30), 
notes TEXT
);

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    driver_licence_number INT not null,
    full_name VARCHAR(50),
    address VARCHAR(50),
    city VARCHAR(50),
    zip_code VARCHAR(10),
    notes TEXT
);

CREATE TABLE	rental_orders (
id INT AUTO_INCREMENT PRIMARY KEY, 
employee_id INT , 
customer_id INT , 
car_id INT , 
car_condition VARCHAR(50), 
tank_level INT , 
kilometrage_start INT  , 
kilometrage_end INT , 
total_kilometrage INT , 
start_date DATE, 
end_date DATE, 
total_days INT , 
rate_applied INT(3), 
tax_rate DECIMAL (5,2), 
order_status VARCHAR(50), 
notes TEXT
);

INSERT INTO cars(plate_number)
VALUES ('123'),('1234'),('12345');
INSERT INTO categories(category)
VALUES ('Classic'),('Limuzine'),('Sport');
INSERT INTO customers(driver_licence_number)
VALUES ('2232'),('232323'),('111');
INSERT INTO employees(first_name,last_name)
VALUES ('Ivan', 'Ivanov'),('Ivan1', 'Ivanov1'), ('Ivan2', 'Ivanov2');
INSERT INTO rental_orders(employee_id,car_id)
VALUES (1, 1),(1, 2), (2, 3);


#EX_13
INSERT INTO towns (`name`) VALUES
("Sofia"),
("Plovdiv"),
("Varna"),
("Burgas");

INSERT INTO departments (`name`)VALUES
("Engineering"),
("Sales"),
("Marketing"),
("Software Development"),
("Quality Assurance");
INSERT INTO employees (`name`,job_title,department,hire_date,salary) VALUES
("Ivan Ivanov Ivanov",".NET Developer",4,"01/02/2013",3500.00),
("Petar Petrov Petrov","Senior Engineer",1,"02/03/2004",4000.00),
("Maria Petrova Ivanova", "Intern",5,"28/08/2016",525.25),
("Georgi Terziev Ivanov","CEO",2,"09/12/2007",3000.00),
("Peter Pan Pan", "Intern",3,"28/08/2016",599.88);

INSERT INTO employees (first_name, middle_name, last_name, job_title, department_id, hire_date, salary) VALUES 
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);


INSERT INTO rental_orders(
employee_id,
customer_id, 
car_id, 
car_condition, 
tank_level,
kilometrage_start,
kilometrage_end ,
rate_applied ,
order_status
) VALUES
(1,1,1,2,50,45000,null, 1,1),
(2,2,3,2,50,65000,null,1,1),
(3,3,2,2,50,55000,null,1,1);


#ex_14
SELECT * FROM towns;
SELECT * FROM departments;
SELECT * FROM employees;

#ex_15
SELECT * FROM towns ORDER BY `name` ASC;
SELECT * FROM departments ORDER BY `name` ASC;
SELECT * FROM employees ORDER BY salary DESC ;

#EX_16
SELECT `name` FROM towns ORDER BY `name` ASC;
SELECT `name` FROM departments  ORDER BY `name` ASC;
SELECT first_name, last_name, job_title, salary FROM employees ORDER BY salary DESC ;

#EX_17
UPDATE employees
SET salary = salary*1.1;

SELECT salary FROM employees;



#EX_18

