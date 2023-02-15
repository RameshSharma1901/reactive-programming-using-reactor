package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

class ReviewServiceRestClientImplTest {

    @Test
    void retrieveReviewsFlux_RestClient() {
        //given
        var movieInfoId = 2L;
        var webClient = WebClient.builder().baseUrl("http://localhost:8080/movies").build();
        var reviewService = new ReviewServiceRestClientImpl(webClient);
        //when
        var reviewFlux = reviewService.retrieveReviewsFlux(movieInfoId);
        //then
        StepVerifier.create(reviewFlux)
                .expectNextMatches(r -> r.getMovieInfoId().equals(movieInfoId))
                .verifyComplete();
    }
}