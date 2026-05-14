# Task Management REST API

REST API built with Java and Spring Boot for managing personal 
tasks with JWT authentication.

## Tech Stack
- Java 25
- Spring Boot 4
- MySQL
- Spring Security + JWT
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

4. Run the project
   ./mvnw spring-boot:run

## Main Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/v1/auth/register | Register a new user |
| POST | /api/v1/auth/login | Login |

### Tasks (JWT token required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/v1/tasks | List my tasks |
| POST | /api/v1/tasks | Create a task |
| PUT | /api/v1/tasks/{id} | Update a task |
| DELETE | /api/v1/tasks/{id} | Delete a task |

## Author
Elkin Esteban Salazar Pérez
[GitHub](https://github.com/ezlap)