CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

INSERT INTO products (name, count, price) VALUES ('product_1', 1, 5), ('product_2', 2, 10), ('product_3', 3, 15), ('product_4', 4, 20);
INSERT INTO products (name, count, price) VALUES ('product_5', 5, 25), ('product_6', 6, 30), ('product_7', 7, 35), ('product_8', 8, 40);
INSERT INTO products (name, count, price) VALUES ('product_9', 9, 45), ('product_10', 10, 50), ('product_11', 11, 55), ('product_12', 12, 60);
INSERT INTO products (name, count, price) VALUES ('product_13', 13, 65), ('product_14', 14, 70), ('product_15', 15, 75), ('product_16', 16, 80);
INSERT INTO products (name, count, price) VALUES ('product_17', 17, 85), ('product_18', 18, 90), ('product_19', 19, 95), ('product_20', 20, 100);

BEGIN TRANSACTION;

DECLARE back_cursor SCROLL CURSOR FOR SELECT * FROM products;

FETCH LAST back_cursor;

FETCH BACKWARD 5 FROM back_cursor;

MOVE BACKWARD 5 FROM back_cursor;

FETCH BACKWARD 5 FROM back_cursor;

FETCH FIRST FROM back_cursor;

CLOSE back_cursor;

COMMIT TRANSACTION;