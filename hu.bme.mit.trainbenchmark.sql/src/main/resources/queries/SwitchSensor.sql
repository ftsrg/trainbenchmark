SELECT Switch.id AS id
FROM Switch LEFT JOIN TrackElement ON TrackElement.id = Switch.id
WHERE TrackElement.sensor IS NULL;
