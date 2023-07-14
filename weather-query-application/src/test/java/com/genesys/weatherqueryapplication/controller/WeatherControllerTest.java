package com.genesys.weatherqueryapplication.controller;

import com.genesys.weatherqueryapplication.dto.WeatherMetricsDTO;
import com.genesys.weatherqueryapplication.model.WeatherSensorMetric;
import com.genesys.weatherqueryapplication.repository.WeatherSensorRepository;
import com.genesys.weatherqueryapplication.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateSensorMetric() throws Exception {

//        WeatherMetricsDTO metricsDTO = new WeatherMetricsDTO("sensor1", "temperature",25,5);
//
//        // Perform the test
//        mockMvc.perform(post("/api/metrics")
//                        .contentType(MediaType.APPLICATION_JSON).cont
//                        .contentType(asJsonString(metricsDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Sensor metric created successfully"));
//
//        // Verify the service method was called
//        verify(weatherService, times(1)).createSensorMetric(any(WeatherMetricsDTO.class));


    }

}
