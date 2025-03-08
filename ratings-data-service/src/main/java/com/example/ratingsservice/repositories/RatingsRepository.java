package com.example.ratingsservice.repositories;

import com.example.ratingsservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r.movieId, r.rating FROM Rating r WHERE r.user.userId = :userId")
    List<Object[]> findRatingsAndMovieIdsByUserId(@Param("userId") String userId);

    @Query("SELECT r.user.userId, r.rating FROM Rating r WHERE r.movieId.movieId = :movieId")
    List<Object[]> findRatingsAndUserIdsByMovieId(@Param("movieId") String movieId);

}
