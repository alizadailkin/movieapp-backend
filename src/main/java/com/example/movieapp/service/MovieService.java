package com.example.movieapp.service;


import com.example.movieapp.dto.dtorequest.MovieRequest;
import com.example.movieapp.dto.dtoresponse.MovieResponse;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    // butun
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ID
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return mapToResponse(movie);
    }

    // Genre'ye
    public List<MovieResponse> getMoviesByGenre(Genre genre) {
        return movieRepository.findByGenre(genre).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // title
    public List<MovieResponse> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Yeni
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = new Movie(request.getTitle(), request.getGenre());
        Movie savedMovie = movieRepository.save(movie);
        return mapToResponse(savedMovie);
    }

    //  güncelle
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movie.setTitle(request.getTitle());
        movie.setGenre(request.getGenre());
        Movie updatedMovie = movieRepository.save(movie);
        return mapToResponse(updatedMovie);
    }

    // sil
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        movieRepository.delete(movie);
    }

    // Entity Response çevir
    private MovieResponse mapToResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre().name()
        );
    }
}