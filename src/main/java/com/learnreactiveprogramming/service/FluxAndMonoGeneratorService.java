package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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

    /**
     * defaultIfEmpty and switchIfEmpty Example
     */
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

    /**
     * concat and concatWith with Examples
     */
    public Flux<String> concatExample(){
        var namesFluxOne = Flux.just("ramesh").delayElements(Duration.ofSeconds(2));
        var namesFluxTwo = Flux.just("sharma");

        return Flux.concat(namesFluxOne, namesFluxTwo);
    }
    public Flux<String> concatWithExample(){
        Mono<String> nameMonoOne = Mono.just("ramesh").delayElement(Duration.ofSeconds(2));
        Mono<String> nameMonoTwo = Mono.just("sharma");

        return nameMonoOne.concatWith(nameMonoTwo).log();
    }

    /**
     * merge, mergeWith and mergeSequential Examples
     */
    public Flux<String> mergeExample(){
        Mono<String> nameMonoOne = Mono.just("ramesh").delayElement(Duration.ofSeconds(2));
        Mono<String> nameMonoTwo = Mono.just("sharma");

        return Flux.merge(nameMonoOne, nameMonoTwo).log();
    }
    public Flux<String> mergeWithExample(){
        Mono<String> nameMonoOne = Mono.just("ramesh").delayElement(Duration.ofSeconds(2));
        Mono<String> nameMonoTwo = Mono.just("sharma");

        return nameMonoOne.mergeWith(nameMonoTwo).log();
    }
    public Flux<String> mergeSequentialExample(){
        Mono<String> nameMonoOne = Mono.just("ramesh").delayElement(Duration.ofSeconds(2));
        Mono<String> nameMonoTwo = Mono.just("sharma");

        return Flux.mergeSequential(nameMonoOne, nameMonoTwo).log();
    }


    /**
     * zip and zipWith Examples
     * @return Mono<String>
     */
    public Mono<String> zipMonoExample(){
        Mono<String> nameMonoOne = Mono.just("ramesh").delayElement(Duration.ofSeconds(2));
        Mono<String> nameMonoTwo = Mono.just("sharma");
        Mono<String> nameMonoThree = Mono.just("amarchand");

        return Mono.zip(nameMonoOne, nameMonoTwo, nameMonoThree)
                .map(tuple -> tuple.getT1() + tuple.getT3() + tuple.getT2())
                .log();
    }

    public Flux<String> zipFluxExample(){
        Flux<String> namesFluxOne = Flux.just("ram", "sha");
        Flux<String> namesFluxTwo = Flux.just("esh","rma");

        return Flux.zip(namesFluxOne, namesFluxTwo)
                .map(tuple -> tuple.getT1() + tuple.getT2())
                .log();
    }

    public Mono<String> zipWithMonoExample(){
        Mono<String> nameMonoOne = Mono.just("ramesh").delayElement(Duration.ofSeconds(2));
        Mono<String> nameMonoTwo = Mono.just("sharma");

        return nameMonoOne.zipWith(nameMonoTwo, (s1, s2) -> s1 + s2);
    }

    public Flux<String> zipWithFluxExample(){
        Flux<String> namesFluxOne = Flux.just("ram", "sha").delayElements(Duration.ofSeconds(2));
        Flux<String> namesFluxTwo = Flux.just("esh","rma");

        return namesFluxOne.zipWith(namesFluxTwo, (s1,s2)-> s1+s2)
                .log();
    }

    private Flux<String> splitString(String name){

        return Flux.fromArray(name.split("")).delayElements(Duration.ofSeconds(1));
    }

}
