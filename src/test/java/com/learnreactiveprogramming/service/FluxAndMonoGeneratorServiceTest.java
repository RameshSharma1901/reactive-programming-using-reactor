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

    @Test
    void testDefaultIfEmptyExample() {
        var namesFluxFlatMap = fluxAndMonoGeneratorService.defaultIfEmptyExample();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("harshal")
                .verifyComplete();
    }

    @Test
    void testSwitchIfEmptyExample() {
        var namesFluxFlatMap = fluxAndMonoGeneratorService.switchIfEmptyExample();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("harshal")
                .verifyComplete();
    }

    @Test
    void testConcatExample() {
        var namesFluxConcat = fluxAndMonoGeneratorService.concatExample();

        StepVerifier.create(namesFluxConcat)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }
    @Test
    void testConcatWithExample() {
        var namesFluxConcatWith = fluxAndMonoGeneratorService.concatWithExample();

        StepVerifier.create(namesFluxConcatWith)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }

    @Test
    void testMergeExample() {
        var namesFluxMerge = fluxAndMonoGeneratorService.mergeExample();

        StepVerifier.create(namesFluxMerge)
                .expectNext("sharma", "ramesh")
                .verifyComplete();
    }

    @Test
    void testMergeWithExample() {
        var namesFluxMergeWith = fluxAndMonoGeneratorService.mergeWithExample();

        StepVerifier.create(namesFluxMergeWith)
                .expectNext("sharma", "ramesh")
                .verifyComplete();
    }
    @Test
    void testMergeSequentialExample() {
        var namesFluxMergeWith = fluxAndMonoGeneratorService.mergeSequentialExample();

        StepVerifier.create(namesFluxMergeWith)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }
    @Test
    void testZipExample() {
        var nameMonoZip = fluxAndMonoGeneratorService.zipMonoExample();

        StepVerifier.create(nameMonoZip)
                .expectNext("rameshamarchandsharma")
                .verifyComplete();
    }
    @Test
    void testZipWithExample() {
        var nameMonoZip = fluxAndMonoGeneratorService.zipWithMonoExample();

        StepVerifier.create(nameMonoZip)
                .expectNext("rameshsharma")
                .verifyComplete();
    }

    @Test
    void testZipFluxExample() {
        var zippedNamesFlux = fluxAndMonoGeneratorService.zipFluxExample();

        StepVerifier.create(zippedNamesFlux)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }
    @Test
    void testZipWithFluxExample() {
        var zippedNamesFlux = fluxAndMonoGeneratorService.zipWithFluxExample();

        StepVerifier.create(zippedNamesFlux)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }
}
