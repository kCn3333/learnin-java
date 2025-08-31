-- PostgreSQL version of the employee_directory security schema

-- Drop tables if they exist
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

-- Create 'users' table
CREATE TABLE users (
  user_id VARCHAR(50) PRIMARY KEY,
  pw VARCHAR(68) NOT NULL,
  active BOOLEAN NOT NULL
);

-- Insert data into 'users' (password: test)
INSERT INTO users (user_id, pw, active) VALUES
('john', '{bcrypt}$2a$12$q05qWwgnrllvTnU/68qtOOItBOAjfFQCnSRJhr7gMw14fPhWpgVH2', true),
('mary', '{bcrypt}$2a$12$q05qWwgnrllvTnU/68qtOOItBOAjfFQCnSRJhr7gMw14fPhWpgVH2', true),
('bob', '{bcrypt}$2a$12$q05qWwgnrllvTnU/68qtOOItBOAjfFQCnSRJhr7gMw14fPhWpgVH2', true);

-- Create 'roles' table
CREATE TABLE roles (
  user_id VARCHAR(50) NOT NULL,
  role VARCHAR(50) NOT NULL,
  CONSTRAINT roles_pk PRIMARY KEY (user_id, role),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Insert data into 'roles'
INSERT INTO roles (user_id, role) VALUES
('john', 'ROLE_USER'),
('mary', 'ROLE_USER'),
('mary', 'ROLE_MANAGER'),
('bob', 'ROLE_USER'),
('bob', 'ROLE_MANAGER'),
('bob', 'ROLE_ADMIN');
