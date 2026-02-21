package com.example.movieapp.service;



import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    // butun filmler
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    //id gore filmler
    public Movie getMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }
    //janra gore filmer
    public List<Movie> getMoviesByGenre(Genre genre){
        return movieRepository.findByGenre(genre);
    }

    //basliga gore filmeri getirir
    public List<Movie> getMoviesByTitle(String title){
        return movieRepository.findByTitleContainingIgnoreCase(title);

    }

    //sav elemek ucun
    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    // silmek ucun
    public void deleteMovie(Long id){
        if (!movieRepository.existsById(id)){
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);

    }

    //yenilemek ucun
    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = getMovieById(id);
        movie.setTitle(movieDetails.getTitle());
        movie.setGenre(movieDetails.getGenre());
        return movieRepository.save(movie);
    }
}
