SELECT Switch.id AS id
FROM Switch LEFT JOIN TrackElement_sensor ON TrackElement_sensor.TrackElement_id = Switch.id
WHERE TrackElement_sensor.TrackElement_id IS NULL;
