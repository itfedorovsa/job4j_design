CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

CREATE OR REPLACE FUNCTION discount()
    RETURNS TRIGGER AS
$$
    BEGIN
        UPDATE products
        SET price = price - price * 0.2
        WHERE count <= 5;
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER discount_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE discount();

INSERT INTO products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

ALTER TABLE products DISABLE TRIGGER discount_trigger;

DROP TRIGGER discount_trigger ON products;