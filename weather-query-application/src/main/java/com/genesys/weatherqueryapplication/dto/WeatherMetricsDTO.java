package com.genesys.weatherqueryapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class WeatherMetricsDTO {

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
