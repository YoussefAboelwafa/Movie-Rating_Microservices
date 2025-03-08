package com.example.trendingmoviesservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDTO {
    private final int id;
    private final String title;
    private final double rating;

    @JsonCreator
    public MovieDTO(
            @JsonProperty("id") int id,
            @JsonProperty("title") String title,
            @JsonProperty("rating") double rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    public MovieDTO() {
        this.id = 0;
        this.title = "";
        this.rating = 0.0;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getRating() { return rating; }
}
