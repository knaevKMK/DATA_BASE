#EX_1
CREATE TABLE mountains (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL
);
 
 CREATE TABLE peaks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    mountain_id INT NOT NULL,
    CONSTRAINT fk_mountain_id FOREIGN KEY (mountain_id)
        REFERENCES mountains (id)
);

#EX_2
SELECT  v.driver_id, v.vehicle_type,  CONCAT(c.first_name, ' ' , c.last_name) AS driver_name
FROM vehicles AS v
JOIN campers AS c
ON v.driver_id=c.id;

#EX_3
SELECT (r.starting_point) AS route_starting_point,
(r.end_point) AS route_ending_point,
r.leader_id, 
CONCAT(c.first_name, ' ', c.last_name) AS leader_name
FROM routes AS r
JOIN campers AS c
ON c.id= r.leader_id;

#EX_4
DROP TABLES mountains, peaks;

CREATE TABLE mountains (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL
);
 
 CREATE TABLE peaks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    mountain_id INT NOT NULL,
    CONSTRAINT fk_mountain_id FOREIGN KEY (mountain_id)
        REFERENCES mountains (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);