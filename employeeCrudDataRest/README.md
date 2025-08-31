## Employee-Crud
***

This project is a Spring Boot REST API for managing employees with Spring Data REST, Spring Security, and PostgreSQL.
It demonstrates how to expose repositories directly as REST endpoints and secure them with role-based access control.

---
### Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Data REST
- Spring Security
- PostgreSQL
- Maven

---
### Project Structure

`entity/Employee` – JPA entity representing an employee.

`dao/EmployeeRepository` – extends `JpaRepository<Employee, Long>` and is automatically exposed as REST endpoints via Spring Data REST.

`security/DemoSecurityConfig` – Spring Security configuration with fine-grained access rules for REST endpoints.

---
### REST Endpoints

Spring Data REST exposes the repository under `/api/employees`.
Examples:

`GET /api/employees` → list employees (paginated).

`GET /api/employees/{id}` → get employee by ID.

`POST /api/employees` → create a new employee.

`PUT /api/employees/{id}` → update employee.

`DELETE /api/employees/{id}` → delete employee.

---
### Security Rules

> ROLE_EMPLOYEE → can read (`GET`).

> ROLE_MANAGER → can create (`POST`) and update (`PUT`).

> ROLE_ADMIN → can delete (`DELETE`).

---
### Running the Application

1. Create the database and run `sql-scripts/schema.sql`.

2. Adjust `application.properties` with your DB credentials.

3. Start the app:
```
mvn spring-boot:run
```

4. Access API:

http://localhost:8080/api/employees

Authentication is required.