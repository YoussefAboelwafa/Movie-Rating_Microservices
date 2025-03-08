package com.moviecatalogservice.services;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import com.example.trendingmovies.grpc.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrendingMoviesClient {

    @GrpcClient("trending-movies")  // Match this to the configured name in application.properties
    private TrendingMoviesServiceGrpc.TrendingMoviesServiceBlockingStub stub;

    public List<Movie> getTrendingMovies(int limit) {
        try {
            // Build request
            TrendingMoviesRequest request = TrendingMoviesRequest.newBuilder()
                    .setLimit(limit)
                    .build();

            // Call gRPC server and collect response
            TrendingMoviesResponse response = stub.getTrendingMovies(request);

            return response.getMoviesList();
        } catch (Exception e) {
            // Handle gRPC errors gracefully
            System.err.println("Error calling gRPC server: " + e.getMessage());
            return List.of(); // Empty list as fallback
        }
    }
}