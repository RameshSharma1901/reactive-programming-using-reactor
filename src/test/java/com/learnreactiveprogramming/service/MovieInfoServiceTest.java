package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.MovieInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class MovieInfoServiceTest {
    private static MovieInfoService movieInfoService;
    @BeforeAll
    static void setUp() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/movies")
                .build();
       movieInfoService = new MovieInfoServieRestClientImpl(webClient);
    }
    @Test
    public void retrieveMoviesFluxUsingWebClient() {
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesFlux();
        StepVerifier.create(movieInfoFlux)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    public void test_retrieveMovieInfoById_RestClient() {
        //given
        int movieId = 1;
        //when
        Mono<MovieInfo> movieInfoFlux = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        //then
        StepVerifier.create(movieInfoFlux)
                .expectNextMatches(m -> m.getName().equals("Batman Begins"))
                .verifyComplete();
    }
}