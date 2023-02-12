package com.learnreactiveprogramming;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class BackpressureTest {

    @Test
    public void explore_backPressure() {
        var numberRange = Flux.range(1, 100).log();

        numberRange.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(2);
            }

            @Override
            protected void hookOnNext(Integer value) {
//                super.hookOnNext(value);
                log.info("hookOnNext : {}", value);
                if (value == 2)
                    cancel();
            }

            @Override
            protected void hookOnComplete() {
//                super.hookOnComplete();
            }

            @Override
            protected void hookOnCancel() {
//                super.hookOnCancel();
                log.info("Inside onCancel");
            }
        });
    }

    @Test
    public void explore_backPressure_1() {
        var numberRange = Flux.range(1, 100).log();

        numberRange.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(2);
            }

            @Override
            protected void hookOnNext(Integer value) {
//                super.hookOnNext(value);
                log.info("hookOnNext : {}", value);
                if (value % 2 == 0 || value < 50) { //requesting only 50 elements
                    request(2);
                } else {
                    cancel();
                }
            }

            @Override
            protected void hookOnComplete() {
//                super.hookOnComplete();
            }

            @Override
            protected void hookOnCancel() {
//                super.hookOnCancel();
                log.info("Inside onCancel");
            }
        });
    }

    @Test
    public void explore_backPressure_with_onBackpressureDrop() {
        var numberRange = Flux.range(1, 100).log();

        numberRange.onBackpressureDrop(num -> log.info("Dropped element is: {}", num))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
//                super.hookOnNext(value);
                        log.info("hookOnNext : {}", value);
                        hookOnCancel();

                    }

                    @Override
                    protected void hookOnComplete() {
//                super.hookOnComplete();
                    }

                    @Override
                    protected void hookOnCancel() {
//                super.hookOnCancel();
                        log.info("Inside onCancel");
                    }
                });
    }

    @Test
    public void explore_backPressure_with_onBackpressureBuffer() {
        var numberRange = Flux.range(1, 100).log();

        numberRange.onBackpressureBuffer(10, num -> log.info("Last buffered element = is {}", num))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
//                super.hookOnNext(value);
                        if(value<50){
                            request(1);
                            log.info("hookOnNext : {}", value);
                        } else {
                            hookOnCancel();
                        }

                    }

                    @Override
                    protected void hookOnComplete() {
//                super.hookOnComplete();
                    }

                    @Override
                    protected void hookOnCancel() {
//                super.hookOnCancel();
                        log.info("Inside onCancel");
                    }
                });
    }

    @Test
    public void explore_backPressure_with_onBackpressureError() {
        var numberRange = Flux.range(1, 100).log();

        numberRange.onBackpressureError()
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
//                super.hookOnNext(value);
                        if(value<50){
                            request(1);
                            log.info("hookOnNext : {}", value);
                        } else {
                            hookOnCancel();
                        }

                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        log.error("Exception is ", throwable);
                    }

                    @Override
                    protected void hookOnComplete() {
//                super.hookOnComplete();
                    }

                    @Override
                    protected void hookOnCancel() {
//                super.hookOnCancel();
                        log.info("Inside onCancel");
                    }
                });
    }
}
