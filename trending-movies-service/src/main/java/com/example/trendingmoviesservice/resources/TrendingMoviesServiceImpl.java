package com.example.trendingmoviesservice.resources;

import com.example.trendingmovies.grpc.TrendingMoviesServiceGrpc;
import com.example.trendingmovies.grpc.TrendingMoviesRequest;
import com.example.trendingmovies.grpc.TrendingMoviesResponse;
import com.example.trendingmovies.grpc.Movie;
import com.example.trendingmoviesservice.models.MovieDTO;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class TrendingMoviesServiceImpl extends TrendingMoviesServiceGrpc.TrendingMoviesServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(TrendingMoviesServiceImpl.class);
    private final RestTemplate restTemplate;

    public TrendingMoviesServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void getTrendingMovies(TrendingMoviesRequest request, StreamObserver<TrendingMoviesResponse> responseObserver) {
        try {
            String movieDetailsUrl = "http://localhost:8082/movies/trendy";
            logger.info("Fetching trending movies from: {}", movieDetailsUrl);

            MovieDTO[] movies = restTemplate.getForObject(movieDetailsUrl, MovieDTO[].class);

            if (movies == null) {
                logger.error("movie-info-service returned NULL!");
                responseObserver.onError(new RuntimeException("movie-info-service returned NULL!"));
                return;
            }

            logger.info("Received {} movies from movie-info-service.", movies.length);

            List<Movie> grpcMovies = Arrays.stream(movies)
                    .map(movie -> Movie.newBuilder()
                            .setId(movie.getId())
                            .setTitle(movie.getTitle())
                            .setRating(movie.getRating())
                            .build())
                    .sorted((m1, m2) -> Double.compare(m2.getRating(), m1.getRating())) // Sort by rating DESC
                    .collect(Collectors.toList());

            TrendingMoviesResponse response = TrendingMoviesResponse.newBuilder()
                    .addAllMovies(grpcMovies.subList(0, Math.min(request.getLimit(), grpcMovies.size())))
                    .build();

            logger.info("Sending gRPC response with {} movies.", response.getMoviesCount());

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            logger.error("Error processing gRPC request: {}", e.getMessage(), e);
            responseObserver.onError(e);
        }
    }
}
