package com.example.movieinfoservice.models;

public class MovieDTO {
    private final int id;
    private final String title;
    private final double rating;

    public MovieDTO(int id, String title, double rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getRating() { return rating; }
}

