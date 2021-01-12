package com.liuliu.ceres.bootstrap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello/")
public class HelloController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HelloController.class);

    @GetMapping("mono")
    public Mono<String> mono() {
        return Mono.just("hello webflux");
    }

    @GetMapping("mono02")
    public Mono<Object> mono02() {
        return Mono.create(monoSink -> {
            log.info("create Mono {}", monoSink);
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> {
            log.info("doOnSubscribe {}", subscription);
        }).doOnNext(o -> {
            log.info("doOnNext {}", o);
        });
    }
}
