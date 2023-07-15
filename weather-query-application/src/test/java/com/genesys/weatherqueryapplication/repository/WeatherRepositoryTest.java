package com.genesys.weatherqueryapplication.repository;

import com.genesys.weatherqueryapplication.model.WeatherSensorMetric;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class WeatherRepositoryTest {

    @Autowired
    private WeatherSensorRepository weatherSensorRepository;


    @Test
    void testFindBySensorIdAndMetricTypeInAndTimestampBetween() {


        WeatherSensorMetric metric1 = new WeatherSensorMetric("sensor1", "temperature", 25, Timestamp.valueOf(LocalDateTime.now()));
        WeatherSensorMetric metric2 = new WeatherSensorMetric("sensor2", "temperature",33, Timestamp.valueOf(LocalDateTime.now()));

        weatherSensorRepository.saveAll(List.of(metric1,metric2));

        List<WeatherSensorMetric> result = weatherSensorRepository.findBySensorIdAndMetricTypeInAndTimestampBetween("sensor1", Arrays.asList("temperature","humidity"), LocalDateTime.now(), LocalDateTime.of(2023,7,10,10,0));


        // Verify the result
        assertEquals(0, result.size());
    }

}
