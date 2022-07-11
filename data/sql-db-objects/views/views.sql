CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name varchar(50)
);

INSERT INTO students (name) VALUES ('Tony Smith'), ('Bob Fischer'), ('Johnny Depp');

CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name varchar(50)
);

INSERT INTO authors (name) VALUES ('Agatha Christie');
INSERT INTO authors (name) VALUES ('Stephen King');

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    name varchar(200),
    author_id integer REFERENCES authors(id)
);

INSERT INTO books (name, author_id) VALUES ('Hercule Poirot: The First Cases', 1);
INSERT INTO books (name, author_id) VALUES ('Murder On The Orient Express', 1);
INSERT INTO books (name, author_id) VALUES ('Evil Under The Sun', 1);
INSERT INTO books (name, author_id) VALUES ('The Shining', 2);
INSERT INTO books (name, author_id) VALUES ('The Stand', 2);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    active boolean DEFAULT true,
    book_id integer REFERENCES books(id),
    student_id integer REFERENCES students(id)
);

INSERT INTO orders (book_id, student_id) VALUES (1, 1);
INSERT INTO orders (book_id, student_id) VALUES (3, 1);
INSERT INTO orders (book_id, student_id) VALUES (5, 2);
INSERT INTO orders (book_id, student_id) VALUES (4, 1);
INSERT INTO orders (book_id, student_id) VALUES (2, 2);

SELECT count(o.active), s.name student_name, b.name book_name, a.name author_name FROM students s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON b.author_id = a.id
         WHERE s.name LIKE '%Tony%' AND a.name != 'Stephen King'
         GROUP BY s.name, b.name, a.name;

SELECT s.name AS student, a.name, a.name author FROM students s
    JOIN orders o ON s.id = o.student_id
    JOIN books b ON o.book_id = b.id
    JOIN authors a ON b.author_id = a.id
    GROUP BY (s.name, a.name)
    HAVING COUNT(a.name) >= 2;

CREATE VIEW show_students_with_2_or_more_books
    AS SELECT s.name AS student, count(a.name), a.name AS author FROM students AS s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON b.author_id = a.id
         GROUP BY (s.name, a.name)
         HAVING COUNT(a.name) >= 2;

CREATE VIEW show_students_with_many_conditions
    AS SELECT o.active, s.name student_name, b.name book_name, a.name author_name FROM students s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON b.author_id = a.id
         WHERE (s.name LIKE '%Tony%' OR s.name LIKE '%Johnny%')
         AND a.name != 'Stephen King'
         AND o.active = 'true'
         ORDER BY b.name ASC;

SELECT * FROM show_students_with_2_or_more_books;

ALTER VIEW show_students_with_2_or_more_books RENAME TO show_all_students_with_2_or_more_books;

DROP VIEW show_all_students_with_2_or_more_books;



