CREATE TABLE car(
    id SERIAL PRIMARY KEY,
    brand varchar(30),
    model text,
    year numeric(4)
);
INSERT INTO car(brand, model, year) VALUES ('Toyota', 'Supra', '1999');
UPDATE car SET year = '1998';
DELETE FROM car;