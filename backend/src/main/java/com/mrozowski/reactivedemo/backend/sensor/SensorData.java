package com.mrozowski.reactivedemo.backend.sensor;

import java.time.LocalDateTime;

public record SensorData(double temperature, int humidity, String timestamp) {
}
