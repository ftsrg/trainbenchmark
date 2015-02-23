SELECT Switch.id AS id, Switch_actualState, Sensor_id
FROM Switch LEFT JOIN TrackElement_sensor ON TrackElement_sensor.TrackElement_id = Switch.id
WHERE TrackElement_sensor.TrackElement_id IS NULL;
