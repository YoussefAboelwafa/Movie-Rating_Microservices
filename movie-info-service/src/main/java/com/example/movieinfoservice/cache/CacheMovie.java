package com.example.movieinfoservice.cache;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.movieinfoservice.models.Movie;

@Document(collection = "movie-cache")
public class CacheMovie {
    @Id
    private String movieId;
    private String name;
    private String description;

    public CacheMovie(Movie movie) {
        this.movieId = movie.getMovieId();
        this.name = movie.getName();
        this.description = movie.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}