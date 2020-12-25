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
CHANGE COLUMN last_login_time last_login_time  TIME;

/*EX_11*/
ALTER TABLE users
DROP PRIMARY KEY,
ADD PRIMARY KEY (id);


/*EX_9*/
/*EX_9*/
/*EX_9*/
/*EX_9*/
/*EX_9*/