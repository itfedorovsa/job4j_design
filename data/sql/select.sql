create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('lake_fish', '900', '1960-02-25');
insert into fauna(name, avg_age) values ('rabbit', '12000');
insert into fauna(name, avg_age, discovery_date) values ('frog', '300', '1948-05-14');
insert into fauna(name, avg_age, discovery_date) values ('bear', '15000', '1920-09-30');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
