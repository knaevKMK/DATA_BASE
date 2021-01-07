#EX_1
SELECT COUNT(*) AS `count`
FROM gringotts.wizzard_depostits;

#EX_2
SELECT MAX (longest_magic_wand) AS longest_magic_wand
FROM wizzard_depostits;

#EX_3
SELECT deposit_group,
 MAX(longest_magic_wand) AS `lmw`
 GROUP BY deposit_group
 ORDER BY `lms`, deposit_group;
 
 #EX_4
 SELECT wz.deposit_group
 FROM wizzard_depostits AS wz
 GROUP BY wz.deposit_group
 ORDER BY AVG(magic_wand_size)
 LIMIT 1;
 
 #EX_5
SELECT `deposit_group`, SUM (`deposit_amount`) AS `total_sum`
FROM wizzard_deposits
GROUP BY `deposit_group`
ORDER BY `total_sum`;

#EX_6
SELECT `deposit_group`, SUM(deposti_amount) AS total_sum
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander Family'
GROUP BY deposit_group
ORDER BY deposti_group;

#EX_7
SELECT deposti_group, SUM (deposit_ampount) AS total_sum
FROM wizzard_depostits
WHERE magic_wand_creator = 'Ollivander Family'
GROUP BY deposit_group
HAVING total_sum < 150000
ORDER BY total_sum DESC;

#EX_8
SELECT deposit_group, magic_wand_creator, 
MIN (deposti_charge) AS `min_deposit_charge`
FROM wizzard_deposits
GROUP BY deposit_group, magic_wand_creator
ORDER BY magic_wand_creator, deopsit_group;

#EX_9
SELECT 
( CASE 
 WHEN age BETWEEN 0 AND 10 THEN `[0-10]`
 WHEN age BETWEEN 11 AND 20 THEN `[11-20]`
 WHEN age BETWEEN 21 AND 30 THEN `[21-30]`
 WHEN age BETWEEN 31 AND 40 THEN `[31-40]`
 WHEN age BETWEEN 41 AND 50 THEN `[41-50]`
 WHEN age BETWEEN 51 AND 60 THEN `[51-60]`
 ELSE `[61+]`
 END
) AS age_group, 
COUNT(*) AS wizzard_count
FROM wizzard_deposit
ORDER BY age_group;

#EX_10
