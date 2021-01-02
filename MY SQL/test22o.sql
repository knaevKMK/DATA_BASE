CREATE SCHEMA village;

CREATE TABLE house
(
    id     INT PRIMARY KEY NOT NULL,
    name   VARCHAR(15)     NOT NULL,
    street VARCHAR(50) NULL
);

INSERT INTO house (ID, NAME)
VALUES (1, 'Bungalo'), (2, 'Motel');

