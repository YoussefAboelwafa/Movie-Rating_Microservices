# spring-boot-microservices
In this project, multiple microservices communicate with each other to provide the backend services of a minimalistic/sample movie rating application similar to IMDB.

## Technologies

- __Spring Boot__
- Ratings Service Storage using __MySQL__
- Cache the MovieDB query results in __MongoDB__
- Trending Movies Service with __gRPC__ API

## Summary

*  __MovieInfoService__ provides the movie info by sending requests to __TheMovieDB API__. 
* __RatingsDataService__ provides the user's ratings for movies.
* __MovieCatalogService__ acts as an accumulator that gets data from __RatingsDataService__ and __MovieInfoService__ to present it.
* __DiscoveryServer__ is the Eureka server for service discovery.

![Screen Shot 2021-09-23 at 16 48 57](https://user-images.githubusercontent.com/22833948/134519062-0013cbf9-8a5f-4a43-ba14-635ccdbab04b.png)

## Running

You can run each project either using your IDE or *mvn spring-boot:run* starting from __DiscoveryServer__. Projects will run on the following endpoints:

* Discovery Server - http://localhost:8761
* Movie Catalog - http://localhost:8081/catalog/{userId}
* Movie Info - http://localhost:8082/movies/{movieId}
* Ratings Data - http://localhost:8083/ratings/{userId}
