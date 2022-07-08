CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    brand varchar(255),
    model varchar(255),
    year int,
    person_id int REFERENCES people(id)
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);

SELECT c.brand, c.model, c.person_id
FROM cars c
JOIN people p
ON c.person_id = p.id;

SELECT p.name, p.id, c.year
FROM cars c
JOIN people p
ON c.person_id = p.id;

SELECT c.person_id id, p.name nm, c.brand br, c.model md, c.year yr
FROM cars c
JOIN people p
ON c.person_id = p.id;
