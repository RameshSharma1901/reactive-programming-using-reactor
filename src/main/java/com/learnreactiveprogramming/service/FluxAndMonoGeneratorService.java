package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("Ramesh", "Parth", "Pritesh"));
    }

    public Mono<String> namesMono(){
        return Mono.just("Pranav");
    }

}
