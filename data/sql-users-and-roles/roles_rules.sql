create table roles_rules(
    id serial primary key,
    name varchar(255),
    role_id int references roles(id),
    rule_id int references rules(id)
);