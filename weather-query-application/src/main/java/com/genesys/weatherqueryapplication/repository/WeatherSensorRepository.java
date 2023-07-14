package com.genesys.weatherqueryapplication.repository;

import com.genesys.weatherqueryapplication.model.WeatherSensorMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherSensorRepository extends JpaRepository<WeatherSensorMetric, Long> {

    List<WeatherSensorMetric> findBySensorId(String sensorId);

    List<WeatherSensorMetric> findByCriteria(String sensorId, List<String> metrics, LocalDateTime startDate, LocalDateTime endDate);
}
