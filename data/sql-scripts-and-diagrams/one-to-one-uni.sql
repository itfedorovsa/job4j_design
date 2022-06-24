create table diplomas(
    id serial primary key,
    number varchar(255)
);

create table people(
    id serial primary key,
    diploma_id int references diplomas(id) unique,
    name varchar(255)
);