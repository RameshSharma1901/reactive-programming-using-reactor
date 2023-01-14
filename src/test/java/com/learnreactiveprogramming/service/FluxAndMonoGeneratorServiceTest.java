package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void testNameFlux() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("Ramesh", "Parth", "Pritesh")
                .verifyComplete();
        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void testNameMono() {

        StepVerifier.create(fluxAndMonoGeneratorService.namesMono())
                .expectNext("Pranav")
                .verifyComplete();
    }

    @Test
    void testNameFluxMap() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxMap();

        StepVerifier.create(namesFlux)
                .expectNext("RAMESH", "PARTH", "PRITESH")
                .verifyComplete();
    }

    @Test
    void testNameFluxFilter() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFilter(5);

        StepVerifier.create(namesFlux)
                .expectNext("RAMESH", "PRITESH")
                .verifyComplete();
    }

    @Test
    void testNameMonoMap() {
        var namesMono = fluxAndMonoGeneratorService.namesMonoMap();

        StepVerifier.create(namesMono)
                .expectNext("ALEX")
                .verifyComplete();
    }

    @Test
    void testNameFluxFlatMap() {
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFluxFlatMap();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("r", "a","m","e","s","h","s","h","a","r","m","a")
                .verifyComplete();
    }

    @Test
    void testNameFluxFlatMapAsync() {
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFluxFlatMapAsync();

        StepVerifier.create(namesFluxFlatMap)
                .expectNextCount(12)
                .verifyComplete();
    }


    @Test
    void testNameFluxConcatMap() {
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFluxConcatMap();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("r", "a","m","e","s","h","s","h","a","r","m","a")
                .verifyComplete();
    }

    @Test
    void testNameFluxFlatMapSequential() {
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFluxFlatMapSequential();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("r", "a","m","e","s","h","s","h","a","r","m","a")
                .verifyComplete();
    }
}
