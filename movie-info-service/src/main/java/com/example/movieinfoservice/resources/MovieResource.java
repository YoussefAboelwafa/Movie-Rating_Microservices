package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.cache.CacheMovie;
import com.example.movieinfoservice.cache.CacheMovieRepository;
import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}") // Injects the API key from the application properties
    private String apiKey;

    private final RestTemplate restTemplate; // Used to make HTTP requests to external APIs

    private final CacheMovieRepository cacheMovieRepository; // Injects the API key from the application properties

    // Constructor to initialize restTemplate and cacheMovieRepository using constructor injection
    public MovieResource(RestTemplate restTemplate, CacheMovieRepository cacheMovieRepository) {
        this.restTemplate = restTemplate;
        this.cacheMovieRepository = cacheMovieRepository;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        try {
            // Check if movie is present in cache
            CacheMovie cacheMovie = cacheMovieRepository.findByMovieId(movieId);

            // if movie is not present in cache, fetch it from the API and save it in the cache
            if (cacheMovie == null) {
                System.out.println("Movie " + movieId + " not found in cache 🔴");
                final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
                MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);

                assert movieSummary != null;
                // Create a new Movie object with the fetched data
                Movie movie = new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
                // Save the movie in the cache
                CacheMovie newCacheMovie = new CacheMovie(movie);
                cacheMovieRepository.save(newCacheMovie);
                return movie;

            } else {
                System.out.println("Movie [" + movieId + "] found in cache 🟢");
                return new Movie(movieId, cacheMovie.getName(), cacheMovie.getDescription());
            }
        } catch (HttpClientErrorException e) {
            System.out.println("\u001B[31mError: " + e.getStatusCode() + ": \u001B[0m" + e.getResponseBodyAsString());
            return null;
        }
    }
}