package com.learnreactiveprogramming.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class FunctionalExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of("ramesh", "Sharma")).log().subscribe(System.out::println);

        fluxGenerateTableExample();

        fluxGenerateFibonacciSeriesExample();

        fluxCreateExample();

    }

    private static void fluxCreateExample() {
        Flux<Integer> sequence = Flux.create((FluxSink<Integer> fluxSink) -> {
            IntStream.range(1,10)
                    .peek(i-> System.out.println("Emitting element : "+ i))
                    .forEach(fluxSink::next);
        }).log();

        sequence.delayElements(Duration.ofSeconds(2)).subscribe(i ->
                System.out.println("Received :"+i));
    }

    private static void fluxGenerateFibonacciSeriesExample() {
        Flux.generate( () -> Tuples.of(0, 1), (state, sink) -> {
            sink.next(state.getT1());
            System.out.println(state.getT1());
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        }).take(5).log().subscribe();
    }

    private static void fluxGenerateTableExample() {
        Flux.generate(()->0, (state, sink)->{
            state = state + 1;
            sink.next(3*state);
            System.out.println("3 x "+state+" = "+3*state);
            if (state == 10)
                sink.complete();
            return state;
        }).log().subscribe();
    }
}
