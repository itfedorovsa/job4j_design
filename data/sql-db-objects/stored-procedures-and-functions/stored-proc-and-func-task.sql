CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

INSERT INTO products (name, producer, count, price) VALUES ('milk1', 'producer_1', 25, 50), ('milk2', 'producer_1', 25, 50);
INSERT INTO products (name, producer, count, price) VALUES ('milk3', 'producer_1', 25, 50), ('bread1', 'producer_2', 25, 50);
INSERT INTO products (name, producer, count, price) VALUES ('bread2', 'producer_2', 25, 50), ('bread3', 'producer_2', 25, 50);
INSERT INTO products (name, producer, count, price) VALUES ('fruits1', 'producer_3', 25, 50), ('fruits2', 'producer_3', 25, 50);
INSERT INTO products (name, producer, count, price) VALUES ('fruits3', 'producer_3', 25, 50);

CREATE OR REPLACE PROCEDURE delete_by_name()
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        DELETE FROM products
        WHERE name LIKE '%milk%';
    END
$$;

CALL delete_by_name();

CREATE OR REPLACE FUNCTION del_by_name()
RETURNS void
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        DELETE FROM products
        WHERE producer = 'producer_2';
    END
$$;

SELECT del_by_name();