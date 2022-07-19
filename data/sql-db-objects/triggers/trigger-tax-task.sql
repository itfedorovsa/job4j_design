CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

CREATE OR REPLACE FUNCTION tax()
    RETURNS TRIGGER AS
$$
    BEGIN
        UPDATE products
        SET price = price * 1.2
        WHERE id = (SELECT id FROM inserted);
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE tax();

INSERT INTO products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);


