CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    brand varchar(255),
    model varchar(255),
    owner_id int REFERENCES people(id)
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);