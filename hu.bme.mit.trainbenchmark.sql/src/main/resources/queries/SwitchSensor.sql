SELECT Switch.TrackElement_id AS id, Switch_actualState, Sensor_id
FROM Switch LEFT JOIN TrackElement_sensor ON TrackElement_sensor.TrackElement_id = Switch.TrackElement_id
WHERE TrackElement_sensor.TrackElement_id IS NULL;
