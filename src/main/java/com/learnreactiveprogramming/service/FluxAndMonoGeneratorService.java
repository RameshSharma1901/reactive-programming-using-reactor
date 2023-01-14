package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoGeneratorService {
    Random random = new Random(10);

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

    public Mono<String> namesMonoMap(){
        return Mono.just("alex")
                .map(String::toUpperCase);
    }

    public Flux<String> namesFluxFlatMap(){
        return Flux.fromIterable(List.of("ramesh","sharma"))
                .flatMap(this::splitString);
    }

    public Flux<String> namesFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("ramesh","sharma"))
                .flatMap(this::splitString)
                .log();
    }

    /**
     * Difference between flatMap, concatMap and flatMapSequential
     * <a href="https://stackoverflow.com/questions/71971062/whats-the-difference-between-flatmap-flatmapsequential-and-concatmap-in-project">...</a>
     */
    public Flux<String> namesFluxConcatMap(){
        return Flux.fromIterable(List.of("ramesh","sharma"))
                .concatMap(this::splitString)
                .log();
    }

    public Flux<String> namesFluxFlatMapSequential(){
        return Flux.fromIterable(List.of("ramesh","sharma"))
                .flatMapSequential(this::splitString)
                .log();
    }

    public Flux<String> defaultIfEmptyExample(){
        return Flux.fromIterable(List.of("ramesh","sharma"))
                .filter(s -> s.length() > 6)
                .defaultIfEmpty("harshal")
                .log();
    }

    public Flux<String> switchIfEmptyExample(){
        return Flux.fromIterable(List.of("ramesh","sharma"))
                .filter(s -> s.length() > 6)
                .switchIfEmpty(Flux.just("harshal"))
                .log();
    }
    private Flux<String> splitString(String name){

        return Flux.fromArray(name.split("")).delayElements(Duration.ofSeconds(1));
    }

}
