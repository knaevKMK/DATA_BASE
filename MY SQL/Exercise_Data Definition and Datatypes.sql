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

/*EX_7*/
CREATE TABLE people (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(200)  NOT NULL,
picture BLOB NULL,
height DOUBLE (4,2) NULL,
weight DOUBLE (4,2) NULL,
gender VARCHAR(1) NOT NULL,
birthday DATE NOT NULL,
biography TEXT NULL
);

INSERT INTO people (`name`,gender,birthday) VALUES
('Pesho','m','1984-11-11'),
('Tosho','m','1985-03-21'),
('Kina','f','1986-12-31'),
('Mina','f','1987-10-01'),
('Gosho','m','1988-01-30');

/*EX_8*/
DROP TABLE users;
CREATE TABLE users(
id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(30) UNICODE UNIQUE NOT NULL,
password VARCHAR(26) NOT NULL,
profile_picture BLOB NULL,
last_login_time DATETIME,
is_deleted BOOLEAN NOT NULL
);

INSERT INTO users ( username, `password`, is_deleted) VALUES
('pesh2368','dgd_1_naMamaT@',false),
('toh2368','dbd_1_naMamaT@',false),
('kinS368','dvd_1_naMamaT@',false),
('miKn238','cd_1_naMamaT@',false),
('gosh2118','mp3_1_naMamaT@',false);

/*EX_9 NOT WORK*/
ALTER TABLE `users` 
ADD COLUMN pk_users VARCHAR(45) AS (concat(id,'_',username));
ALTER TABLE `users` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (pk_user) ;

