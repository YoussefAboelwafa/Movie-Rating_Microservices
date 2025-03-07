package com.example.movieinfoservice.cache;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CacheMovieRepository extends MongoRepository<CacheMovie, String> {
    CacheMovie findByMovieId(String movieId);
}