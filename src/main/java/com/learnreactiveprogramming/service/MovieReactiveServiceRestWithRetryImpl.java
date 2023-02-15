package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.domain.MovieInfo;
import com.learnreactiveprogramming.domain.Revenue;
import com.learnreactiveprogramming.domain.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class MovieReactiveServiceRestWithRetryImpl implements MovieReactiveService {

    private MovieInfoService movieInfoService;
    private ReviewService reviewService;

    public MovieReactiveServiceRestWithRetryImpl(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }

    @Override
    public Flux<Movie> getAllMovies(){
        return movieInfoService.retrieveMoviesFlux()
                .flatMap(movieInfo -> {
                    var monoReviewList = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
                    return monoReviewList.map(reviews -> new Movie(movieInfo, reviews));
                }).retry(1).log();
    }

    public Flux<Movie> getAllMoviesWithRepeat(){
        return movieInfoService.retrieveMoviesFlux()
                .flatMap(movieInfo -> {
                    var monoReviewList = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
                    return monoReviewList.map(reviews -> new Movie(movieInfo, reviews));
                }).repeat(2).log();
    }

    @Override
    public Mono<Movie> getMovieById(long movieId){
        return movieInfoService.retrieveMovieInfoMonoUsingId(movieId)
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
                    return reviewsMono.map(reviews -> new Movie(movieInfo, reviews));
                });
    }
}
