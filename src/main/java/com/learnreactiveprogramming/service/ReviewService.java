package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Review;
import reactor.core.publisher.Flux;

public interface ReviewService {
     Flux<Review> retrieveReviewsFlux(long movieInfoId);
}
