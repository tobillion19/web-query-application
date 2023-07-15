package com.genesys.weatherqueryapplication.controller;

import com.genesys.weatherqueryapplication.dto.WeatherMetricsDTO;
import com.genesys.weatherqueryapplication.exception.InvalidDataTypeException;
import com.genesys.weatherqueryapplication.model.WeatherSensorMetric;
import com.genesys.weatherqueryapplication.repository.WeatherSensorRepository;
import com.genesys.weatherqueryapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sensorMetric")
public class WeatherController {

    @Autowired
    private WeatherSensorRepository weatherSensorRepository;

    @Autowired
    private WeatherService weatherService;


    /**
     * Route to create a new Weather Sensor Metric entry
     *
     * @param weatherSensorMetric WeatherSensorMetric Object representing the new entry
     * @return Response Entity indicating the status of the operation
     */
    @PostMapping("/addMetric")
    public ResponseEntity<String> addMetric(@RequestBody WeatherSensorMetric weatherSensorMetric) {
        weatherService.createSensorMetric(weatherSensorMetric);
        return ResponseEntity.ok("Weather Sensor Metric Successfully created");
    }

    /**
     * Route to query sensor data based on specified criteria.
     *
     * @param sensorId   The ID of the sensor (optional).
     * @param metrics    The list of metric types to include in the query (optional).
     * @param statistic  The statistic to calculate (e.g., average, minimum, maximum, sum).
     * @param startDate  The start date of the date range for filtering (optional).
     * @param endDate    The end date of the date range for filtering (optional).
     * @return The response entity containing the list of sensor metric DTOs representing the queried data.
     */
    @GetMapping("/queryMetrics")
    public ResponseEntity<List<WeatherMetricsDTO>> querySensorData(
            @RequestParam(value = "sensorId", required = false) String sensorId,
            @RequestParam(value = "metricType", required = false) List<String> metrics,
            @RequestParam(value = "statistic", required = false) String statistic,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
            ){

        // Check for invalid data types
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new InvalidDataTypeException("startDate must be before endDate");
        }



        List<WeatherMetricsDTO> queriedData = weatherService.querySensorData(sensorId,metrics,statistic,startDate,endDate);

        return ResponseEntity.ok(queriedData);


    }



}
