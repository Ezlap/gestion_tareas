# Task Management REST API

REST API built with Java and Spring Boot for managing personal 
tasks with JWT authentication.

## Tech Stack
- Java 25
- Spring Boot 4
- MySQL
- Spring Security + JWT
- BCrypt password encryption
- Maven

## ▶Running the project locally

### Requirements
- Java 17+
- MySQL running on localhost
- Maven

### Steps

1. Clone the repository
   git clone https://github.com/tu-usuario/gestion-tareas.git

2. Create the database in MySQL
   CREATE DATABASE gestion_tareas;

3. Create the file src/main/resources/application-local.properties
   with your credentials:

   DB_URL=jdbc:mysql://localhost:3306/gestion_tareas
   DB_USER=your_username
   DB_PASSWORD=your_password
   jwt.secret=your_key

4. Run the project
   ./mvnw spring-boot:run

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register a new user | nombre, email, password |
| POST | /auth/login | Login and get JWT token | email, password |

### Tasks (JWT required) — coming soon
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/v1/tasks | List my tasks |
| POST | /api/v1/tasks | Create a task |
| PUT | /api/v1/tasks/{id} | Update a task |
| DELETE | /api/v1/tasks/{id} | Delete a task |

## Authentication
All task endpoints require a JWT token in the header:
Authorization: Bearer <your_token>

## Author
Elkin Esteban Salazar Pérez
[GitHub](https://github.com/ezlap)