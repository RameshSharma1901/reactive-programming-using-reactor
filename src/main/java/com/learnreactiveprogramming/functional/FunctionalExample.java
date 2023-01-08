package com.learnreactiveprogramming.functional;

import reactor.core.publisher.Flux;

import java.util.List;

public class FunctionalExample {
    public static void main(String[] args) {
        Flux.fromIterable(List.of("ramesh", "Sharma")).log().subscribe(System.out::println);

    }
}
