package com.example.movieapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="movies")
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name ="year")
    private Integer year;
    @Column(name ="rating")
    private Double Rating;

        public Movie(String title, Genre genre, Integer year, Double rating) {;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.Rating = rating;


   }

}
