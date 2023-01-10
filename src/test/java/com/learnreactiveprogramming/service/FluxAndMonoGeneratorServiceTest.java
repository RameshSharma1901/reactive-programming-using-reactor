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

}
