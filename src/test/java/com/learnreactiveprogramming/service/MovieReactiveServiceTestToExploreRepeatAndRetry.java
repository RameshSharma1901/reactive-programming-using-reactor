package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.exception.ReviewException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieReactiveServiceTestToExploreRepeatAndRetry {
    @Mock
    private MovieInfoService movieInfoService;
    @Mock
    private ReviewServiceInMemoryImpl reviewServiceInMemoryImpl;
    @InjectMocks
    private MovieReactiveService movieReactiveService;

    @Test
    public void explore_retryOnException() {
        //given
        String errMsg = "Something Wrong Happened";
        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();
        Mockito.when(reviewServiceInMemoryImpl.retrieveReviewsFlux(ArgumentMatchers.anyLong()))
                .thenThrow(ReviewException.class);
        //when
        Flux<Movie> movieFlux = movieReactiveService.getAllMoviesWithRetry();
        //then
        StepVerifier.create(movieFlux)
                .expectError(RuntimeException.class)
                .verify();

        Mockito.verify(reviewServiceInMemoryImpl, Mockito.times(2))
                .retrieveReviewsFlux(ArgumentMatchers.anyLong());
    }

    @Test
    public void explore_repeat() {
        //given
        String errMsg = "Something Wrong Happened";
        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();
        Mockito.when(reviewServiceInMemoryImpl.retrieveReviewsFlux(ArgumentMatchers.anyLong()))
                .thenCallRealMethod();
        //when
        Flux<Movie> movieFlux = movieReactiveService.getAllMoviesWithRepeat();
        //then
        StepVerifier.create(movieFlux)
                .expectNextCount(9)
                .verifyComplete();

        Mockito.verify(reviewServiceInMemoryImpl, Mockito.times(9))
                .retrieveReviewsFlux(ArgumentMatchers.anyLong());
    }
}