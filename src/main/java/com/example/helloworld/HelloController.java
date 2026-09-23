package com.example.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import java.time.Duration;

@RestController
public class HelloController {

    @GetMapping("/hello/")
    public Mono<String> sayHello() {
        return Mono.just("Hello World from Reactive Spring Boot & Netty on Orange Pi 4 Pro!");
    }

    // Новый эндпоинт, возвращающий поток (Flux)
    @GetMapping(value = "hello/stream", produces = org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamTelemetry() {
        return Flux.interval(Duration.ofSeconds(1)) // генерирует тик каждую секунду
                .map(sequence -> "Telemetry packet #" + sequence + " from Orange Pi 4 Pro [OK]");
    }
}