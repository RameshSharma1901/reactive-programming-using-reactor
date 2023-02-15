package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Review;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class ReviewServiceRestClientImpl implements ReviewService {
    private WebClient webClient;

    @Override
    public Flux<Review> retrieveReviewsFlux(long movieInfoId) {
        var uri = UriComponentsBuilder.fromUriString("/v1/reviews")
                .queryParam("movieInfoId",movieInfoId)
                .buildAndExpand()
                .toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve().bodyToFlux(Review.class);
    }

}
