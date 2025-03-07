package com.example.ratingsservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", nullable = false, unique = true, updatable = false)
    private Long ratingId;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movieId;
    @Column(name = "rating", nullable = false)
    private int rating;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserRating user;
}
