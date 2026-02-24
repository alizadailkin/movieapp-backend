package com.example.movieapp.model;

import com.example.movieapp.dto.dtorequest.MovieFilterDto;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {
    public static Specification<Movie> filterMovies(MovieFilterDto filter) {
        return Specification.where(hasTitle(filter.getTitle()))
                .and(hasGenre(filter.getGenre()))
                .and(hasYear(filter.getYear()))
                .and(hasMinRating(filter.getMinRating()))
                .and(hasMaxRating(filter.getMaxRating()));
    }

    public static Specification<Movie> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                (title == null || title.isEmpty())
                        ? null
                        : criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Movie> hasGenre(String genreStr) {
        return (root, query, criteriaBuilder) -> {
            if (genreStr == null || genreStr.isEmpty()) return null;
            try {
                Genre genre = Genre.valueOf(genreStr.toUpperCase());
                return criteriaBuilder.equal(root.get("genre"), genre);
            } catch (IllegalArgumentException e) {
                return null; // Geçersiz enum gönderilirse filtre uygulanmaz
            }
        };
    }

    public static Specification<Movie> hasYear(Integer year) {
        return (root, query, criteriaBuilder) ->
                (year == null)
                        ? null
                        : criteriaBuilder.equal(root.get("year"), year);
    }

    public static Specification<Movie> hasMinRating(Double minRating) {
        return (root, query, criteriaBuilder) ->
                (minRating == null)
                        ? null
                        : criteriaBuilder.greaterThanOrEqualTo(root.get("minRating"), minRating);
    }

    public static Specification<Movie> hasMaxRating(Double maxRating) {
        return (root, query, criteriaBuilder) ->
                (maxRating == null)
                        ? null
                        : criteriaBuilder.lessThanOrEqualTo(root.get("maxRating"), maxRating);
    }

}
