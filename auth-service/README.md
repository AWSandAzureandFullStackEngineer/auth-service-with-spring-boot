# Auth Service

A Spring Boot-based authentication service implementing **JWT** and **OAuth2** for secure user authentication and authorization. Built using **Java 21** with clean architecture principles.

---

## Table of Contents

- [Project Setup](#project-setup)
- [Running the Project](#running-the-project)
- [User Domain Class](#user-domain-class)
- [Contributing](#contributing)

---

## Project Setup

### Prerequisites

- **Java 21** or higher
- **Maven**
- **Git**
- (Optional) **IDE** (e.g., IntelliJ IDEA, Eclipse, or VS Code)

### Step 1: Clone or Fork the Repository

#### Clone the Repository

To clone the repository locally:
```bash
git clone https://github.com/YOUR_USERNAME/auth-service.git
```

Fork the Repository
To make changes or contribute:

Go to the repository URL: https://github.com/ORIGINAL_OWNER/auth-service
Click on Fork in the top right.
Clone your fork:

``` 
git clone https://github.com/YOUR_USERNAME/auth-service.git
```

Step 2: Build and Run the Project
Navigate to the Project Directory:

1. Navigate to the Project Directory:
```
cd auth-service
```

2. Build the Project using Maven:

```
mvn clean install
```

3. Run the Project:

```
mvn spring-boot:run
```

# Running the Project on Different Operating Systems

## Mac/Linux
1. Open a terminal and navigate to the project directory.

2. Follow the Build and Run steps above.

## Windows
1. Open Command Prompt or PowerShell.

2. Navigate to the project directory.

3. Follow the Build and Run steps above.

# User Domain Class

The User class represents essential information about each user in the system and forms the basis for authentication and authorization.

File Location: src/main/java/com/example/auth/domain/User.java

## Purpose
The User class manages user data in the auth-service to handle secure authentication and authorization.

## Fields
- id (Long): Primary key for each user, generated automatically.
- username (String): The user’s unique login name, with a non-null constraint.
- password (String): Hashed password stored securely.
- roles (Set<String>): Collection of roles assigned to the user (e.g., ROLE_USER, ROLE_ADMIN).

## Annotations
- @Entity: Specifies the class as a JPA entity.
- @Table: Customizes the table name for the entity.
- @Id: Designates id as the primary key.
- @GeneratedValue: Defines how the primary key is generated.
- @Column: Sets up column-level constraints, such as uniqueness and nullability.
- @ElementCollection: Maps the roles field to a separate user_roles table.

## Example
Creating a new User instance:

```
Set<String> roles = new HashSet<>(Arrays.asList("ROLE_USER"));
User newUser = new User(null, "john_doe", "hashed_password_here", roles);
```

# Contributing
To contribute, follow these steps:

1. Fork the repository.

2. Clone your fork and make changes.

3. Commit and push changes:

```
git add .
git commit -m "Describe your changes here"
git push origin main
```

4. Create a pull request on GitHub.

This README should help new users set up the project, understand its purpose, and easily start working with the User domain class. Let me know if there’s anything specific you’d like to add!

```
---

This **README.md** file includes all the setup instructions, detailed documentation on the `User` domain class, and guidelines for contributing to the repository. Let me know if any additional sections or adjustments are needed!
```
## DTO Classes

Data Transfer Objects (DTOs) are used to handle data between the client and server during the authentication process. The `auth-service` includes two main DTOs for this purpose: `AuthRequest` and `AuthResponse`.

### `AuthRequest` Class

The `AuthRequest` class represents the data received during a login attempt.

**File Location**: `src/main/java/com/example/auth/dto/AuthRequest.java`

- **Fields**:
    - `username` (`String`): The username provided by the user.
    - `password` (`String`): The user's password.
- **Annotations**:
    - **@Data**: Generates getters, setters, and other standard methods.
    - **@NoArgsConstructor**: Generates a no-arguments constructor.
    - **@AllArgsConstructor**: Generates a constructor with all arguments.

#### Example Usage:

```java
AuthRequest request = new AuthRequest("john_doe", "password123");
```

These DTO classes help encapsulate request and response data, promoting clean and organized code.

```
---

This documentation provides an overview of the `AuthRequest` and `AuthResponse` classes, their purpose, and field-level details. After creating these DTOs and adding the documentation, commit and push the updated `README.md` file to GitHub. Let me know if you’d like to proceed with implementing the service layer or any other part of the `auth-service`.
```

## Repository Layer

The repository layer is responsible for interacting with the database. In this project, we use Spring Data JPA to simplify CRUD operations on the `User` entity.

### UserRepository

The `UserRepository` interface provides database access for the `User` entity.

**File Location**: `src/main/java/com/example/auth/repository/UserRepository.java`

#### Purpose

The `UserRepository` allows us to perform CRUD operations on `User` objects and provides a custom query to find users by their username.

#### Methods

- **`findByUsername(String username)`**: Fetches a user based on their username.

### Testing the Repository Layer

A test class `UserRepositoryTest` is provided to verify the functionality of the `UserRepository`. This uses the H2 in-memory database, making it lightweight and ideal for testing.

#### Example Test Scenarios

- **shouldFindUserByUsername**: Verifies that the `findByUsername` method retrieves a user when the username exists.
- **shouldReturnEmptyIfUserNotFound**: Verifies that an empty result is returned if the username doesn’t exist in the database.

These tests ensure that the repository interacts correctly with the database.
