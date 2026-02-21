package com.example.movieapp.repository;

import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie>findByGenre(Genre genre);
    List<Movie>findByTitleContainingIgnoreCase(String title);
    List<Movie>findByTitle(String title);
}
