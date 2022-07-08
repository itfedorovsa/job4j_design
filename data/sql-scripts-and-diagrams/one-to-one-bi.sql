CREATE TABLE diplomas(
    id SERIAL PRIMARY KEY,
    number varchar(255)
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE people_diplomas(
    id SERIAL PRIMARY KEY,
    diploma_id int REFERENCES diplomas(id) UNIQUE,
    person_id int REFERENCES people(id)
);