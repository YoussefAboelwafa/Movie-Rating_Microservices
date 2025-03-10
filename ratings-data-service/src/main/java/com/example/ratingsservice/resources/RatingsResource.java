package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.RatingDTO;
import com.example.ratingsservice.models.UserRating;
import com.example.ratingsservice.models.UserRatingDTO;
import com.example.ratingsservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.ratingsservice.repositories.RatingsRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @Autowired
    RatingsRepository ratingsRepository;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserRatingDTO getRatingsOfUser(@PathVariable String userId) {
        List<Rating> ratings = ratingsRepository.findAll();
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for (Rating rating : ratings) {
            if (rating.getUser().getUserId().equals(userId)) {
                ratingDTOS.add(new RatingDTO(rating.getMovieId(), rating.getRating()));
            }
        }
        return new UserRatingDTO(userId, ratingDTOS);
    }
}
