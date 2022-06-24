create table diplomas(
    id serial primary key,
    number varchar(255)
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table people_diplomas(
    id serial primary key,
    diploma_id int references diplomas(id) unique,
    person_id int references people(id)
);