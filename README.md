# ToDoApp REST API

This project is a REST API for managing tasks and users, built with Spring Boot and Spring Security.

## Description

The API provides endpoints for:

* Creating, reading, updating, and deleting tasks.
* Creating, reading, updating, and deleting users.
* Authentication and authorization using JWT.

## Technologies Used

* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* H2 Database (for development)
* Maven
* JUnit and Mockito (for testing)

## Requirements

* Java 17 or higher
* Maven 3.6 or higher

## Setup

1.  Clone the repository:

    ```bash
    git clone [https://github.com/oPelayo/to_do_app.git](https://github.com/oPelayo/to_do_app.git)
    ```

2.  Navigate to the project directory:

    ```bash
    cd ToDoApp
    ```

3.  Build the project with Maven:

    ```bash
    ./mvnw clean install
    ```

4.  Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

The API will be available at `http://localhost:8080`.

## Endpoints

### Users

* `POST /users/register`: Register a new user.
* `POST /users/login`: Log in and get a JWT token.
* `GET /users/{id}`: Get a user by ID.
* `PUT /users/{id}`: Update a user.
* `DELETE /users/{id}`: Delete a user.

### Tasks

* `POST /tasks`: Create a new task.
* `GET /tasks/{id}`: Get a task by ID.
* `GET /tasks`: Get all tasks.
* `PUT /tasks/{id}`: Update a task.
* `DELETE /tasks/{id}`: Delete a task.
* `GET /tasks/user/{userId}`: Get tasks by user.
* `GET /tasks/status/{status}`: Get tasks by status.
* `GET /tasks/priority/{priority}`: Get tasks by priority.
* `GET /tasks/date/{date}`: Get tasks by date.
* `GET /tasks/category/{category}`: Get tasks by category.
* `GET /tasks/tags/{tags}`: Get tasks by tags.
* `GET /tasks/completed`: Get completed tasks.
* `GET /tasks/pending`: Get pending tasks.
* `GET /tasks/in-progress`: Get in-progress tasks.
* `GET /tasks/cancelled`: Get cancelled tasks.
* `GET /tasks/date-range`: Get tasks by date range.

## Testing

To run the unit tests:

```bash
./mvnw test
```

##Contributing
Contributions are welcome. Please create a Pull Request with your changes.

##License
This project is under the MIT license.
