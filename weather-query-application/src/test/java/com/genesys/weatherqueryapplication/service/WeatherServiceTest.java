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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherServiceTest {


    @Mock
    private WeatherSensorRepository repository;

    @InjectMocks
    private WeatherService weatherService;


    /**
     * Test case for the querySensorData method with the "average" statistic.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void testQuerySensorDataForAverage() throws Exception{

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

    /**
     * Test case for the querySensorData method with the "minimum" statistic.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testQuerySensorDataForMinimum() throws Exception {
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "temperature", 25, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor1", "temperature", 30, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "temperature", 20, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(), anyList(), any(), any()))
                .thenReturn(queriedData);

        // Perform the test
        List<WeatherMetricsDTO> result = weatherService.querySensorData("sensor1", Arrays.asList("temperature"), "minimum", null, null);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("temperature", result.get(0).getMetricType());
        assertEquals(20.0, result.get(0).getMinimum());
    }

    /**
     * Test case for the querySensorData method with the "maximum" statistic.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testQuerySensorDataForMaximum() throws Exception{
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "temperature", 25, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor1", "temperature", 30, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "temperature", 20, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(), anyList(), any(), any()))
                .thenReturn(queriedData);

        // Perform the test
        List<WeatherMetricsDTO> result = weatherService.querySensorData("sensor1", Arrays.asList("temperature"), "maximum", null, null);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("temperature", result.get(0).getMetricType());
        assertEquals(20.0, result.get(0).getMaximum());
    }

    /**
     * Test case for the querySensorData method with the "sum" statistic.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testQuerySensorDataForSum() throws Exception{
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "temperature", 25, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor1", "temperature", 30, Timestamp.valueOf(LocalDateTime.now())),
                new WeatherSensorMetric("sensor2", "temperature", 20, Timestamp.valueOf(LocalDateTime.now()))
        );
        when(repository.findBySensorIdAndMetricTypeInAndTimestampBetween(anyString(), anyList(), any(), any()))
                .thenReturn(queriedData);

        // Perform the test
        List<WeatherMetricsDTO> result = weatherService.querySensorData("sensor1", Arrays.asList("temperature"), "sum", null, null);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("temperature", result.get(0).getMetricType());
        assertEquals(75.0, result.get(0).getSum());
    }


    /**
     * Test case for the querySensorData method with the "startDate" & "endDate" date range statistic.
     *
     * @throws Exception if an error occurs during the test
     */

    @Test
    public void testQuerySensorDataWithDateRange() throws Exception {
        // Mocked data
        List<WeatherSensorMetric> queriedData = Arrays.asList(
                new WeatherSensorMetric("sensor1", "temperature", 25, Timestamp.valueOf(LocalDateTime.now())),
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
