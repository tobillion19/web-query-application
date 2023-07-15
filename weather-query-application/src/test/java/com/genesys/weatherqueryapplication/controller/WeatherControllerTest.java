package com.genesys.weatherqueryapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesys.weatherqueryapplication.dto.WeatherMetricsDTO;
import com.genesys.weatherqueryapplication.model.WeatherSensorMetric;
import com.genesys.weatherqueryapplication.repository.WeatherSensorRepository;
import com.genesys.weatherqueryapplication.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 *
 * JUnit test class for {@link WeatherController}
 *
 */
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private WeatherSensorRepository weatherSensorRepository;

    @InjectMocks
    private WeatherController weatherController;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
    * Test case for createWeatherSensorMetric method
    *
    * @throws Exception if an error occurs during the test
    *
    * */
    @Test
    void testCreateWeatherSensorMetric() throws Exception {

        WeatherSensorMetric metrics = new WeatherSensorMetric("sensor1", "temperature", 25, Timestamp.valueOf(LocalDateTime.now()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/sensorMetric/addMetrics")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(metrics))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();

        Assertions.assertNotNull(mvcResult, "Result can't be null");

    }

    /**
     * Test case for the querySensorData method with the "average" statistic.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void testQuerySensorDataForAverage() throws Exception {

        List<WeatherMetricsDTO> queriedData = Arrays.asList(
                new WeatherMetricsDTO("sensor1", "temperature", 25.0),
                new WeatherMetricsDTO("sensor2", "temperature", 30.0)
        );

        when(weatherService.querySensorData(anyString(), anyList(), anyString(), any(), any())).thenReturn(queriedData);

        mockMvc.perform(get("/api/sensorMetric/queryMetrics")
                        .param("sensorId", "sensor1")
                        .param("metrics", "temperature")
                        .param("statistic", "average"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(queriedData)));

        // verify service method was called
        verify(weatherService, times(1)).querySensorData(eq("sensor1"), eq(Arrays.asList("temperature")), eq("average"), isNull(), isNull());
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
