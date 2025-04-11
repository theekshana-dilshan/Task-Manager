How to run backend & frontend

Backend:
* Add Dependencies:Include Spring Boot, Hibernate (JPA), MySQL, Spring Security, and JWT libraries in your pom.xml.
* Configure Database:Set the database connection, Hibernate settings, and JWT secret in application.properties.
* Create User Entity:Define a User entity with Hibernate annotations to map to the database.
* Set Up JWT:Create utility classes to generate and validate JWT tokens. Implement a filter to validate JWT in incoming requests.
* Authentication Endpoint:Create a login endpoint to authenticate users and return a JWT token.
* Run Application:Build the project and run it.

Fontend:
* Create Task Model: Define a model (Task) to represent the task structure.
* Task Service: Implement a service for handling API requests (get, create, update, delete tasks).
* Task Component: Build a component to display tasks and manage task creation.
* Task Component HTML: Create a form for adding tasks and a list to display existing tasks.
* Routing: Optionally, configure routing for task-related pages.
* HttpClientModule: Ensure HttpClientModule and FormsModule are imported in your app.module.ts.
* Run Application: Ensure both the backend and front-end are running.

Database setup details
* Add Dependencies: Include spring-boot-starter-data-jpa and mysql-connector-java in pom.xml.
* Configure Database: Add MySQL connection settings in application.properties (URL, username, password).
* Create Entity: Define entity classes with JPA annotations (@Entity, @Id).
* Enable JPA: Use @EnableJpaRepositories if necessary.
* Run: Hibernate will automatically configure based on the settings.


When using JWT:
* User Login: Client sends username and password to the backend.
* JWT Generation: Backend validates credentials and returns a JWT.
* JWT Storage: Client stores the JWT (usually in localStorage).
* Subsequent Requests: Client includes the JWT in the Authorization header for protected routes.
