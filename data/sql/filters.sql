create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('cheese'), ('milk'), ('bread');
insert into product(name, type_id, expired_date, price) values ('tofu', 1, '2022-06-22', 5.5);
insert into product(name, type_id, expired_date, price) values ('feta', 1, '2022-07-22', 15.2);
insert into product(name, type_id, expired_date, price) values ('big ice-cream', 2, '2022-07-22', 10.7);
insert into product(name, type_id, expired_date, price) values ('bun', 3, '2022-06-22', 3.3);

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'cheese';

select * from product
where name like '%ice-cream%';

select * from product
where current_date > expired_date;

select max(price) from product; 

select t.name, count(p.type_id) from product p
join type t
on p.type_id = t.id
group by t.name;

select * from product p
join type t
on p.type_id = t.id
where t.name = 'cheese' or t.name = 'milk';

select t.name, count(p.type_id) 
from product p
join type t
on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

select p.name, p.type_id, p.expired_date, p.price, t.name type from product p
join type t
on p.type_id = t.id;
