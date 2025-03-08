package com.example.ratingsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.ratingsservice.repositories.RatingsRepository;
import com.example.ratingsservice.models.Rating;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingsRepository ratingsRepository;

    public ResponseEntity<?> getRatingsByUserId(String userId) {
        List<Object[]> ratings = ratingsRepository.findRatingsAndMovieIdsByUserId(userId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    public ResponseEntity<?> getRatingsByMovieId(String movieId) {
        List<Object[]> ratings = ratingsRepository.findRatingsAndUserIdsByMovieId(movieId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
