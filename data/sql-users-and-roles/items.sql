create table items(
    id serial primary key,
    item varchar(255),
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id)
);