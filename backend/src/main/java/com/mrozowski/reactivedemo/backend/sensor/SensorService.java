package com.mrozowski.reactivedemo.backend.sensor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;


@Slf4j
@Service
@RequiredArgsConstructor
class SensorService {

  private final Sinks.Many<String> sink;
  private final static ObjectWriter WRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();

  public void updateTemperature(SensorData sensorData){
    var json = toJson(sensorData);
    sink.tryEmitNext(String.valueOf(json));
    log.info("Current Temperature: {} Celsius", sensorData);
  }

  private String toJson(SensorData sensorData) {
    try {
      return WRITER.writeValueAsString(sensorData);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
