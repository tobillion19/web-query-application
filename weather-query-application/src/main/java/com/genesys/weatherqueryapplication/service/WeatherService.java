package com.genesys.weatherqueryapplication.service;

import com.genesys.weatherqueryapplication.dto.WeatherMetricsDTO;
import com.genesys.weatherqueryapplication.model.WeatherSensorMetric;
import com.genesys.weatherqueryapplication.repository.WeatherSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    @Autowired
    private WeatherSensorRepository weatherSensorRepository;

    /**
     * Queries sensor data based on specified criteria.
     *
     * @param sensorId   The ID of the sensor.
     * @param metrics    The list of metric types to include in the query.
     * @param statistics  The statistic to calculate (e.g., average, minimum, maximum, sum).
     * @param startDate  The start date of the date range for filtering (optional).
     * @param endDate    The end date of the date range for filtering (optional).
     * @return The list of sensor metric DTOs representing the queried data.
     */
    public List<WeatherMetricsDTO> querySensorData(String sensorId, List<String> metrics, String statistics,LocalDateTime startDate, LocalDateTime endDate) {

        List<WeatherSensorMetric> queriedData = weatherSensorRepository.findByCriteria(sensorId,metrics,startDate,endDate);

        List<WeatherMetricsDTO> result = new ArrayList<>();

        for(String metric: metrics) {
            List<Integer> metricValues =  filterMetricValuesByType(queriedData, metric);

            List<Double> convertedDoubleList = new ArrayList<>();

            for(Integer integer: metricValues){
                double convertedValues = integer.doubleValue();
                convertedDoubleList.add(convertedValues);
            }

            WeatherMetricsDTO weatherMetricsDTO = new WeatherMetricsDTO();
            weatherMetricsDTO.setMetricType(metric);

            if(convertedDoubleList.isEmpty()) {
                weatherMetricsDTO.setAverage(0.0);
                weatherMetricsDTO.setMaximum(0.0);
                weatherMetricsDTO.setMinimum(0.0);
                weatherMetricsDTO.setSum(0.0);
            }else{

                switch (statistics) {
                    case "sum" -> weatherMetricsDTO.setSum(calculateSum(convertedDoubleList));
                    case "average" -> weatherMetricsDTO.setAverage(calculateAverage(convertedDoubleList));
                    case "maximum" -> weatherMetricsDTO.setMaximum(calculateMaximum(convertedDoubleList));
                    case "minimum" -> weatherMetricsDTO.setMinimum(calculateMinimum(convertedDoubleList));
                    default -> {
                        weatherMetricsDTO.setAverage(0.0);
                        weatherMetricsDTO.setMaximum(0.0);
                        weatherMetricsDTO.setMinimum(0.0);
                        weatherMetricsDTO.setSum(0.0);
                    }
                }
            }

            result.add(weatherMetricsDTO);

        }

        return result;

    }

    /**
     * Filters Metric values based on the metric type
     *
     * @param queriedData List of sensor metrics to filter
     * @param metricType The metric type to filter by
     * @return The list of filtered metric values
     */
    private List<Integer> filterMetricValuesByType(List<WeatherSensorMetric> queriedData, String metricType) {

        return queriedData.stream()
                .filter(metricData -> metricData.getMetricType().equals(metricType))
                .map(WeatherSensorMetric::getMetricValue)
                .collect(Collectors.toList());
    }


    /**
     * Calculates the maximum value from a list of metric values.
     *
     * @param values The list of metric values.
     * @return The calculated average value.
     */
    private double calculateMaximum(List<Double> values) {

        return values.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0);
    }

    /**
     * Calculates the average value from a list of metric values.
     *
     * @param values The list of metric values.
     * @return The calculated average value.
     */
    private double calculateAverage(List<Double> values) {

        return values.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

    }

    /**
     * Calculates the minimum value from a list of metric values.
     *
     * @param values The list of metric values.
     * @return The calculated minimum value.
     */
    private double calculateMinimum(List<Double> values) {

        return values.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0);

    }

    /**
     * Calculates the sum of metric values from a list of metric values.
     *
     * @param values The list of metric values.
     * @return The calculated sum of metric values.
     */
    private double calculateSum(List<Double> values) {
        return values.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }


}
