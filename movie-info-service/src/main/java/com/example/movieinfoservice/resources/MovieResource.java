package com.example.movieinfoservice.resources;







import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieDTO;
import com.example.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        // Get the movie info from TMDB
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }





































    private RestTemplate restTemplate;
    private final Random random = new Random();
    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @RequestMapping("/trendy")
    public List<MovieDTO> getTrendingMovies() {
        final String url = "https://api.themoviedb.org/3/trending/movie/day?api_key=" + apiKey;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

        return results.stream()
                .flatMap(movie -> {
                    int id = (int) movie.get("id");
                    String title = (String) movie.get("title");

                    // Generate 3 duplicates per movie with random ratings
                    return IntStream.range(0, 3)
                            .mapToObj(i -> new MovieDTO(
                                    id + (int)(random.nextDouble()*40),
                                    title,
                                    (random.nextDouble() * 10.0)
                            ));
                })
                .collect(Collectors.toList());
    }

}
