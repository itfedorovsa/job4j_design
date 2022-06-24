create table users(
    id serial primary key,
    name varchar(255),
    role_id int references roles(id)
);