package com.learnreactiveprogramming.functional;

import reactor.core.publisher.Flux;

import java.util.List;

public class FunctionalExample {
    public static void main(String[] args) {
        Flux.fromIterable(List.of("ramesh", "Sharma")).log().subscribe(System.out::println);

        // Example for Flux.create()
        Flux.generate(()->0, (state, sink)->{
            state = state + 1;
            sink.next(3*state);
            System.out.println("3 x "+state+" = "+3*state);
            if (state == 10)
                sink.complete();
            return state;
        }).log().subscribe();

        // Example for Flux.generate
    }
}