ALTER TABLE `users` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`, `username`);



/*EX_10*/
ALTER TABLE users
CHANGE COLUMN last_login_time last_login_time TIME;

/*EX_11*/
ALTER TABLE users
DROP PRIMARY KEY,
ADD PRIMARY KEY (id);


/*EX_12*/
DROP SCHEMA movies;
CREATE DATABASE movies;
USE movies;

CREATE TABLE directors (
id INT AUTO_INCREMENT ,
director_name VARCHAR(30) NOT  NULL,
notes TEXT NULL,
PRIMARY KEY (id)
);

CREATE TABLE genres(
id INT AUTO_INCREMENT,
genre_name VARCHAR(50) NOT NULL,
notes TEXT NULL,
PRIMARY KEY (id)
);

CREATE TABLE categories(
id INT AUTO_INCREMENT,
category_name VARCHAR(50) NOT NULL,
notes TEXT NULL,
PRIMARY KEY (id)
);

CREATE TABLE movies(
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR (50) NOT NULL,
director_id  INT NOT NULL,
copyright_year YEAR,
lenght TIME,
genre_id INT NOT NULL,
category_id INT NOT NULL,
rating INT NULL,
notes TEXT NULL
);

INSERT INTO directors (director_name) VALUES 
('Mel Gibson'), 
('Will Smith'), 
('Stiven King'), 
('Silvester Staloun '), 
('Luk Beson'); 

INSERT INTO  genres (genre_name) VALUES 
('action'),
('musichol'),
('documental'),
('horror'),
('comedy');

INSERT INTO categories (category_name) VALUES
('drama'),
('animation'),
('fantasy'),
('historical'),
('criminal');

INSERT INTO movies (title, director_id,copyright_year,genre_id,category_id) VALUES 
('Lethal Weapon',1 ,'1993',1,1),
('Man In Black',2 ,'1996',1,3),
('It',3 ,'1994',4,3),
('Rambo',4 ,'1991',1,1),
('Taxy',5 ,'1993',5,3);


ALTER TABLE movies
ADD CONSTRAINT fk_directors_id
FOREIGN KEY (director_id)
REFERENCES directors (id),
ADD CONSTRAINT fk_genres_id
FOREIGN KEY (genre_id)
REFERENCES genres (id),
ADD CONSTRAINT fk_category_id
FOREIGN KEY  (category_id)
REFERENCES categories (id);

SELECT m.id, title, d.director_name AS Director, copyright_year AS `Year`,g.genre_name AS Genre,c.category_name AS CATEGORY FROM movies  m
JOIN directors d , genres g, categories c 
WHERE director_id = d.id AND genre_id=g.id AND category_id=c.id;

/*EX_13*/
drop schema car_rental;
CREATE DATABASE car_rental;
USE car_rental;

CREATE TABLE categories (
id INT AUTO_INCREMENT PRIMARY KEY, 
category VARCHAR (45) NOT NULL, 
daily_rate DECIMAL(5,2)UNSIGNED NULL, 
weekly_rate DECIMAL(5,2)UNSIGNED NULL, 
monthly_rate DECIMAL(5,2)UNSIGNED NULL,
weekend_rate DECIMAL(5,2)UNSIGNED NULL
);

CREATE TABLE cars (
id INT AUTO_INCREMENT PRIMARY KEY, 
plate_number VARCHAR(8) , 
make VARCHAR(20), 
model VARCHAR(10), 
car_year YEAR, 
category_id INT,
CONSTRAINT fk_categories_id
FOREIGN KEY (category_id) 
REFERENCES categories(id),
doors INT NOT NULL, 
picture BLOB NULL, 
`condition` VARCHAR(5) NULL, 
available BOOLEAN
); 

CREATE TABLE 	employees (
id INT AUTO_INCREMENT PRIMARY KEY, 
first_name VARCHAR(15) NOT NULL, 
last_name VARCHAR(15) NOT NULL, 
title VARCHAR(20) NULL, 
notes TEXT NULL
);

CREATE TABLE 	customers (
id INT AUTO_INCREMENT PRIMARY KEY, 
driver_licence_number VARCHAR (20) UNIQUE NOT NULL, 
full_name VARCHAR(30) NOT NULL, 
address VARCHAR(55) NULL, 
city VARCHAR(20) NOT NULL, 
`zip-code` VARCHAR (10) NOT NULL, 
notes TEXT NULL
);

DROP TABLE rental_orders;
CREATE TABLE rental_orders (
id INT AUTO_INCREMENT PRIMARY KEY, 
employee_id INT NOT NULL, 
CONSTRAINT fk_employees_id
FOREIGN KEY (employee_id)
REFERENCES employees (id),
customer_id INT NOT NULL, 
 CONSTRAINT fk_customer_id
FOREIGN KEY (customer_id)
REFERENCES customers(id),
car_id INT NOT NULL, 
CONSTRAINT fk_cars_id
FOREIGN KEY (car_id)
REFERENCES cars (id),
car_condition VARCHAR(10) NULL , 
tank_level INT , 
kilometrage_start INT(6) NOT NULL, 
kilometrage_end INT(6)  NULL, 
total_kilometrage INT(6) AS (kilometrage_end-kilometrage_start) NULL, 
start_date DATE  not null, 
end_date DATE  null, 
total_days INT AS (end_date-start_date), 
rate_applied INT  null, 
tax_rate DECIMAL null, 
order_status VARCHAR(10) NOT NULL, 
notes TEXT NULL
);

INSERT INTO categories (category, daily_rate, weekly_rate, monthly_rate, weekend_rate) VALUES
('VAN',30,120.5,500.11,50.25),
('SUW',35,130.5,550.11,60.25),
('LIMOSINE',40,140.5,600.11,70.25);

INSERT INTO	cars ( plate_number, make, model, car_year, category_id, doors, `condition`, available) VALUES
('CT2211PA', 'Mercedes-Benz', 'E280', '2007', 3, 4, 'good', true),
('CT4562BA', 'Mercedes-Benz', 'ML280', '2008', 2, 4, 'poor', true),
('CT3546CA', 'VW', 'SHARAN', '2003', 3, 5, 'poor', true);

INSERT INTO 	employees (first_name, last_name) VALUES
('Krasimir', 'Mitev'),
('Zhenya', 'Ivanova'),
('Pencho', 'Zlatev');

INSERT INTO 	customers ( driver_licence_number, full_name, city, `zip-code`) VALUES
('234892314','Pesho Peshev', 'Radnevo', '6020'),
('234322314','Techo Techev', 'London', 'N8 9DJ'),
('234492314','Gosho Goshev', 'NUREMBERG', '90442');


INSERT INTO rental_orders (employee_id, customer_id, car_id, tank_level, kilometrage_start, kilometrage_end,
  start_date, end_date, order_status) VALUES
 (2,1,3,80, 250345,null, '2020-12-20',NULL, 'PROCESS'),
 (1,2,1,100, 150345,150775,'2020-12-19','2020-12-24', 'COMPLETED'),
 (3,3,2,60, 70345,NULL,'2020-12-22',NULL, 'PROCESS');

SELECT 
customers.full_name, 
customers.city, 
cars.plate_number, 
cars.make, 
rental_orders.total_kilometrage, 
rental_orders.total_days, 
rental_orders.order_status
 FROM rental_orders
JOIN 
customers, employees, cars
 WHERE 
 customer_id=customers.id 
AND  car_id=cars.id
AND employee_id=employees.id;




/*EX_9*/
/*EX_9*/
/*EX_9*/