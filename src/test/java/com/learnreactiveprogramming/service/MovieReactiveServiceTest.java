package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieReactiveServiceTest {

    MovieInfoService movieInfoService = new MovieInfoService();
    ReviewService reviewService = new ReviewService();
    MovieReactiveService movieReactiveService = new MovieReactiveService(movieInfoService, reviewService);
    @Test
    void getAllMovies() {

        StepVerifier.create(movieReactiveService.getAllMovies())
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                })
                .assertNext(movie -> {
                    assertEquals("The Dark Knight", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                })
                .assertNext(movie -> {
                    assertEquals("Dark Knight Rises", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                })
                .verifyComplete();
    }

    @Test
    void getMovieById() {

        StepVerifier.create(movieReactiveService.getMovieById(102))
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                }).verifyComplete();
    }
}