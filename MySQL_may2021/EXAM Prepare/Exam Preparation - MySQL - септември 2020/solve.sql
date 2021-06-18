CREATE SCHEMA IF NOT EXISTS instd ;
USE instd ;


CREATE TABLE users (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(30) UNIQUE NOT NULL,
password VARCHAR(30) NOT NULL,
email VARCHAR(50) NOT NULL,
gender CHAR(1) NOT NULL,
age INT(11) NOT NULL,
job_title VARCHAR(40) NOT NULL,
ip VARCHAR(30) NOT NULL
);

CREATE TABLE addresses(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
address VARCHAR(30) NOT NULL,
town VARCHAR(30) NOT NULL,
country VARCHAR(30) NOT NULL,
user_id INT(11) NOT NULL,
CONSTRAINT fk_addresses_users
FOREIGN KEY (user_id)
REFERENCES users(id)
);

CREATE TABLE photos(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`description` TEXT NOT NULL,
date DATETIME NOT NULL,
views INT(11) DEFAULT 0 NOT NULL
);

CREATE TABLE comments(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`comment` VARCHAR(255) NOT NULL,
`date` DATETIME NOT NULL,
photo_id INT (11)  NOT NULL,
CONSTRAINT fk_comments_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id)
);

CREATE TABLE likes(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
photo_id INT(11),
user_id INT(11) ,
CONSTRAINT fk_likes_users
FOREIGN KEY (user_id)
REFERENCES users(id),
CONSTRAINT fk_likes_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id)
);

CREATE TABLE users_photos (
user_id INT(11) NOT NULL,
photo_id INT(11) NOT NULL,
CONSTRAINT pk_users_photos
PRIMARY KEY (user_id,photo_id),
CONSTRAINT fk_users_photos_users
FOREIGN KEY (user_id)
REFERENCES users(id),
CONSTRAINT fk_users_photos_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id));



#EX_2
INSERT INTO addresses (address, town,country,user_id)
	SELECT u.username,u.`password`,u.ip,u.age 
    FROM users AS u
    WHERE u.gender ='M' ;

#EX_3
SET SQL_SAFE_UPDATES = 0;
UPDATE addresses 
SET country = (
	CASE
		WHEN country LIKE 'B%' THEN 'Blocked'
		WHEN country LIKE 'T%' THEN 'Test'
		WHEN country LIKE 'P%' THEN 'In Progress'
        ELSE country=country
	END
);

#EX_4
DELETE a FROM addresses AS a
WHERE a.id%3=0;


#EX_5
SELECT username,gender,age FROM users ORDER BY age DESC, username;

#EX_6
SELECT 
	p.id,
 	p.date AS date_and_time,
    p.`description`,
    COUNT(c.id)AS commentsCount
FROM photos AS p
JOIN comments AS c ON p.id=c.photo_id
GROUP BY photo_id
ORDER BY commentsCount DESC, p.id
LIMIT 5;

#EX_7
SELECT 
	CONCAT(u.id,' ' , u.username) AS id_username,
    email
FROM users AS u
JOIN users_photos AS up ON u.id=up.user_id
WHERE u.id=up.photo_id;

#EX_8
SELECT 
	p.id AS photo_id,
	COUNT(DISTINCT l.id) AS likes_count,
	COUNT(DISTINCT c.id) AS comments_count
FROM photos AS p
LEFT JOIN likes AS l ON p.id= l.photo_id
LEFT JOIN comments AS c ON p.id = c.photo_id
GROUP BY p.id
ORDER BY likes_count DESC, comments_count DESC,  photo_id;

#EX_2
#EX_2