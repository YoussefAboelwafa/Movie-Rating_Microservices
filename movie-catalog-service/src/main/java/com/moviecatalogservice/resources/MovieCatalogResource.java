package com.moviecatalogservice.resources;

import com.example.trendingmovies.grpc.Movie;
import com.moviecatalogservice.models.MovieDTO;
import com.moviecatalogservice.models.Rating;
import com.moviecatalogservice.services.MovieInfoService;
import com.moviecatalogservice.services.TrendingMoviesClient;
import com.moviecatalogservice.services.UserRatingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    private final MovieInfoService movieInfoService;

    private final UserRatingService userRatingService;

    private final TrendingMoviesClient trendingMoviesClient;
    public MovieCatalogResource(RestTemplate restTemplate,
                                MovieInfoService movieInfoService,
                                UserRatingService userRatingService,
                                TrendingMoviesClient trendingMoviesClient) {

        this.restTemplate = restTemplate;
        this.movieInfoService = movieInfoService;
        this.userRatingService = userRatingService;
        this.trendingMoviesClient = trendingMoviesClient;
    }

    /**
     * Makes a call to MovieInfoService to get movieId, name and description,
     * Makes a call to RatingsService to get ratings
     * Accumulates both data to create a MovieCatalog
     * @param userId
     * @return CatalogItem that contains name, description and rating
     */
    @RequestMapping("/{userId}")
    public List<Rating> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = userRatingService.getUserRating(userId).getRatings();
        return ratings;
    }
    @RequestMapping("/topTen")
    public List<MovieDTO> getTopTen() {
        return trendingMoviesClient.getTrendingMovies(10)
                .stream()
                .map(movie -> new MovieDTO(movie.getId(), movie.getTitle(), movie.getRating()))
                .collect(Collectors.toList());
    }
}
