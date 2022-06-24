create table attachs(
    id serial primary key,
    attach varchar(255),
    item_id int references item(id)
);