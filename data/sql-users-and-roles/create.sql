CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    role varchar(255)
);

CREATE TABLE rules(
    id SERIAL PRIMARY KEY,
    rule varchar(255)
);

CREATE TABLE states(
    id SERIAL PRIMARY KEY,
    state varchar(255)
);

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    category varchar(255)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    role_id int REFERENCES roles(id)
);

CREATE TABLE items(
    id SERIAL PRIMARY KEY,
    item varchar(255),
    user_id int REFERENCES users(id),
    category_id int REFERENCES categories(id),
    state_id int REFERENCES states(id)
);

CREATE TABLE comments(
    id SERIAL PRIMARY KEY,
    comment varchar(255),
    item_id int REFERENCES items(id)
);

CREATE TABLE attachs(
    id SERIAL PRIMARY KEY,
    attach varchar(255),
    item_id int REFERENCES items(id)
);

CREATE TABLE roles_rules(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    role_id int REFERENCES roles(id),
    rule_id int REFERENCES rules(id)
);