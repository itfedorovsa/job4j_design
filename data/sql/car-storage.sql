create table body(
    id serial primary key,
    type varchar(255)
);

create table engine(
    id serial primary key,
    volume float
);

create table gearbox(
    id serial primary key,
    gear int
);

create table car(
    id serial primary key,
    model varchar(255),
    body_id int references body(id),
    engine_id int references engine(id),
    gearbox_id int references gearbox(id)
);

insert into body (type) values ('sedan'), ('hatchback'), ('coupe');
insert into engine (volume) values (1.6), (2.0), (2.5);
insert into gearbox (gear) values (4), (5), (6);
insert into car (model, body_id, engine_id, gearbox_id) values ('corolla', 1, 1, 2);
insert into car (model, body_id, engine_id, gearbox_id) values ('camry', 1, null, 3);
insert into car (model, body_id, engine_id, gearbox_id) values ('gt86', 3, 3, null);

select c.model, b.type, e.volume, g.gear from car c
left join body b
on c.body_id = b.id
left join engine e
on c.engine_id = e.id
left join gearbox g
on c.gearbox_id = g.id;

select b.type from body b
left join car c
on c.body_id = b.id
where c.body_id is null;

select e.volume from engine e
left join car c
on c.engine_id = e.id
where c.engine_id is null;

select g.gear from gearbox g
left join car c
on c.gearbox_id = g.id
where c.gearbox_id is null;