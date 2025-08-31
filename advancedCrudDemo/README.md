## advancedCrudDemo
***

Demo Spring Boot (v3.4.5) project showcasing advanced CRUD operations using Spring Data JPA and PostgreSQL.
The application demonstrates entity relationships:

Instructor ↔ InstructorDetail (One-to-One)

Instructor ↔ Course (One-to-Many)

Course ↔ Review (One-to-Many)

Course ↔ Student (Many-to-Many)

---
### Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- PostgreSQL
- Maven

---
### Project Structure

`entity/` – domain entities: Course, Instructor, InstructorDetail, Review, Student.

`dao/` – data access layer (AppDao and its implementations).

`AdvancedCrudDemoApplication` – main class with CRUD examples executed via CommandLineRunner.

---
### Example CRUD Operations

The AdvancedCrudDemoApplication class contains ready-to-use methods demonstrating:

- creating an instructor with an associated `InstructorDetail`,

- adding courses to an instructor,

- adding students to a course,

- adding reviews to a course,

- fetching entities with their relationships (Eager/Join Fetch),

- updating and deleting entities (`Instructor`, `Course`, `Student`).


### Configuration

Create a PostgreSQL database with `sql-scripts/schema.sql`

Configure the connection in application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=postgres
spring.datasource.password=your_password
```


### Running the Application

In the project root directory:
```
mvn spring-boot:run
```

After startup, console logs will show which CRUD examples are executed from the `CommandLineRunner`.
You can enable different CRUD scenarios by uncommenting specific methods in `AdvancedCrudDemoApplication`.