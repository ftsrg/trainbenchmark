SELECT Switch.id AS id
FROM Switch LEFT JOIN sensor ON sensor.TrackElement_id = Switch.id
WHERE sensor.TrackElement_id IS NULL;
