package com.mrozowski.reactivedemo.backend.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Sinks;

@Configuration
class FluxConfiguration {

  @Bean
  public Sinks.Many<String> sink() {
    return Sinks.many().multicast().onBackpressureBuffer();
  }
}
