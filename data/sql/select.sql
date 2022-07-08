CREATE TABLE fauna (
    id SERIAL PRIMARY KEY,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('lake_fish', '900', '1960-02-25');
INSERT INTO fauna(name, avg_age) VALUES ('rabbit', '12000');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('frog', '300', '1948-05-14');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('bear', '15000', '1920-09-30');

SELECT * FROM fauna WHERE name LIKE '%fish%';
SELECT * FROM fauna WHERE avg_age >= 10000 AND avg_age <= 21000;
SELECT * FROM fauna WHERE discovery_date IS NULL;
SELECT * FROM fauna WHERE discovery_date < '1950-01-01';
