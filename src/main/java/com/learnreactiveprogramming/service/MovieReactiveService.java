package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieReactiveService {
    Flux<Movie> getAllMovies();
    Mono<Movie> getMovieById(long movieId);

}
