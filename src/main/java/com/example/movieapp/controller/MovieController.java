package com.example.movieapp.controller;

import com.example.movieapp.dto.dtorequest.MovieRequest;
import com.example.movieapp.dto.dtoresponse.MovieResponse;
import com.example.movieapp.model.Genre;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // butun
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // 🔹 ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        MovieResponse movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    // 🔹 Genre
    @GetMapping("/genre")
    public ResponseEntity<List<MovieResponse>> getMoviesByGenre(@RequestParam Genre genre) {
        List<MovieResponse> movies = movieService.getMoviesByGenre(genre);
        return ResponseEntity.ok(movies);
    }

    // ada gore
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> searchMovies(@RequestParam String title) {
        List<MovieResponse> movies = movieService.searchMovies(title);
        return ResponseEntity.ok(movies);
    }

    // yeni
    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest request) {
        MovieResponse createdMovie = movieService.createMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    // guncelleme
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody MovieRequest request) {
        MovieResponse updatedMovie = movieService.updateMovie(id, request);
        return ResponseEntity.ok(updatedMovie);
    }

    // sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}