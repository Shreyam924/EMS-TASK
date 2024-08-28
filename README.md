***********  Employee Management System (EMS) ***************
This project is a Spring Boot-based Employee Management System (EMS) that implements role-based access control and provides basic CRUD operations for managing employees.


1.Role-Based Access Control:
  Restrict access to certain endpoints based on user roles (ADMIN, USER).
  Only users with the ADMIN role can create, update, or delete employees.

2.User Authentication and Authorization:
  Integrated Spring Security for handling authentication and authorization.
  Custom user management using UserDetailsService.

3.CRUD Operations:
  Create, read, update, and delete operations for employees.
  Separate service classes for each operation to ensure modularity and testability.

4.DTO and Entity Mapping:
  DTOs (Data Transfer Objects) are used to decouple the API layer from the database layer.
  Custom mappers are implemented to convert between DTOs and entities.

5.Password Encoding:
  User passwords are securely stored using BCrypt password encoding.

6.Database Integration:
  PostgreSQL is used as the relational database.
  Spring Data JPA is used for database access.

7.RESTful API Endpoints:
  Exposes RESTful endpoints for user and employee management.
  Uses standard HTTP methods: GET, POST, PUT, DELETE.

8.Custom Error Handling:
  Implemented custom error messages for cases such as "User not found" or "Employee not found".

9.Spring Profiles:
  Default profile setup, can be extended for multiple environments like dev, test, prod.

10.Logging and Debugging:
  Integrated with SLF4J and Logback for logging.
  Configurable logging levels for better debugging and monitoring.

11.JUnit Test Coverage:
  Comprehensive unit tests for all service and controller classes.
  Focus on achieving 90%+ test coverage.

12.Configuration Properties:
  Customizable application properties for database configuration, security settings, etc.

13.Security Configuration:
  Detailed security setup with SecurityFilterChain.
  Customized endpoints and role-specific access rules.

14.OpenAPI Documentation (Optional):
  Can be integrated with Swagger/OpenAPI for API documentation.

15.Running the Project:
  Use mvn spring-boot:run to start the application.
  Application runs on localhost:8080 by default.
