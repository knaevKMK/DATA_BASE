CREATE SCHEMA minions;

USE minions;
#EX_1
CREATE TABLE minions (
id INT auto_increment primary key,
`name` varchar(50) ,
age INT
);

CREATE TABLE towns (
town_id INT auto_increment primary key,
`name` varchar (50)
);



#EX_2
ALTER table towns
DROP COLUMN town_id,
ADD COLUMN id INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE minions
ADD COLUMN town_id int NOT NULL;

ALTER table minions
ADD CONSTRAINT fk_town_id
foreign key (town_id)
references towns (id);

#EX_3
INSERT INTO towns (name) VALUES
("Sofia"),
("Plovdiv"),
("Varna");
INSERT INTO minions (`name`, age, town_id) VALUES
("Kevin" , 22, 1),
("Bob" ,15, 3),
("Steward",NULL, 2);

