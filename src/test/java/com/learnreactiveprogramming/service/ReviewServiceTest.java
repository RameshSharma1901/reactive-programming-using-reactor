package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {

    @Test
    void retrieveReviewsFlux_RestClient() {
        //given
        var movieInfoId = 2L;
        var webClient = WebClient.builder().baseUrl("http://localhost:8080/movies").build();
        var reviewService = new ReviewService(webClient);
        //when
        var reviewFlux = reviewService.retrieveReviewsFlux_RestClient(movieInfoId);
        //then
        StepVerifier.create(reviewFlux)
                .expectNextMatches(r -> r.getMovieInfoId().equals(movieInfoId))
                .verifyComplete();
    }
}