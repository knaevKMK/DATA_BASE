create database if not EXISTS colonial_journey_management_system_db;
use colonial_journey_management_system_db;

CREATE TABLE planets(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(30) NOT NULL
);


CREATE TABLE spaceports(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL,
planet_id INT(11) ,
CONSTRAINT fk_spaceports_planets
FOREIGN KEY (planet_id)
REFERENCES planets(id)
);

CREATE TABLE spaceships(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL,
manufacturer VARCHAR(30)  NOT NULL,
light_speed_rate INT(11) DEFAULT 0
);
CREATE TABLE journeys(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
journey_start DATETIME NOT NULL,
journey_end DATETIME  NOT NULL,
purpose ENUM ('Medical','Technical','Educational','Military'),
destination_spaceport_id INT(11),
spaceship_id INT(11) ,
CONSTRAINT fk_journeys_spaceports
FOREIGN KEY (destination_spaceport_id)
REFERENCES spaceports(id),
CONSTRAINT fk_journeys_spaceships
FOREIGN KEY (spaceship_id)
REFERENCES spaceships(id)
);

CREATE TABLE colonists(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
ucn CHAR(10) UNIQUE NOT NULL,
birth_date DATE NOT NULL
);

CREATE TABLE travel_cards(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
card_number CHAR(10) UNIQUE NOT NULL,
job_during_journey ENUM( 'Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook'),
colonist_id INT(11) ,
journey_id INT(11),
CONSTRAINT fk_travel_cards_colonists 
FOREIGN KEY (colonist_id)
REFERENCES colonists(id),
CONSTRAINT fk_travel_cards_journeys
FOREIGN KEY (journey_id)
REFERENCES journeys(id)
);


# EX_1 
INSERT INTO travel_cards (card_number, job_during_journey, colonist_id, journey_id)
	SELECT 
		(CASE 
        WHEN YEAR(c.birth_date) >= 1980 THEN CONCAT(YEAR(c.birth_date),DAY(c.birth_date), SUBSTRING(c.ucn,1,4))
		ELSE CONCAT(YEAR(c.birth_date),MONTH(c.birth_date), SUBSTRING(c.ucn,-4,4))
        END)
        ,
		(CASE
			WHEN c.id % 2 = 0 THEN 'Pilot'
            WHEN c.id % 3 = 0 THEN 'Cook'
            ELSE 'Engineer'
        END) ,
        c.id ,
        SUBSTRING(c.ucn,1,1) 
    FROM colonists AS c
WHERE c.id BETWEEN 96 AND 100;

SELECT * FROM travel_cards
WHERE colonist_id BETWEEN 96 AND 100;
# EX_2 
UPDATE journeys 
SET purpose = (
CASE
	WHEN id % 2 = 0 THEN 'Medical'
	WHEN id % 3 = 0 THEN 'Technical'
	WHEN id % 5 = 0 THEN 'Educational'
	WHEN id % 7 = 0 THEN 'Military'
    ELSE purpose
END
);

# EX_3 
DELETE colonists FROM colonists 
WHERE id >95;

# EX_4 
SELECT 
card_number,
job_during_journey
FROM travel_cards
ORDER BY card_number ASC;

# EX_5 
SELECT
 id, 
 CONCAT(first_name, ' ' , last_name),
 ucn
 FROM colonists
 ORDER BY first_name,last_name,id;
 
# EX_6 
SELECT
id,	journey_start,	journey_end
FROM journeys
WHERE purpose = 'Military'
ORDER BY DATE(journey_start);
SELECT * FROM journeys;

# EX_7 
SELECT 
c.id,CONCAT(c.first_name , ' ' , c.last_name) AS	full_name
FROM colonists AS c
JOIN travel_cards AS tc
ON c.id=tc.colonist_id
WHERE tc.job_during_journey='Pilot'
ORDER BY c.id ASC;

SELECT * FROM colonists;

# EX_8 
SELECT 
COUNT(c.id) AS count
FROM colonists AS c
JOIN travel_cards AS tc
ON c.id=tc.colonist_id
JOIN journeys AS j
ON tc.journey_id= j.id
WHERE j.purpose='Technical';

