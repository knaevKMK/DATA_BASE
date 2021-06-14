# ex-4 - 8   NOT COMLATED at 100 points

CREATE SCHEMA exam_prep2;
USE exam_prep2;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE countries (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE towns (
	id INT(11) AUTO_INCREMENT PRIMARY KEY, 
    `name` VARCHAR(45) NOT NULL,
    country_id INT(11) NOT NULL,
    CONSTRAINT fk_towns_countries
    FOREIGN KEY (country_id)
    REFERENCES countries(id)
);

CREATE TABLE stadiums (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
 `name` VARCHAR(45) NOT NULL,
 capacity INT(11) NOT NULL,
 town_id INT(11) NOT NULL,
 CONSTRAINT fk_stadiums_towns
 FOREIGN KEY (town_id)
 REFERENCES towns(id)
);

CREATE TABLE teams (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL,
established DATE NOT NULL,
fan_base BIGINT(20) NOT NULL DEFAULT 0,
stadium_id INT(11) NOT NULL,
CONSTRAINT fk_teams_stadiums
FOREIGN KEY (stadium_id)
REFERENCES stadiums(id)
);

CREATE TABLE skills_data (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
dribbling INT(11) DEFAULT 0,
pace INT(11) DEFAULT 0,
passing INT(11) DEFAULT 0,
shooting INT(11) DEFAULT 0,
speed INT(11) DEFAULT 0,
strength INT(11) DEFAULT 0
);

CREATE TABLE players (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
age INT(11) NOT NULL DEFAULT 0,
position CHAR(1) NOT NULL,
salary DECIMAL(10,2) NOT NULL DEFAULT 0,
hire_date DATETIME ,
skills_data_id INT(11) NOT NULL,
team_id INT(11) ,
CONSTRAINT fk_players_skills_data
FOREIGN KEY(skills_data_id)
REFERENCES skills_data(id),
CONSTRAINT fk_players_teams
FOREIGN KEY (team_id)
REFERENCES teams(id)
);

CREATE TABLE coaches (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(10,2) NOT NULL DEFAULT 0,
coach_level INT(11) NOT NULL DEFAULT 0
);

CREATE TABLE players_coaches (
player_id INT(11) ,
coach_id INT(11),
CONSTRAINT pk_players_coaches 
PRIMARY KEY (player_id,coach_id),
CONSTRAINT fk_players_coaches_players
FOREIGN KEY (player_id)
REFERENCES players(id),
CONSTRAINT fk_players_coaches_coaches
FOREIGN KEY (coach_id)
REFERENCES coaches(id));

#EX_2
INSERT INTO coaches (
 first_name,
 last_name,
 salary,
 coach_level)
SELECT
 first_name,
 last_name,
 salary, 
 length(first_name) AS coach_level 
FROM players AS p 
WHERE p.age>=45;

#EX_3
UPDATE coaches AS c 
SET 
    c.coach_level = c.coach_level + 1
WHERE
    1 <= (SELECT 
            COUNT(player_id)
        FROM
            players_coaches AS pc
            
                JOIN
            players AS p ON p.id = pc.player_id
            WHERE c.id=pc.coach_id AND c.first_name LIKE 'A%');
            
#EX_4
DELETE p FROM players AS p
WHERE p.age>=45;

SELECT COUNT(*) FROM players;

SET SQL_SAFE_UPDATES = 0;
#EX_5
SELECT first_name,age , salary FROM players ORDER BY salary DESC;

#EX_6
SELECT
p.id,
CONCAT(p.first_name,' ', last_name) AS full_name,
	p.age,
	p.position,
	p.hire_date
    FROM players AS p
    JOIN skills_data AS sd
    ON sd.id=p.skills_data_id
    WHERE 
		sd.strength>50 
        AND p.age <23 
        AND p.position='A' 
        AND p.hire_date IS NULL
    ORDER BY p.salary, p.age;

#EX_7
SELECT 
(t.`name`)AS team_name,
t.established,
t.fan_base,
COUNT(p.id) AS players_count
FROM teams AS t
LEFT JOIN players AS p
ON p.team_id=t.id
GROUP BY t.id
ORDER BY players_count DESC, t.fan_base DESC;

#EX_8
SELECT 
    MAX(sd.speed) AS max_speed, (t.`name`) AS town_name
FROM
    towns AS t
        LEFT JOIN
    stadiums AS s ON t.id = s.town_id
        LEFT JOIN
    teams AS `te` ON s.id = te.stadium_id
        LEFT JOIN
    players AS p ON te.id = p.team_id
        LEFT JOIN
    skills_data AS `sd` ON p.skills_data_id = `sd`.id
WHERE
    te.name != 'Devify'
GROUP BY t.id
ORDER BY max_speed DESC , town_name ASC;

#EX_9
SELECT 
    c.`name`,
    COUNT(p.id) AS total_count_of_players,
    SUM(p.salary) total_sum_of_salaries
FROM
    countries AS c
        LEFT JOIN
    towns AS t ON t.country_id = c.id
        LEFT JOIN
    stadiums AS st ON st.town_id = t.id
        LEFT JOIN
    teams AS te ON te.stadium_id = st.id
        LEFT JOIN
    players AS p ON p.team_id = te.id
GROUP BY c.`name`
ORDER BY total_count_of_players DESC, c.`name`;

#EX_10

CREATE FUNCTION udf_stadium_players_count (
	stadium_name VARCHAR(30)
	)
RETURNS INT 
RETURN (
			SELECT 
				COUNT(p.id) AS players_count
			FROM stadiums AS s
            JOIN teams AS t
				ON t.stadium_id=s.id
			JOIN players AS p
				ON p.team_id=t.id
            WHERE s.`name`=stadium_name);
           
    
SELECT udf_stadium_players_count ('Jaxworks') as `count`; 
SELECT udf_stadium_players_count ('Linklinks') as `count`; 
    
#EX_11
DELIMITER $$
CREATE PROCEDURE udp_find_playmaker  (
min_dribble_points INT (3),
 team_name VARCHAR (45))
BEGIN
	SELECT 
    CONCAT(p.first_name, ' ' , p.last_name) AS full_name,
    p.age,
    p.salary,
    sd.dribbling,
    sd.speed,
    te.`name`
	FROM players AS p
    JOIN skills_data AS sd
    ON p.skills_data_id=sd.id
    JOIN teams AS te
    ON p.team_id= te.id
    WHERE te.`name`=team_name
    AND sd.dribbling > min_dribble_points
    ORDER BY sd.speed DESC 
    LIMIT 1;
END $$
DELIMITER ;

CALL udp_find_playmaker (20, 'Skyble');