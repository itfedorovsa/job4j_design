create table cars(
    id serial primary key,
    brand varchar(255),
    model varchar(255),
    year int,
    person_id int references people(id)
);

create table people(
    id serial primary key,
    name varchar(255)
);

select c.brand, c.model, c.person_id from cars c
join people p
on c.person_id = p.id;

select p.name, p.id, c.year from cars c
join people p
on c.person_id = p.id;

select c.person_id id, p.name nm, c.brand br, c.model md, c.year yr from cars c
join people p
on c.person_id = p.id;
