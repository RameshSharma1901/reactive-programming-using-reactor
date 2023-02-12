package com.learnreactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

@Slf4j
public class FluxAndMonoSchedulersService {

    static List<String> namesList = List.of("alex", "ben", "chloe");
    static List<String> namesList1 = List.of("adam", "jill", "jack");

    public ParallelFlux<String> explore_parallelFlux(){
        log.info("Number of processors : {}", Runtime.getRuntime().availableProcessors());
        return Flux.fromIterable(namesList)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(this::upperCase)
                .log();
    }
    private String upperCase(String name) {
        delay(1000);
        return name.toUpperCase();
    }

}
