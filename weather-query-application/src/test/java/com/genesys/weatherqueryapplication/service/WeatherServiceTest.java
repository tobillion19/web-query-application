package com.genesys.weatherqueryapplication.service;

import com.genesys.weatherqueryapplication.dto.WeatherMetricsDTO;
import com.genesys.weatherqueryapplication.model.WeatherSensorMetric;
import com.genesys.weatherqueryapplication.repository.WeatherSensorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherServiceTest {


    @Mock
    private WeatherSensorRepository repository;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void testQuerySensorDataForAverage() {

        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "temperature", 25, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor1", "temperature", 30, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "temperature", 20, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(),anyList(),any(),any())).thenReturn(queriedData);

        List<WeatherMetricsDTO> result = weatherService.querySensorData("sensor1", List.of("temperature"), "average", null, null);

        assertEquals(1, result.size());
        assertEquals("temperature", result.get(0).getMetricType());
        assertEquals(25.0, result.get(0).getAverage());
    }

    @Test
    public void testQuerySensorDataForMinimum() {
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "humidity", 25,Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor1", "temperature", 30, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "temperature", 20, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(), anyList(), any(), any()))
                .thenReturn(queriedData);

        // Perform the test
        List<WeatherMetricsDTO> result = weatherService.querySensorData(null, Arrays.asList("humidity"), "minimum", null, null);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("humidity", result.get(0).getMetricType());
        assertEquals(0.0, result.get(0).getMinimum());
    }

    @Test
    public void testQuerySensorDataForMaximum() {
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "windSpeed", 10, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "windSpeed", 15, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor3", "windSpeed", 12, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(), anyList(), any(), any()))
                .thenReturn(queriedData);

        // Perform the test
        List<WeatherMetricsDTO> result = weatherService.querySensorData(null, Arrays.asList("windSpeed"), "maximum", null, null);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("windSpeed", result.get(0).getMetricType());
        assertEquals(0, result.get(0).getMaximum());
    }

    @Test
    public void testQuerySensorDataForSum() {
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "rainfall", 5, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "rainfall", 10, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor3", "rainfall", 7, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(), anyList(), any(), any()))
                .thenReturn(queriedData);

        // Perform the test
        List<WeatherMetricsDTO> result = weatherService.querySensorData(null, Arrays.asList("rainfall"), "sum", null, null);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("rainfall", result.get(0).getMetricType());
        assertEquals(0, result.get(0).getSum());
    }

    @Test
    public void testQuerySensorDataWithDateRange() {
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "humidity", 25,Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor1", "temperature", 30, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "temperature", 20, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(), anyList(), any(), any()))
                .thenReturn(queriedData);

        // Perform the test
        LocalDateTime startDate = LocalDateTime.of(2023, 7, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 7, 3, 0, 0);
        List<WeatherMetricsDTO> result = weatherService.querySensorData("sensor1", List.of("temperature"), "average", startDate, endDate);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("temperature", result.get(0).getMetricType());
        assertEquals(25.0, result.get(0).getAverage(), 0.01);
    }

}
