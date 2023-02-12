package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoSchedulersServiceTest {

    FluxAndMonoSchedulersService fluxAndMonoSchedulersService = new FluxAndMonoSchedulersService();
    @Test
    void explore_parallelFlux() {
        var namesFlux = fluxAndMonoSchedulersService.explore_parallelFlux();

        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void explore_parallelFlux_withFlatMap() {
        var namesFlux = fluxAndMonoSchedulersService.explore_parallelismUsingFlatMap();

        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void explore_parallelFlux_withFlatMap_withMerge() {
        var namesFlux = fluxAndMonoSchedulersService.explore_parallelismUsingFlatMap_withMergeWithOperator();

        StepVerifier.create(namesFlux)
                .expectNextCount(6)
                .verifyComplete();
    }
}