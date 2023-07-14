CREATE TABLE IF NOT EXISTS weather_sensor_metric (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_id VARCHAR(50) NOT NULL,
    metric_type VARCHAR(50) NOT NULL,
    metric_value DOUBLE NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

INSERT INTO weather_sensor_metric (sensor_id, metric_type, metric_value, timestamp) VALUES ('sensor1', 'temperature', 25.0, '2023-07-10 10:00:00');
INSERT INTO weather_sensor_metric (sensor_id, metric_type, metric_value, timestamp) VALUES ('sensor1', 'humidity', 70.0, '2023-07-10 10:00:00');
INSERT INTO weather_sensor_metric (sensor_id, metric_type, metric_value, timestamp) VALUES ('sensor2', 'temperature', 27.0, '2023-07-10 10:00:00');
INSERT INTO weather_sensor_metric (sensor_id, metric_type, metric_value, timestamp) VALUES ('sensor2', 'humidity', 75.0, '2023-07-10 10:00:00');