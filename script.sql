create table car(
    id serial primary key,
    brand varchar(30),
    model text,
    year numeric(4)
);
insert into car(brand, model, year) values ('Toyota', 'Supra', '1999');
update car set year = '1998';
delete from car;