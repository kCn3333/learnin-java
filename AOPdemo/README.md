## AOPdemo
***

This is a Spring Boot AOP (Aspect-Oriented Programming) demo project that illustrates how to use aspects to add cross-cutting concerns (such as logging) to DAOs and services.

The application runs from a `CommandLineRunner` and demonstrates how aspects are triggered during method executions on DAOs.

---
### Technologies

- Java 21
- Spring Boot 3.4.5
- Spring AOP (spring-boot-starter-aop)
- Maven 

---
### Project Structure

- `aspect/` – AOP components

  - `AopExpressions` – reusable pointcut definitions.

  - `MyDemoLoggingAspect` – logging aspect (e.g. @Before, @After, @Around).

- `dao/` – Data Access Objects

  - `AccountDAO` – simulates account-related operations (addAccount, getName).

  - `MembershipDAO` – simulates membership-related operations.

- `AoPdemoApplication` – main Spring Boot application. 

     - Bootstraps the app with `@SpringBootApplication` and `@EnableAspectJAutoProxy`.

     - Demonstrates AOP advice through a `CommandLineRunner`.

---
### Example Workflow

When the app starts:

1. An `Account` object is created.

2. `demoBeforeAdvice()` invokes DAO methods (`addAccount`, `getName`).

3. Aspects from `MyDemoLoggingAspect` are automatically triggered before/after these method calls.

4. The program simulates operations, waits (`Thread.sleep`), and calls DAO methods again, so aspects run multiple times.

---
### Running the Application

In the project root:
```
mvn spring-boot:run
```

You should see console logs showing both DAO method outputs and aspect logs from `MyDemoLoggingAspect`.