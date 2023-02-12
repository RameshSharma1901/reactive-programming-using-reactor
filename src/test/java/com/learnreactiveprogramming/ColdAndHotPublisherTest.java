package com.learnreactiveprogramming;

import com.learnreactiveprogramming.util.CommonUtil;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ColdAndHotPublisherTest {

    @Test
    void coldPublisherTest() {
        var flux = Flux.range(1,10);
        flux.subscribe(value -> System.out.println("Subscriber 1 "+value));
        flux.subscribe(value -> System.out.println("Subscriber 2 "+value));
    }

    @Test
    void hotPublisherTest() {
        var flux = Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1));

        ConnectableFlux<Integer> connectableFlux = flux.publish();
        connectableFlux.connect();

        connectableFlux.subscribe(value -> System.out.println("Subscriber 1 "+value));
        CommonUtil.delay(4000);

        connectableFlux.subscribe(value -> System.out.println("Subscriber 2 "+value));
        CommonUtil.delay(10000);
    }

    @Test
    public void hotPublisherTest_autoConnect() {
        var flux = Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1));

        var hotSource = flux.publish().autoConnect(2);

        hotSource.subscribe(value -> System.out.println("Subscriber 1 "+value));
        CommonUtil.delay(2000);

        hotSource.subscribe(value -> System.out.println("Subscriber 2 "+value));
        System.out.println("Two subscribers connected");
        CommonUtil.delay(2000);
        hotSource.subscribe(value -> System.out.println("Subscriber 3 "+value));
        CommonUtil.delay(10000);
    }

    @Test
    public void refCount() {
        var flux = Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnCancel(() -> System.out.println("Cancel Signal received"));

        var hotSource = flux.publish().refCount(2);

        Disposable disposable = hotSource.subscribe(value -> System.out.println("Subscriber 1 "+value));
        CommonUtil.delay(2000);

        Disposable disposable1 = hotSource.subscribe(value -> System.out.println("Subscriber 2 "+value));
        System.out.println("Two subscribers connected");
        CommonUtil.delay(2000);
        disposable1.dispose();
        disposable.dispose();
        hotSource.subscribe(value -> System.out.println("Subscriber 3 "+value));
        CommonUtil.delay(2000);
        hotSource.subscribe(value -> System.out.println("Subscriber 4 "+value));
        CommonUtil.delay(10000);
    }
}
