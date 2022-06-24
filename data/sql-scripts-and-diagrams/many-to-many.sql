create table apps(
  id serial primary key,
  name varchar(255)
);

create table programmists(
    id serial primary key,
    name varchar(255)
);

create table programmist_apps(
    id serial primary key,
    app_id int references apps(id),
    programmist_id int references programmists(id)
);