package com.example.ratingsservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;
    @Column(name = "movie_description", nullable = false)
    private String description;
}
