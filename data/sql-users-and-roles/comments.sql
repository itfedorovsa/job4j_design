create table comments(
    id serial primary key,
    comment varchar(255),
    item_id int references item(id)
);