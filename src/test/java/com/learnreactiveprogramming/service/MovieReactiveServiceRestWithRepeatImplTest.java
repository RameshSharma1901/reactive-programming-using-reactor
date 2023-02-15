package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.exception.ReviewException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@MockitoSettings(strictness = Strictness.LENIENT)
class MovieReactiveServiceRestWithRepeatImplTest {

    @Mock
    private MovieInfoServiceInMemoryImpl movieInfoService;
    @Mock
    private ReviewServiceInMemoryImpl reviewService;
    @InjectMocks
    private MovieReactiveServiceRestWithRepeatImpl movieReactiveService;

    @Test
    public void explore_repeat() {
        //given
        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();
        Mockito.when(reviewService.retrieveReviewsFlux(ArgumentMatchers.anyLong()))
                .thenCallRealMethod();
        //when
        Flux<Movie> movieFlux = movieReactiveService.getAllMovies();
        //then
        StepVerifier.create(movieFlux)
                .expectNextCount(9)
                .verifyComplete();

        Mockito.verify(reviewService, Mockito.times(9))
                .retrieveReviewsFlux(ArgumentMatchers.anyLong());
    }
}