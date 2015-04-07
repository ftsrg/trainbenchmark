SELECT Switch.id AS sw
FROM Switch LEFT JOIN TrackElement ON TrackElement.id = Switch.id
WHERE TrackElement.sensor IS NULL;
