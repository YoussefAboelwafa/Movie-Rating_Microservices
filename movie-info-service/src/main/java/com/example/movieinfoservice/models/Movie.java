package com.example.movieinfoservice.models;

public class Movie {

    private String movieId;
    private String name;
    private String description;

    public Movie() {
    }

    public Movie(String movieId, String name, String description) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }
}
