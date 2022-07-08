CREATE TABLE body(
    id SERIAL PRIMARY KEY,
    type varchar(255)
);

CREATE TABLE engine(
    id SERIAL PRIMARY KEY,
    volume float
);

CREATE TABLE gearbox(
    id SERIAL PRIMARY KEY,
    gear int
);

CREATE TABLE car(
    id SERIAL PRIMARY KEY,
    model varchar(255),
    body_id int REFERENCES body(id),
    engine_id int REFERENCES engine(id),
    gearbox_id int REFERENCES gearbox(id)
);

INSERT INTO body (type) VALUES ('sedan'), ('hatchback'), ('coupe');
INSERT INTO engine (volume) VALUES (1.6), (2.0), (2.5);
INSERT INTO gearbox (gear) VALUES (4), (5), (6);
INSERT INTO car (model, body_id, engine_id, gearbox_id) VALUES ('corolla', 1, 1, 2);
INSERT INTO car (model, body_id, engine_id, gearbox_id) VALUES ('camry', 1, null, 3);
INSERT INTO car (model, body_id, engine_id, gearbox_id) VALUES ('gt86', 3, 3, null);

SELECT c.model, b.type, e.volume, g.gear
FROM car c
LEFT JOIN body b
ON c.body_id = b.id
LEFT join engine e
ON c.engine_id = e.id
LEFT join gearbox g
ON c.gearbox_id = g.id;

SELECT b.type
FROM body b
LEFT JOIN car c
ON c.body_id = b.id
WHERE c.body_id IS NULL;

SELECT e.volume
FROM engine e
LEFT JOIN car c
ON c.engine_id = e.id
WHERE c.engine_id IS NULL;

SELECT g.gear
FROM gearbox g
LEFT JOIN car c
ON c.gearbox_id = g.id
WHERE c.gearbox_id IS NULL;