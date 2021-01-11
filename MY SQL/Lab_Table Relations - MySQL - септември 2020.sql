CREATE SCHEMA lab_realtion;

#EX_1 
CREATE TABLE lab_realtion.mountains(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);
CREATE TABLE lab_realtion.peaks (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
mountain_id INT NOT NULL,
CONSTRAINT fk_mountain_peaks
FOREIGN KEY (mountain_id)
REFERENCES mountains(id)
ON UPDATE CASCADE
ON DELETE SET NULL
);

#EX_2
SELECT driver_id, vehicle_type, CONCAT(first_name +' '+last_anme) AS `driver_name`
FROM vehicles AS v
JOIN campare AS c
ON v.driver_id=c.id;

#EX_3
SELECT start_point AS routing_start_point,
end_point AS routing_end_point,
leader_id,
CONCAT(first_name+' '+ last_name) AS leader_name
FROM routs AS r
JOIN campers AS C
ON r.leader_id=c.id;

#EX_4
DROP TABLES peaks,mountains;

CREATE TABLE mountains(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

ALTER TABLE peaks
ADD CONSTRAINT fk_mountain_peaks
FOREIGN KEY (mountained_id)
REFERENCES mountains(id)
ON UPDATE CASCADE
ON DELETE CASCADE;