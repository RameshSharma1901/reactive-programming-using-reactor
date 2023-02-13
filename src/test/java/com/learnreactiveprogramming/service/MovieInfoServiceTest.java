package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.MovieInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieInfoServiceTest {
    private static WebClient webClient;
    private static MovieInfoService movieInfoService;
    @BeforeAll
    static void setUp() {
       webClient = WebClient.builder()
               .baseUrl("http://localhost:8080/movies")
               .build();
       movieInfoService = new MovieInfoService(webClient);
    }
    @Test
    public void retrieveMoviesFluxUsingWebClient() {
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesFluxUsingWebClient();
        StepVerifier.create(movieInfoFlux)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    public void retrieveMoviesByIdUsingWebClient() {
        Mono<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesByIdUsingWebClient(2);
        StepVerifier.create(movieInfoFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
}