# EX_9 
SELECT 
(s.`name`) AS spaceship_name,
(sp.`name`) AS	spaceport_name
FROM spaceships AS s
JOIN journeys AS j
ON s.id=j.spaceship_id
JOIN spaceports AS sp
ON j.destination_spaceport_id=sp.id
ORDER BY s.light_speed_rate DESC
LIMIT 1;

# EX_10 
SELECT s.`name`, s.`manufacturer` FROM spaceships AS s
JOIN journeys AS j ON s.id=j.spaceship_id
JOIN travel_cards AS tc ON j.id=tc.journey_id
JOIN colonists AS c ON tc.colonist_id=c.id
 WHERE 30 > ( YEAR('2019-01-01') - YEAR(c.birth_date))
 GROUP BY s.id
ORDER BY s.`name` ASC;

# EX_11
SELECT 
p.`name` AS  planet_name,
s.`name` AS	spaceport_name
FROM planets AS p
JOIN spaceports AS s
ON p.id=s.planet_id
JOIN journeys AS j
ON s.id= j.destination_spaceport_id
WHERE j.purpose='Educational'
ORDER BY s.`name` DESC;

# EX_12
SELECT 
    p.`name` AS planet_name, COUNT(j.id) AS journeys_count
FROM
    planets AS p
        JOIN
    spaceports AS s ON p.id = s.planet_id
        JOIN
    journeys AS j ON s.id = j.destination_spaceport_id
GROUP BY p.id
ORDER BY journeys_count DESC , planet_name ASC;

# EX_13
SELECT 
    j.id,
    p.`name` AS planet_name,
    s.`name` AS spaceport_name,
    j.purpose
FROM
    planets AS p
        JOIN
    spaceports AS s ON p.id = s.planet_id
        JOIN
    journeys AS j ON s.id = j.destination_spaceport_id
ORDER BY TIMEDIFF(j.journey_end, j.journey_start) ASC , planet_name
LIMIT 1;

# EX_14

SELECT 
 	tc.job_during_journey AS	job_name
    ,COUNT(c.id),
    TIMEDIFF(j.journey_end, j.journey_start)
FROM travel_cards AS tc
JOIN colonists AS c 
ON tc.colonist_id=c.id
 JOIN journeys AS j
 ON tc.journey_id= j.id
GROUP BY  tc.job_during_journey
ORDER BY (COUNT(c.id));
LIMIT 1,1;
# EX_15

DELIMITER $$
CREATE FUNCTION udf_count_colonists_by_destination_planet (planet_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN(	SELECT 
    COUNT(tc.colonist_id)
    FROM
    planets AS p
        JOIN
    spaceports AS s ON p.id = s.planet_id
        JOIN
    journeys AS j ON s.id = j.destination_spaceport_id
		JOIN 
        travel_cards AS tc    ON j.id=tc.journey_id
    WHERE p.`name`= planet_name
    );
END$$
DELIMITER ;

SELECT p.name, udf_count_colonists_by_destination_planet('Otroyphus') AS count
FROM planets AS p
    WHERE p.`name`='Otroyphus';



# EX_16
DELIMITER $$
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate(
spaceship_name VARCHAR(50), 
light_speed_rate_increse INT(11)) 
BEGIN
	CASE
		WHEN 0 = (
				SELECT COUNT(ss.id) FROM spaceships AS ss WHERE ss.`name` = spaceship_name)
		THEN 
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.' ;
		 ELSE 
           UPDATE spaceships
            SET light_speed_rate = light_speed_rate + light_speed_rate_increse
            WHERE `name`= spaceship_name;
    END CASE;
END$$
DELIMITER ;

CALL udp_modify_spaceship_light_speed_rate ('Na Pesho koraba', 1914);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'Na Pesho koraba';

CALL udp_modify_spaceship_light_speed_rate ('USS Templar', 5);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'USS Templar';

SET SQL_SAFE_UPDATES = 0;
