CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

CREATE OR REPLACE PROCEDURE insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
LANGUAGE 'plpgsql'
AS $$
    BEGIN
    INSERT INTO products (name, producer, count, price)
    VALUES (i_name, prod, i_count, i_price);
    END
$$;

CALL insert_data('product_2', 'producer_2', 15, 32);

CREATE OR REPLACE PROCEDURE update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
AS $$
    BEGIN
        IF u_count > 0 THEN
            UPDATE products SET count = count - u_count WHERE id = u_id;
        END if;
        if tax > 0 THEN
            UPDATE products SET price = price + price * tax;
        END if;
    END;
$$;

CALL update_data(10, 0, 1);

CALL insert_data('product_1', 'producer_1', 3, 50);
CALL insert_data('product_3', 'producer_3', 8, 115);

ALTER PROCEDURE update_data(u_count integer, tax float, u_id integer) RENAME TO update;

DROP PROCEDURE update(u_count integer, tax float, u_id integer);