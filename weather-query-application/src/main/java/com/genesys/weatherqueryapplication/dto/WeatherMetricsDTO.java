package com.genesys.weatherqueryapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class WeatherMetricsDTO {

    public WeatherMetricsDTO(String sensorId, String metricType, double value) {
        this.sensorId = sensorId;
        this.metricType = metricType;
        this.value = value;
    }

    @Getter
    @Setter
    private String sensorId;
    @Getter
    @Setter
    private String metricType;

    @Getter
    @Setter
    private double value;

    @Getter
    @Setter
    private double average;

    @Getter
    @Setter
    private double minimum;

    @Getter
    @Setter
    private double maximum;

    @Getter
    @Setter
    private double sum;
}
