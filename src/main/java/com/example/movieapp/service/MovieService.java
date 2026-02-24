package com.example.movieapp.service;


import com.example.movieapp.dto.dtorequest.MovieFilterDto;
import com.example.movieapp.dto.dtorequest.MovieRequest;
import com.example.movieapp.dto.dtoresponse.MovieResponse;
import com.example.movieapp.dto.dtoresponse.PageResponse;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieSpecification;
import com.example.movieapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    public PageResponse<MovieResponse> getAllMovies(MovieFilterDto filterDto, Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(MovieSpecification.filterMovies(filterDto), pageable);

        // Page -> PagedResponse dönüşümü
        PageResponse<MovieResponse> response = new PageResponse<>();
        response.setPage(movies.getNumber());
        response.setSize(movies.getSize());
        response.setTotalPagesCount(movies.getTotalPages());
        response.setTotalElementsCount(movies.getTotalElements());
        response.setItems(movies.map(this::mapToResponse).getContent());

        return response;
    }



    // ID
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return mapToResponse(movie);
    }

    // Genreler
    public List<String> getAllGenres() {
        return Arrays.stream(Genre.values())
                .map(Enum::name)
                .toList();
    }

    // title
    public List<MovieResponse> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Yeni
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = new Movie(
                request.getTitle(),
                request.getGenre(),
                request.getYear(),
                request.getRating()
        );
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
                , movie.getYear(),
                movie.getRating()
        );
    }
}