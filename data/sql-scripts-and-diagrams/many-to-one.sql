create table cars(
    id serial primary key,
    brand varchar(255),
    model varchar(255)
);

create table people(
    id serial primary key,
    car_id int references cars(id),
    name varchar(255)
);