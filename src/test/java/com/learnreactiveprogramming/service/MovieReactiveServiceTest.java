package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieReactiveServiceTest {

    MovieInfoService movieInfoService = new MovieInfoServiceInMemoryImpl();
    ReviewServiceInMemoryImpl reviewServiceInMemoryImpl = new ReviewServiceInMemoryImpl();

    RevenueService revenueService = new RevenueService();
    MovieReactiveService movieReactiveService = new MovieReactiveService(movieInfoService, reviewServiceInMemoryImpl, revenueService);
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
    void getMovieById_usingFlatMap() {

        StepVerifier.create(movieReactiveService.getMovieByIdV1(102))
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                }).verifyComplete();
    }

    @Test
    void getMovieById_usingZipWith() {

        StepVerifier.create(movieReactiveService.getMovieByIdV2(102))
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                }).verifyComplete();
    }

    @Test
    void getMovieByIdWithRevenue_usingZipWith() {
        //given
        var movieId = 102;
        //when
        var movieMono = movieReactiveService.getMovieWithRevenueById(movieId);
        //then
        StepVerifier.create(movieMono)
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                    assertEquals(1000000,movie.getRevenue().getBudget());
                }).verifyComplete();
    }
}