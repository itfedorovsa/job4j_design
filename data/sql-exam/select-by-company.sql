CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer REFERENCES company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'AAA Inc'), (2, 'BBB Inc'), (3, 'CCC Inc'), (4, 'DDD Inc'), (5, 'EEE Inc');
INSERT INTO person (id, name, company_id) VALUES (1, 'A', 2), (2, 'B', 1), (3, 'C', 5), (4, 'D', 1), (5, 'E', 2);
INSERT INTO person (id, name, company_id) VALUES (6, 'F', 1), (7, 'G', 4), (8, 'H', 2), (9, 'I', 3), (10, 'J', 3);
INSERT INTO person (id, name, company_id) VALUES (11, 'K', 3), (12, 'L', 2), (13, 'M', 4), (14, 'N', 1), (15, 'O', 5);

SELECT p.name person, c.name company 
FROM person p
JOIN company c
ON p.company_id = c.id
WHERE c.id != 5
    
SELECT c.name company, count(p.company_id) people
FROM person p
JOIN company c
ON p.company_id = c.id
GROUP BY c.name
HAVING count(p.company_id) = (
    SELECT count(company_id)
    FROM person
    GROUP BY company_id
    ORDER BY count(company_id) desc
    LIMIT 1
);

