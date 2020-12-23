CREATE DATABASE `data_base`;

/* EX_6* 100/100 */
USE `data_base`;
CREATE TABLE people(
`id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
`name` VARCHAR(200) NOT NULL,
`picture` MEDIUMBLOB NULL, 
`height` DOUBLE (4,2) NULL,
`weight` DOUBLE (4,2) NULL,
`gender` CHAR  NOT NULL,
`birthdate` DATE NOT NULL,
`biography` VARCHAR (200) NULL
);

INSERT INTO people (id,`name`,gender,birthdate)
 VALUES (1, 'Pesho', 'm', '2000-11-02'),
 (2, 'Pesho', 'm', '2000-11-12'),
 (3, 'Pena', 'f', '2000-11-22'),
 (4, 'Tosho', 'm', '2000-11-01'),
 (5, 'Gosho', 'm', '2000-11-11');
 
 
 /* EX_7 100/100 */
 CREATE TABLE users(
 `id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
 `username` VARCHAR(30) NOT NULL,
 `password` VARCHAR(26) NOT NULL,
 `profile_picture` MEDIUMBLOB NULL,
 `last_login_time` DATETIME ,
 `is_deleted` BOOLEAN
 );

INSERT INTO users (id, username, `password`, is_deleted)
VALUES (1, 'pesh_12','dgd_naBABA', false), 
(2, 'gesh_12','dgd_naBABA', false), 
(3, 'msh_12','dgd_naBABA', false), 
(4, 'ash_12','dgd_naBABA', false), 
(5, 'tosh_12','dgd_naBABA', false); 

 /* EX_8 100/100 */
