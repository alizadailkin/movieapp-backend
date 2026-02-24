package com.example.movieapp.controller;

import com.example.movieapp.dto.dtorequest.MovieFilterDto;
import com.example.movieapp.dto.dtorequest.MovieRequest;
import com.example.movieapp.dto.dtoresponse.MovieResponse;
import com.example.movieapp.dto.dtoresponse.PageResponse;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // butun filmler pagination/Specification formatinda
    @GetMapping
    public PageResponse<MovieResponse> getAllMovies(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Double Minrating,
            @RequestParam(required = false) Double Maxrating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        MovieFilterDto filterDto = new MovieFilterDto();
        filterDto.setTitle(title);
        filterDto.setYear(year);
        filterDto.setGenre(genre);
        filterDto.setMinRating(Minrating);
        filterDto.setMaxRating(Maxrating);
        return movieService.getAllMovies(filterDto, pageable);
    }

    // 🔹 ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        MovieResponse movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    // Genreler
    @GetMapping("/genres")
    public ResponseEntity<List<String>> getAllGenres() {
        return ResponseEntity.ok(movieService.getAllGenres());
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