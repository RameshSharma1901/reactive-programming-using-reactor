package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoOperatorsExampleTest {
    FluxAndMonoOperatorsExample fluxAndMonoOperatorsExample = new FluxAndMonoOperatorsExample();

    @Test
    void testNameFlux() {
        var namesFlux = fluxAndMonoOperatorsExample.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("Ramesh", "Parth", "Pritesh")
                .verifyComplete();
        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void testNameMono() {

        StepVerifier.create(fluxAndMonoOperatorsExample.namesMono())
                .expectNext("Pranav")
                .verifyComplete();
    }

    @Test
    void testNameFluxMap() {
        var namesFlux = fluxAndMonoOperatorsExample.namesFluxMap();

        StepVerifier.create(namesFlux)
                .expectNext("RAMESH", "PARTH", "PRITESH")
                .verifyComplete();
    }

    @Test
    void testNameFluxFilter() {
        var namesFlux = fluxAndMonoOperatorsExample.namesFluxFilter(5);

        StepVerifier.create(namesFlux)
                .expectNext("RAMESH", "PRITESH")
                .verifyComplete();
    }

    @Test
    void testNameMonoMap() {
        var namesMono = fluxAndMonoOperatorsExample.namesMonoMap();

        StepVerifier.create(namesMono)
                .expectNext("ALEX")
                .verifyComplete();
    }

    @Test
    void testNameFluxFlatMap() {
        var namesFluxFlatMap = fluxAndMonoOperatorsExample.namesFluxFlatMap();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("r", "a","m","e","s","h","s","h","a","r","m","a")
                .verifyComplete();
    }

    @Test
    void testNameFluxFlatMapAsync() {
        var namesFluxFlatMap = fluxAndMonoOperatorsExample.namesFluxFlatMapAsync();

        StepVerifier.create(namesFluxFlatMap)
                .expectNextCount(12)
                .verifyComplete();
    }


    @Test
    void testNameFluxConcatMap() {
        var namesFluxFlatMap = fluxAndMonoOperatorsExample.namesFluxConcatMap();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("r", "a","m","e","s","h","s","h","a","r","m","a")
                .verifyComplete();
    }

    @Test
    void testNameFluxFlatMapSequential() {
        var namesFluxFlatMap = fluxAndMonoOperatorsExample.namesFluxFlatMapSequential();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("r", "a","m","e","s","h","s","h","a","r","m","a")
                .verifyComplete();
    }

    @Test
    void testDefaultIfEmptyExample() {
        var namesFluxFlatMap = fluxAndMonoOperatorsExample.defaultIfEmptyExample();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("harshal")
                .verifyComplete();
    }

    @Test
    void testSwitchIfEmptyExample() {
        var namesFluxFlatMap = fluxAndMonoOperatorsExample.switchIfEmptyExample();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("harshal")
                .verifyComplete();
    }

    @Test
    void testConcatExample() {
        var namesFluxConcat = fluxAndMonoOperatorsExample.concatExample();

        StepVerifier.create(namesFluxConcat)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }
    @Test
    void testConcatWithExample() {
        var namesFluxConcatWith = fluxAndMonoOperatorsExample.concatWithExample();

        StepVerifier.create(namesFluxConcatWith)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }

    @Test
    void testMergeExample() {
        var namesFluxMerge = fluxAndMonoOperatorsExample.mergeExample();

        StepVerifier.create(namesFluxMerge)
                .expectNext("sharma", "ramesh")
                .verifyComplete();
    }

    @Test
    void testMergeWithExample() {
        var namesFluxMergeWith = fluxAndMonoOperatorsExample.mergeWithExample();

        StepVerifier.create(namesFluxMergeWith)
                .expectNext("sharma", "ramesh")
                .verifyComplete();
    }
    @Test
    void testMergeSequentialExample() {
        var namesFluxMergeWith = fluxAndMonoOperatorsExample.mergeSequentialExample();

        StepVerifier.create(namesFluxMergeWith)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }
    @Test
    void testZipExample() {
        var nameMonoZip = fluxAndMonoOperatorsExample.zipMonoExample();

        StepVerifier.create(nameMonoZip)
                .expectNext("rameshamarchandsharma")
                .verifyComplete();
    }
    @Test
    void testZipWithExample() {
        var nameMonoZip = fluxAndMonoOperatorsExample.zipWithMonoExample();

        StepVerifier.create(nameMonoZip)
                .expectNext("rameshsharma")
                .verifyComplete();
    }

    @Test
    void testZipFluxExample() {
        var zippedNamesFlux = fluxAndMonoOperatorsExample.zipFluxExample();

        StepVerifier.create(zippedNamesFlux)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }
    @Test
    void testZipWithFluxExample() {
        var zippedNamesFlux = fluxAndMonoOperatorsExample.zipWithFluxExample();

        StepVerifier.create(zippedNamesFlux)
                .expectNext("ramesh", "sharma")
                .verifyComplete();
    }

    @Test
    void testOnErrorReturn() {
        var onErrorReturnFlux = fluxAndMonoOperatorsExample.onErrorReturn();

        StepVerifier.create(onErrorReturnFlux)
                .expectNext("A", "B", "C", "D")
                .verifyComplete();

    }

    @Test
    void testOnErrorResume() {
        var onErrorResume = fluxAndMonoOperatorsExample.onErrorResume();

        StepVerifier.create(onErrorResume)
                .expectNext("A", "B", "C", "D")
                .verifyComplete();

    }
    @Test
    void testOnErrorContinue() {
        var onErrorContinue = fluxAndMonoOperatorsExample.onErrorContinue();

        StepVerifier.create(onErrorContinue)
                .expectNext("A", "C", "D")
                .verifyComplete();

    }
}
