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

## Running the project locally

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

- DB_URL=jdbc:mysql://localhost:3306/gestion_tareas
- DB_USER=your_username
- DB_PASSWORD=your_password
- jwt.secret=your_key

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
| GET |  /task/list | List my tasks |
| POST | /task/create | Create a task | titulo, descripcion, estado, fechaLimite |
| PUT | /task/update/{id} | Update a task | titulo, descripcion, estado, fechaLimite |
| DELETE | /task/delete/{id} | Delete a task |

## How to authenticate

1. Register or login to get your JWT token
2. Copy the token from the response
3. In Thunder Client go to Auth → Bearer and paste the token
4. All task requests will be authenticated automatically

### Example request body for register:
{
    "nombre": "Your name",
    "email": "your@email.com",
    "password": "yourpassword"
}

### Example request body for create task:
{
    "titulo": "My first task",
    "descripcion": "Task description",
    "estado": "PENDIENTE",
    "fechaLimite": "2026-12-31"
}

### Valid status values:
- PENDIENTE
- EN_PROGRESO
- COMPLETADA

## 📁 Project Structure
src/main/java/com/example/demo/
├── controller/     → HTTP endpoints
├── service/        → Business logic
├── repository/     → Database queries
├── model/          → Database entities
├── dto/            → Request and response objects
└── security/       → JWT and Spring Security config

## Author
Elkin Esteban Salazar Pérez
- [LinkedIn](https://www.linkedin.com/in/elkin-esteban-salazar-perez-904924366/)
- [GitHub](https://github.com/ezlap)