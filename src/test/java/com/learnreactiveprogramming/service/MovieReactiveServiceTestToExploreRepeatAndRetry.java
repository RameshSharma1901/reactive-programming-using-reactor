package com.learnreactiveprogramming.service;

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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieReactiveServiceUsingMockitoTest {
    @Mock
    private MovieInfoService movieInfoService;
    @Mock
    private ReviewService reviewService;
    @InjectMocks
    private MovieReactiveService movieReactiveService;
    @Test
    public void explore_retryOnException(){
        //given
        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();
        Mockito.when(reviewService.retrieveReviews(ArgumentMatchers.anyLong()))
                .thenThrow(ReviewException.class);
        //when
        movieReactiveService.getAllMovies();
        //then
        Mockito.verify(reviewService, Mockito.times(0))
                .retrieveReviews(ArgumentMatchers.anyLong());
    }
}