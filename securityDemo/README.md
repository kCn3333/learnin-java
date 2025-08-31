## securityDemo
***

This project demonstrates Spring Security integration with Spring Boot, Thymeleaf, and PostgreSQL.
It shows how to secure routes based on user roles, authenticate users with a database, and customize login/logout pages.
---
### Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Security 6
- Spring Data JPA
- Thymeleaf + Thymeleaf Spring Security Extras
- PostgreSQL
- Maven

---
### Project Structure

- `controller/`

  - `DemoController` – handles requests for different protected pages.

  - `LoginController` – serves the custom login form.

- `security/`

- `DemoSecurityConfig` – Spring Security configuration:

  - JDBC-based authentication with custom queries.

  - Role-based access rules.

  - Custom login/logout and access-denied pages.

- `resources/templates/` – Thymeleaf templates (e.g. `loginForm.html`, `access-denied.html`, `role-based pages`).

- `resources/static/` – static resources (`/images`, `/css`, `/js`) publicly accessible.

---
### Database Schema

The application expects two tables:
```sql
CREATE TABLE users (
user_id VARCHAR(50) PRIMARY KEY,
pw VARCHAR(68) NOT NULL,
active BOOLEAN NOT NULL
);

CREATE TABLE roles (
user_id VARCHAR(50) NOT NULL,
role VARCHAR(50) NOT NULL,
CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

`users` → holds credentials (`user_id`, `pw`, `active`).

`roles` → holds authorities (`role`) for each user.

---
### Security Configuration

Key elements from DemoSecurityConfig:

- `/` → accessible to `ROLE_USER`.

- `/leaders/**` → accessible to `ROLE_MANAGER`.

- `/systems/**` → accessible to `ROLE_ADMIN`.

- Static resources (`/images`, `/css`, `/js`) are always accessible.

- Custom login form: `/loginForm`.

- Failed authorization → redirects to `/access-denied`.

---
### Running the Application

1. Create the database and tables in PostgreSQL with `sql-script/schema.sql`

2. Configure connection in application.properties

3. Start the application:
```
mvn spring-boot:run
```

Navigate to:

http://localhost:8080/ → requires `ROLE_USER`.

http://localhost:8080/leaders/ → requires `ROLE_MANAGER`.

http://localhost:8080/systems/ → requires `ROLE_ADMIN`.