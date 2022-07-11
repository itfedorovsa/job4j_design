CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    brand varchar(50),
    model varchar(50)
);

INSERT INTO products (name, producer, count, price) VALUES ('Toyota', 'Supra');
INSERT INTO products (name, producer, count, price) VALUES ('BMW', 'E65');
INSERT INTO products (name, producer, count, price) VALUES ('Audi', 'A8');
INSERT INTO products (name, producer, count, price) VALUES ('Mercedes-Benz', 'CLS');

SELECT * FROM cars;

UPDATE cars SET model = 'S8' WHERE brand = 'Audi';