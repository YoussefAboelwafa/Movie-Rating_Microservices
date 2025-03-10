package com.example.ratingsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRatingDTO {
    private String userId;
    private List<RatingDTO> ratings;

}
