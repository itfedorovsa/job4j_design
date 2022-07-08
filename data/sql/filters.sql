CREATE TABLE type(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    type_id int REFERENCES type(id),
    expired_date date,
    price float
);

INSERT INTO type(name) VALUES ('cheese'), ('milk'), ('bread');
INSERT INTO product(name, type_id, expired_date, price) VALUES ('tofu', 1, '2022-06-22', 15.2);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('feta', 1, '2022-07-22', 15.2);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('big ice-cream', 2, '2022-07-22', 15.2);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('bun', 3, '2022-06-22', 3.3);

SELECT * FROM product AS p
JOIN type AS t
ON p.type_id = t.id
WHERE t.name = 'cheese';

SELECT * FROM product
WHERE name LIKE '%ice-cream%';

SELECT * FROM product
WHERE current_date > expired_date;

SELECT * FROM product p
WHERE p.price = (SELECT max(p1.price) FROM product p1);

SELECT t.name, count(p.type_id) FROM product p
JOIN type t
ON p.type_id = t.id
GROUP BY t.name;

SELECT * FROM product p
JOIN type t
ON p.type_id = t.id
WHERE t.name = 'cheese' OR t.name = 'milk';

SELECT t.name, count(p.type_id)
FROM product p
JOIN type t
ON p.type_id = t.id
GROUP BY t.name
HAVING count(p.type_id) < 10;

SELECT p.name, p.type_id, p.expired_date, p.price, t.name type
FROM product p
JOIN type t
ON p.type_id = t.id;
