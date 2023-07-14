package com.genesys.weatherqueryapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "weather_sensor_metric")
public class WeatherSensorMetric {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;

    @Getter
    @Setter
    @Column(name= "sensor_id")
    private String sensorId;

    @Getter
    @Setter
    @Column(name = "metric_type")
    private String metricType;

    @Getter
    @Setter
    @Column(name = "metric_value")
    private int metricValue;

    @Getter
    @Setter
    @Column(name = "timestamp")
    public Timestamp timestamp;


}
