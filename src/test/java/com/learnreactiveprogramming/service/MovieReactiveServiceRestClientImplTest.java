package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieReactiveServiceRestClientImplTest {

    private final WebClient webClient
            = WebClient.builder().baseUrl("http://localhost:8080/movies").build();
    private final MovieInfoService movieInfoService = new MovieInfoServieRestClientImpl(webClient);
    private final ReviewService reviewService = new ReviewServiceRestClientImpl(webClient);
    private final RevenueService revenueService = new RevenueService();
    private final MovieReactiveService movieReactiveService = new MovieReactiveServiceImpl(movieInfoService, reviewService, revenueService);
    @Test
    void getAllMovies() {
        //when
        var movies = movieReactiveService.getAllMovies();
        //then
        StepVerifier.create(movies)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    void getMovieById() {
        //given
        var movieId = 2;
        //when
        var movie = movieReactiveService.getMovieById(movieId).log();
        //then
        StepVerifier.create(movie)
                .assertNext(m -> m.getMovie().getMovieInfoId().equals(movieId))
                .verifyComplete();
    }
}