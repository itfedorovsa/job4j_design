CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

CREATE OR REPLACE FUNCTION f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
RETURNS void
LANGUAGE 'plpgsql'
AS
$$
    BEGIN
        INSERT INTO products (name, producer, count, price)
        VALUES (i_name, prod, i_count, i_price);
    END;
$$;

SELECT f_insert_data('product_1', 'producer_1', 25, 50);

CREATE OR REPLACE FUNCTION f_update_data(u_count integer, tax float, u_id integer)
RETURNS integer
LANGUAGE 'plpgsql'
AS
$$
    DECLARE
        RESULT integer;
    BEGIN
        IF u_count > 0 THEN
            UPDATE products SET count = count - u_count WHERE id = u_id;
            SELECT INTO RESULT COUNT FROM products WHERE id = u_id;
        END IF;
        IF tax > 0 THEN
            UPDATE products set price = price + price * tax;
            SELECT INTO RESULT SUM(price) FROM products;
        END IF;
        RETURN RESULT;
    END;
$$;

SELECT f_update_data(10, 0, 1);

SELECT f_insert_data('product_2', 'producer_2', 15, 32);
SELECT f_insert_data('product_3', 'producer_3', 8, 115);

SELECT f_update_data(0, 0.2, 0);