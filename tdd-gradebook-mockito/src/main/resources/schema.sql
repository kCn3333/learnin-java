CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email_address VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS math_grade (
    id SERIAL PRIMARY KEY,
    grade NUMERIC(3,1),
    student_id INT REFERENCES student(id)
);

CREATE TABLE IF NOT EXISTS science_grade (
    id SERIAL PRIMARY KEY,
    grade NUMERIC(3,1),
    student_id INT REFERENCES student(id)
);

CREATE TABLE IF NOT EXISTS history_grade (
    id SERIAL PRIMARY KEY,
    grade NUMERIC(3,1),
    student_id INT REFERENCES student(id)
);

