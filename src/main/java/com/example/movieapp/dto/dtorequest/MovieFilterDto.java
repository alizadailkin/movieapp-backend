package com.example.movieapp.dto.dtorequest;

import lombok.Data;

@Data
public class MovieFilterDto {

    private String title;
    private String genre;
    private Integer year;
    private Double minRating;
    private Double maxRating;
}
