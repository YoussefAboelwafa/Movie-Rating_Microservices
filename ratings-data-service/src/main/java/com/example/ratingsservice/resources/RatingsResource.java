package com.example.ratingsservice.resources;

import com.example.ratingsservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value = "byUser/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getRatingsOfUser(@PathVariable String userId) {
        ResponseEntity<?> responseEntity = this.ratingService.getRatingsByUserId(userId);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(value = "byMovie/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<?> getRatingsOfMovie(@PathVariable String movieId) {
        ResponseEntity<?> responseEntity = this.ratingService.getRatingsByMovieId(movieId);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }
}
