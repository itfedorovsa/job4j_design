create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('lake_fish', '900', '01.01.1960');
insert into fauna(name, avg_age) values ('rabbit', '12000');
insert into fauna(name, avg_age, discovery_date) values ('frog', '300', '01.01.1948');
insert into fauna(name, avg_age, discovery_date) values ('bear', '15000', '01.01.1920');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';
