🎬 Movie App Backend

A simple Spring Boot REST API for managing movies with CRUD operations and genre-based filtering.
Built with Java 21, Spring Boot 4, PostgreSQL, and JPA/Hibernate.

📝 Description

This project provides a RESTful backend for a Movie application.

Users can:

List all movies

Get movie by ID

Search movies by title

List all genres

Add new movies

Update existing movies

Delete movies

Genres are defined using an Enum:

ACTION
COMEDY
DRAMA
HORROR
SCI_FI
FANTASY
ROMANCE

🔐 Password Security

User passwords are stored securely in the database using BCrypt hashing.

Raw passwords are never stored. Only the hashed version (e.g., $2a$10$Mm/...) is saved.

This ensures sensitive data is safe and prevents unauthorized access.

⚙️ Technology Stack

Java 21

Spring Boot 4

Spring Data JPA

PostgreSQL

Hibernate

Lombok

Maven

Swagger / OpenAPI

🚀 Getting Started
Prerequisites

You need to install:

Java 21

PostgreSQL

Maven

Installation

Clone the repository:

git clone https://github.com/alizadailkin/movieapp-backend.git
cd movieapp-backend

Configure PostgreSQL in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/movie_db
spring.datasource.username=postgres
spring.datasource.password=1
spring.jpa.hibernate.ddl-auto=update

Build and run the application:

mvn spring-boot:run

Backend will run on:
http://localhost:8080

📘 API Documentation (Swagger)

After running the project, open:
http://localhost:8080/swagger-ui/index.html

You can test all endpoints directly from Swagger UI.

🛠️ API Endpoints
Method	URL	Description
GET	/api/movies	Get all movies
GET	/api/movies/{id}	Get movie by ID
GET	/api/movies/genre/{genre}	Get movies by genre
GET	/api/movies/search?title=Inception	Search movies by title
POST	/api/movies	Add a new movie
PUT	/api/movies/{id}	Update a movie
DELETE	/api/movies/{id}	Delete a movie

Example JSON (Create Movie):

{
  "title": "Avengers: Endgame",
  "genre": "ACTION"
}
📂 Project Structure

movieapp-backend
│
├── src/main/java/com/example/movieapp
│ ├── controller
│ ├── service
│ ├── repository
│ ├── model
│ ├── dto
│ ├── exception
│ └── config
│
├── src/main/resources
│ └── application.properties
│
├── pom.xml
└── README.md

✨ Features

Clean layered architecture

DTO Request / Response structure

Global Exception Handling

Enum-based movie genres

Swagger API documentation

PostgreSQL integration

BCrypt password hashing for users

👨‍💻 Author

Ilkin Alizada
Java Backend Developer
