CREATE TABLE diplomas(
    id SERIAL PRIMARY KEY,
    number varchar(255)
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    diploma_id int REFERENCES diplomas(id) UNIQUE,
    name varchar(255)
);