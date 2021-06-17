CREATE SCHEMA IF NOT EXISTS fsd;
USE fsd;

CREATE TABLE countries(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL
);
CREATE TABLE towns(
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

CREATE TABLE teams(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL,
established DATE NOT NULL,
fan_base BIGINT (20) DEFAULT 0 NOT NULL,
stadium_id INT(11) NOT NULL,
CONSTRAINT fk_teams_stadiums
FOREIGN KEY (stadium_id)
REFERENCES stadiums(id)
);

CREATE TABLE skills_data(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
dribbling INT(11) DEFAULT 0,
pace INT(11) DEFAULT 0,
passing INT(11) DEFAULT 0,
shooting INT(11) DEFAULT 0,
speed INT(11) DEFAULT 0,
strength INT(11) DEFAULT 0
);
CREATE TABLE players(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
age INT(11) DEFAULT 0 NOT NULL,
position CHAR(1) NOT NULL,
salary DECIMAL (10,2) DEFAULT 0 NOT NULL,
hire_date DATETIME  ,
skills_data_id INT(11) NOT NULL,
team_id INT(11),
CONSTRAINT fk_players_skills_data 
FOREIGN KEY (skills_data_id)
REFERENCES skills_data(id),
CONSTRAINT fk_players_teams
FOREIGN KEY (team_id)
REFERENCES teams(id)
);
CREATE TABLE coaches(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL (10,2) DEFAULT 0 NOT NULL,
coach_level INT(11) DEFAULT 0 NOT NULL
);
CREATE TABLE players_coaches(
player_id INT(11) ,
coach_id INT(11),
CONSTRAINT fk_players_coaches_players
FOREIGN KEY (player_id)
REFERENCES players(id),
CONSTRAINT fk_players_coaches_coaches
FOREIGN KEY (coach_id)
REFERENCES coaches(id)
);


#ex_2
INSERT INTO coaches (
    first_name,
    last_name, 
    salary,
    coach_level)
    
  SELECT 
  p.first_name, 
  p.last_name,
  p.salary, 
  length(p.first_name)
  FROM players AS p 
  WHERE p.age > 44;


#ex_3
UPDATE coaches  AS c
SET c.coach_level = c.coach_level+ 1 
WHERE 
  c.first_name LIKE 'A%'
  AND 1<= (
    SELECT COUNT(player_id) 
    FROM players_coaches  AS pc
    WHERE pc.coach_id=c.id
);
SET SQL_SAFE_UPDATES = 0;
SELECT * FROM coaches;
#ex_4
DELETE p FROM players AS p
WHERE p.age >44;
#ex_5
SELECT first_name	,	age	,salary 
FROM players
ORDER BY salary DESC;
#ex_6
SELECT 
    p.id,
    CONCAT(p.first_name, ' ' , p.last_name) AS full_name,
    p.age,	
    p.position,
    p.hire_date
  FROM players AS p
  JOIN skills_data AS sd ON sd.id = p.skills_data_id
  WHERE 
    p.age<23 
    AND p.position ='A' 
    AND p.hire_date IS NULL 
    AND sd.strength >50
  ORDER BY p.salary , age ;
#ex_7
SELECT 
  t.`name` AS team_name,
	t.established,
	t.fan_base,
	COUNT(p.id)players_count
  FROM teams AS t
  LEFT JOIN players AS p ON p.team_id=t.id
  GROUP BY t.id
  ORDER BY players_count DESC, t.fan_base DESC;

#ex_8
SELECT 
 MAX( sd.speed) AS max_speed,
  tw.`name` AS town_name
  FROM  skills_data AS sd
  RIGHT JOIN players AS p ON p.skills_data_id= sd.id
  RIGHT JOIN teams AS te ON p.team_id=te.id
  RIGHT JOIN stadiums AS st ON te.stadium_id=st.id
  RIGHT JOIN towns AS tw ON st.town_id=tw.id
  WHERE 
    te.`name` NOT IN  ('Devify')
    GROUP BY  tw.id
  ORDER BY max_speed DESC, town_name;
#ex_9
SELECT 
  co.`name`,
	COUNT(p.id) AS total_count_of_players,
	SUM(p.salary) AS total_sum_of_salaries
  FROM players AS p
   RIGHT JOIN teams AS te ON p.team_id=te.id
   RIGHT JOIN stadiums AS st ON te.stadium_id=st.id 
   RIGHT JOIN towns AS tw ON st.town_id=tw.id 
   RIGHT JOIN countries AS co ON tw.country_id = co.id
  GROUP BY co.id
  ORDER BY total_count_of_players DESC, co.`name`;
#ex_10
DELIMITER $$
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30)) 
RETURNS INT
BEGIN
RETURN(
    SELECT COUNT(p.id) 
    FROM players AS p
    JOIN teams AS te ON p.team_id= te.id
    JOIN stadiums AS st ON te.stadium_id=st.id
    WHERE st.`name` =  stadium_name
 );
END$$
DELiMITER ;

#ex_11

DELIMITER $$
CREATE PROCEDURE udp_find_playmaker (
min_dribble_points INT(11),
team_name VARCHAR(45)
)
BEGIN
    SELECT 
        CONCAT(p.first_name, ' ', p.last_name) AS full_name	,
        p.age	,
        p.salary	,
        sd.dribbling	,
        sd.speed	,
        te.`name` AS team_name
      FROM players AS p
      JOIN skills_data AS sd ON p.skills_data_id= sd.id
      JOIN teams AS te ON p.team_id= te.id
      WHERE 
        te.`name`=   team_name
        AND sd.dribbling >min_dribble_points
      AND sd.speed > (SELECT AVG (speed) FROM skills_data)
      ORDER BY sd.speed DESC
      LIMIT 1;
END$$
DELIMITER ;
SELECT
AVG (speed)
FROM skills_data;