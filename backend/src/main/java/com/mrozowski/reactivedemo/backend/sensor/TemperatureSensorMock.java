package com.mrozowski.reactivedemo.backend.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemperatureSensorMock {

  private static final int MAX_TEMPERATURE = 110;
  private static final int IDLE_TEMPERATURE = 30;
  private static final int MAX_HUMIDITY = 100;
  private static final int MIN_HUMIDITY = 10;
  private static final Random RANDOM = new Random();
  private final SensorService sensorService;

  private double previousTemperature = 45;
  private int previousHumidity = 30;

  @Scheduled(fixedRate = 3000)
  public void simulateTemperature() {
    var newTemperature = generateTemperature();
    var newHumidity = generateHumidity();
    previousTemperature = newTemperature;
    previousHumidity = newHumidity;
    sensorService.updateTemperature(new SensorData(newTemperature, newHumidity, formatDateTime(LocalDateTime.now())));
  }

  private int generateHumidity() {
    var nextValue = (int) Math.ceil(generateRandomNumber(-2, 2));
    var newHumidity = nextValue + previousHumidity;
    if(newHumidity > MAX_HUMIDITY){
      return MAX_HUMIDITY;
    }

    if(newHumidity < MIN_HUMIDITY){
      return MIN_HUMIDITY;
    }
    return newHumidity;
  }

  private double generateTemperature() {
    var nextValue = Math.round(generateRandomNumber(-8, 8));
    var newTemperature = nextValue + previousTemperature;
    if(newTemperature > MAX_TEMPERATURE + 5){
      return MAX_TEMPERATURE;
    }

    if(newTemperature < IDLE_TEMPERATURE + 5){
      return IDLE_TEMPERATURE;
    }
    return newTemperature;
  }

  private String formatDateTime(LocalDateTime localDateTime){
    return localDateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH));
  }

  static double generateRandomNumber(int min, int max) {
    return RANDOM.nextDouble((max - min) + 1) + min;
  }
}