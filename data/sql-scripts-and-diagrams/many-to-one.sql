create table cars(
    id serial primary key,
    brand varchar(255),
    model varchar(255),
    owner_id int references people(id)
);

create table people(
    id serial primary key,
    name varchar(255)
);