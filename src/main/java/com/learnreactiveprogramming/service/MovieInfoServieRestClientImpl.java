package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.MovieInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class MovieInfoServieRestClientImpl implements MovieInfoService{

    private WebClient webClient;

    @Override
    public Flux<MovieInfo> retrieveMoviesFlux() {
        return webClient.get()
                .uri("/v1/movie_infos")
                .retrieve()
                .bodyToFlux(MovieInfo.class);
    }

    @Override
    public Mono<MovieInfo> retrieveMovieInfoMonoUsingId(long movieId) {
        return webClient.get()
                .uri("/v1/movie_infos/{movieId}", movieId)
                .retrieve()
                .bodyToMono(MovieInfo.class).log();
    }
}
