package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("Ramesh", "Parth", "Pritesh")).log();
    }

    public Mono<String> namesMono(){
        return Mono.just("Pranav");
    }

    public Flux<String> namesFluxMap(){
        return Flux.fromIterable(List.of("Ramesh", "Parth", "Pritesh"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> namesFluxFilter(int length){
        return Flux.fromIterable(List.of("Ramesh", "Parth", "Pritesh"))
                .filter(s -> s.length() > length)
                .map(String::toUpperCase)
                .log();
    }
}
