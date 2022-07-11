CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50), ('product_2', 'producer_2', 15, 32);
INSERT INTO products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

BEGIN TRANSACTION;

SAVEPOINT first;
SELECT * FROM products;

INSERT INTO products (name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 100);
SAVEPOINT second;
SELECT * FROM products;

DELETE FROM products;
SELECT * FROM products;

ROLLBACK TO SAVEPOINT second;
SELECT * FROM products;

ROLLBACK TO SAVEPOINT first;
SELECT * FROM products;

COMMIT TRANSACTION;
