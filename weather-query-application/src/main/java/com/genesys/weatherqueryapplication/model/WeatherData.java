package com.genesys.weatherqueryapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
public class WeatherData {

    @Getter
    @Setter
    public Long sensorId;

    @Getter
    @Setter
    public int temperature;

    @Getter
    @Setter
    public int humidity;

    @Getter
    @Setter
    public int windSpeed;

    @Getter
    @Setter
    public Timestamp timestamp;


}
