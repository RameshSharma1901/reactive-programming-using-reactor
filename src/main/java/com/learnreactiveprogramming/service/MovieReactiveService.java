package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.domain.MovieInfo;
import com.learnreactiveprogramming.domain.Revenue;
import com.learnreactiveprogramming.domain.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class MovieReactiveService {

    private MovieInfoService movieInfoService;
    private ReviewService reviewService;

    private RevenueService revenueService;

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService, RevenueService revenueService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
        this.revenueService = revenueService;
    }

    public Flux<Movie> getAllMovies(){
        return movieInfoService.retrieveMoviesFlux()
                .flatMap(movieInfo -> {
                    var monoReviewList = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
                    return monoReviewList.map(reviews -> new Movie(movieInfo, reviews));
        });
    }

    public Flux<Movie> getAllMoviesWithRetry(){
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

    public Mono<Movie> getMovieByIdV1(long movieId){
        return movieInfoService.retrieveMovieInfoMonoUsingId(movieId)
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
                    return reviewsMono.map(reviews -> new Movie(movieInfo, reviews));
                });
    }

    public Mono<Movie> getMovieByIdV2(long movieId){
        Mono<MovieInfo> movieInfoMono =
                movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        Flux<Review> reviewFlux = reviewService.retrieveReviewsFlux(movieId);
        Mono<List<Review>> listReviewMono = reviewFlux.collectList();
        return movieInfoMono.zipWith(listReviewMono, Movie::new);
    }

    public Mono<Movie> getMovieWithRevenueById(long movieId){
        Mono<MovieInfo> movieInfoMono =
                movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        Flux<Review> reviewFlux = reviewService.retrieveReviewsFlux(movieId);
        Mono<List<Review>> listReviewMono = reviewFlux.collectList();
        Mono<Revenue> revenueMono = Mono.fromCallable(()-> revenueService.getRevenue(movieId))
                .subscribeOn(Schedulers.boundedElastic());
        return movieInfoMono.zipWith(listReviewMono, Movie::new).zipWith(revenueMono, (movie, revenue)->{
             movie.setRevenue(revenue);
             return movie;
        }).log();
    }
}
