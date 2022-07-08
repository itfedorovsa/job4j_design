CREATE TABLE departments(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE employees(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    department_id int REFERENCES departments(id)
);

INSERT INTO departments(name) VALUES ('manager'), ('worker'), ('trainee');
INSERT INTO employees(name, department_id) VALUES ('Tom', 1), ('Bob', 2), ('Jack', 2), ('John', null);

SELECT * FROM employees e
LEFT JOIN departments d
ON e.department_id = d.id;

SELECT * FROM employees e
RIGHT JOIN departments d
ON e.department_id = d.id;

SELECT * FROM employees e
FULL JOIN departments d
ON e.department_id = d.id;

SELECT * FROM employees e
CROSS JOIN departments d;

SELECT * FROM departments d
LEFT JOIN employees e
ON e.department_id = d.id
WHERE e.department_id IS NULL;

SELECT * FROM departments d
LEFT JOIN employees e
ON e.department_id = d.id
WHERE e.department_id IS NOT NULL;

SELECT * FROM departments d
RIGHT JOIN employees e
ON e.department_id = d.id
WHERE e.department_id IS NOT NULL;

CREATE TABLE teens(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    gender varchar(255)
);

INSERT INTO teens(name, gender) VALUES ('Alice', 'F'), ('Sarah', 'F');
INSERT INTO teens(name, gender) VALUES ('Bob', 'M'), ('Bill', 'M'), ('Joe', 'M');

SELECT * FROM teens t1 CROSS JOIN teens t2
WHERE t1.gender = 'M' AND t2.gender = 'F';