# EX_1
SELECT title FROM book_library.books
WHERE title LIKE 'The%'
# WHERE SUBSTRING(title, 1, 4) = 'The'
ORDER BY id;

# EX_2
SELECT REPLACE(title, 'The', '***') AS title
FROM book_library.books
WHERE title LIKE 'The%';
# WHERE SUBSTRING(title, 1, 3)= 'The';

#EX_3


#EX_4
#SELECT (first_name + ' '+ last_name) AS `Full Name`,
SELECT concat(first_name, ' ', last_name) AS `Full Name`,
TIMESTAMPDIFF(DAY, born, died) AS `Days Lived`
FROM authors;

#EX_5 
SELECT titles FROM books
WHERE titles LIKE 'Harry Potter%'
ORDER BY id;