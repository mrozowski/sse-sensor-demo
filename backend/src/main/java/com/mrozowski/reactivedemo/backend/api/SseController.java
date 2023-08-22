package com.mrozowski.reactivedemo.backend.api;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;


@Slf4j
@RestController
@RequiredArgsConstructor
class SseController {

  private final Sinks.Many<String> sink;

  @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @CrossOrigin(origins = "http://localhost:3000")
  public Flux<String> sse() {
    return sink.asFlux();
  }

  @Scheduled(fixedRateString = "3000")
  public void run(){
    var random = Math.random();
    log.info("emit: {}", random);
    sink.tryEmitNext(String.valueOf(random));
  }

}
