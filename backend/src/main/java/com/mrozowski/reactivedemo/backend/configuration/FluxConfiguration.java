package com.mrozowski.reactivedemo.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
class FluxConfiguration {

  @Bean
  public Sinks.Many<String> sink() {
    return Sinks.many().multicast().onBackpressureBuffer();
  }
}
