package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.MovieInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieInfoService {
    Flux<MovieInfo> retrieveMoviesFlux();

    Mono<MovieInfo> retrieveMovieInfoMonoUsingId(long movieId);
}
