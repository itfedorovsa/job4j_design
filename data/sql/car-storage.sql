create table car(
    id serial primary key,
    model varchar(255)
);

create table body(
    id serial primary key,
    type varchar(255),
    car_id int references car(id)
);

create table engine(
    id serial primary key,
    volume float,
    car_id int references car(id)
);

create table gearbox(
    id serial primary key,
    gear int,
    car_id int references car(id)
);

insert into car (model) values ('corolla'), ('camry'), ('gt86');
insert into body (type, car_id) values ('sedan', 1), ('sedan', 2), ('hatchback', null), ('coupe', 3);
insert into engine (volume, car_id) values (1.6, 1), (2.0, null), (2.5, 3);
insert into gearbox (gear, car_id) values (4, null), (5, 1), (6, 2), (6, null);

select c.model, b.type body, e.volume engine, g.gear gearbox from car c
left join body b
on b.car_id = c.id
left join engine e
on e.car_id = c.id
left join gearbox g
on g.car_id = c.id;

select b.type from car c
full join body b
on b.car_id = c.id
where c.id is null;

select e.volume from car c
full join engine e 
on e.car_id = c.id
where c.id is null;

select g.gear from car c
full join gearbox g
on g.car_id = c.id
where c.id is null;