🎬 Movie App Backend

A simple Spring Boot backend for managing movies, including CRUD operations and genre-based filtering. Built with Java 21, Spring Boot 4.0.0, PostgreSQL, and JPA/Hibernate.

📝 Description

This project provides a RESTful API for a Movie application.
Users can:

List all movies

Search movies by title

Filter movies by genre

Add, update, and delete movies

Genres are defined using an Enum (ACTION, COMEDY, DRAMA, HORROR, etc.).

⚙️ Technology Stack

Java 21

Spring Boot 4.0.0

PostgreSQL 15

JPA/Hibernate

Lombok

Maven

🚀 Getting Started
Prerequisites

Java 21

PostgreSQL installed and running

Maven installed

Installation

Clone the repository:

git clone https://github.com/<your-username>/movie-app-backend.git
cd movie-app-backend

Configure PostgreSQL in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/movie_db
spring.datasource.username=postgres
spring.datasource.password=your_password

Build and run the application:

./mvnw spring-boot:run

The backend will run on http://localhost:8080
.

🛠️ API Endpoints
Method	URL	Description
GET	/api/movies	Get all movies
GET	/api/movies/{id}	Get movie by ID
GET	/api/movies/genre?genre=ACTION	Get movies by genre
GET	/api/movies/search?title=Inception	Search movies by title
POST	/api/movies	Add a new movie
PUT	/api/movies/{id}	Update an existing movie
DELETE	/api/movies/{id}	Delete a movie
Example JSON for Adding a Movie
{
  "title": "Avengers: Endgame",
  "genre": "ACTION"
}
📂 Project Structure
movie-app-backend/
│
├─ src/main/java/com/example/movieapp/
│   ├─ controller/   # REST controllers
│   ├─ service/      # Business logic
│   ├─ repository/   # JPA repositories
│   ├─ model/        # Entities and Enums
│   └─ MovieAppApplication.java
│
├─ src/main/resources/
│   └─ application.properties
│
├─ pom.xml
└─ README.md
⚡ Notes

The project uses HikariCP for database connection pooling.

Enum-based genres allow easy filtering of movies.

Spring Security can be added later for authentication if needed.
