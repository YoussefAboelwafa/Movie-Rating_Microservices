package com.example.ratingsservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "movie_id", nullable = false, unique = true, updatable = false)
    private String movieId;
    @Column(name = "movie_name", nullable = false)
    private String movieName;
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "movie_description", nullable = false)
    private String movieDescription;
    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> rating;
}
