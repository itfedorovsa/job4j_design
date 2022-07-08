CREATE TABLE apps(
  id SERIAL PRIMARY KEY,
  name varchar(255)
);

CREATE TABLE programmists(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE programmist_apps(
    id SERIAL PRIMARY KEY,
    app_id int REFERENCES apps(id),
    programmist_id int REFERENCES programmists(id)
);