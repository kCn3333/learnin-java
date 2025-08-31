DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
-- Table structure for `users`
CREATE TABLE users (
   username VARCHAR(50) PRIMARY KEY,
   password VARCHAR(100) NOT NULL,
   enabled BOOLEAN NOT NULL
);
-- Inserting data into `users`
INSERT INTO users (username, password, enabled) VALUES
('john', '{bcrypt}$2a$12$q05qWwgnrllvTnU/68qtOOItBOAjfFQCnSRJhr7gMw14fPhWpgVH2', true),
('suzan', '{bcrypt}$2a$12$q05qWwgnrllvTnU/68qtOOItBOAjfFQCnSRJhr7gMw14fPhWpgVH2', true),
('bob', '{bcrypt}$2a$12$q05qWwgnrllvTnU/68qtOOItBOAjfFQCnSRJhr7gMw14fPhWpgVH2', true);
-- Table structure for `authorities`
CREATE TABLE authorities (
   username VARCHAR(50) NOT NULL,
   authority VARCHAR(50) NOT NULL,
   CONSTRAINT authorities_pk PRIMARY KEY (username, authority),
   CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES users(username)
);
-- Inserting data into `authorities`
INSERT INTO authorities (username, authority) VALUES
('john', 'ROLE_USER'),
('suzan', 'ROLE_USER'),
('suzan', 'ROLE_MANAGER'),
('bob', 'ROLE_USER'),
('bob', 'ROLE_MANAGER'),
('bob', 'ROLE_ADMIN');